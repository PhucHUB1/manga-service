package com.manganoob.mangaservice.service;

import com.manganoob.mangaservice.dto.request.RatingsRequest;
import com.manganoob.mangaservice.dto.response.AverageRatingResponse;
import com.manganoob.mangaservice.dto.response.RatingsResponse;
import com.manganoob.mangaservice.entity.Manga;
import com.manganoob.mangaservice.entity.Ratings;
import com.manganoob.mangaservice.entity.User;
import com.manganoob.mangaservice.mapper.RatingsMapper;
import com.manganoob.mangaservice.repository.MangaRepository;
import com.manganoob.mangaservice.repository.RatingsRepository;
import com.manganoob.mangaservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RatingService {
    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private RatingsMapper ratingsMapper;

    private User getCurrentAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    public RatingsResponse createRating(UUID mangaId, RatingsRequest request) {
        User currentUser = getCurrentAuthenticatedUser();
        Optional<Manga> mangaOptional = mangaRepository.findById(mangaId);

        if (mangaOptional.isPresent()) {
            Ratings rating = ratingsMapper.toEntity(request);
            rating.setUser(currentUser);
            rating.setManga(mangaOptional.get());
            Ratings savedRating = ratingsRepository.save(rating);

            return ratingsMapper.toResponse(savedRating);
        } else {
            throw new RuntimeException("Manga not found.");
        }
    }
    public RatingsResponse updateRating(Long ratingId, RatingsRequest request) {
        Ratings rating = ratingsRepository.findById(ratingId)
                .orElseThrow(() -> new RuntimeException("Rating not found."));

        // Check if the current user is the owner of the rating
        User currentUser = getCurrentAuthenticatedUser();
        if (!rating.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You are not authorized to update this rating.");
        }

        rating.setScore(request.getScore());
        Ratings updatedRating = ratingsRepository.save(rating);

        return ratingsMapper.toResponse(updatedRating);
    }
    public void deleteRating(Long ratingId) {
        Ratings rating = ratingsRepository.findById(ratingId)
                .orElseThrow(() -> new RuntimeException("Rating not found."));

        // Check if the current user is the owner of the rating
        User currentUser = getCurrentAuthenticatedUser();
        if (!rating.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You are not authorized to delete this rating.");
        }

        ratingsRepository.delete(rating);
    }

    public List<RatingsResponse> getRatingsByManga(UUID mangaId) {
        List<Ratings> ratings = ratingsRepository.findByMangaId(mangaId);
        return ratings.stream()
                .map(ratingsMapper::toResponse)
                .toList();
    }

    public AverageRatingResponse getAverageRatingByManga(UUID mangaId) {
        List<Ratings> ratings = ratingsRepository.findByMangaId(mangaId);

        if (ratings.isEmpty()) {
            return new AverageRatingResponse(0.0, mangaId);
        }

        double averageRating = ratings.stream()
                .mapToInt(Ratings::getScore)
                .average()
                .orElse(0.0);

        return new AverageRatingResponse(averageRating, mangaId);
    }
}
