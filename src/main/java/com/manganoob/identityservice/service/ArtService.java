package com.manganoob.identityservice.service;

import com.manganoob.identityservice.dto.request.manga_req.ArtRequest;
import com.manganoob.identityservice.dto.response.manga_res.ArtResponse;
import com.manganoob.identityservice.entity.Art;
import com.manganoob.identityservice.entity.Manga;
import com.manganoob.identityservice.exception.AppException;
import com.manganoob.identityservice.exception.ErrorCode;
import com.manganoob.identityservice.mapper.ArtMapper;
import com.manganoob.identityservice.repository.ArtRepository;
import com.manganoob.identityservice.repository.MangaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ArtService {
    ArtRepository artRepository;
    MangaRepository mangaRepository;
    ArtMapper artMapper;


    public ArtResponse postArt(ArtRequest request, MultipartFile imageArt) throws IOException {
        Art art = artMapper.toEntity(request,imageArt);
        if (imageArt != null && !imageArt.isEmpty()) {
            try {
                art.setArt(imageArt.getBytes());
                art.setArtName(imageArt.getOriginalFilename());
            } catch (IOException e) {
                throw new AppException(ErrorCode.AVATAR_UPLOAD_FAILED);
            }
        }

        try {
            art = artRepository.save(art);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.EXISTED);
        }
        return artMapper.toResponse(art);
    }


    public ArtResponse getArtById(UUID id) {
        Optional<Art> artOpt = artRepository.findById(id);
        if (artOpt.isPresent()) {
            return artMapper.toResponse(artOpt.get());
        }
        throw new RuntimeException("Art not found with id: " + id);
    }

    public List<ArtResponse> getAllArtsByMangaId(UUID mangaId) {
        Manga manga = mangaRepository.findById(mangaId)
                .orElseThrow(() -> new RuntimeException("Manga not found with id: " + mangaId));
        List<Art> arts = artRepository.findByMangaId(mangaId);
        return arts.stream().map(artMapper::toResponse).toList();
    }

    public ArtResponse updateArt(UUID id, ArtRequest request) throws IOException {
        Optional<Art> artOpt = artRepository.findById(id);
        if (artOpt.isPresent()) {
            Art art = artOpt.get();

            if (request.getImageArt() != null && !request.getImageArt().isEmpty()) {
                art.setArt(request.getImageArt().getBytes());
                art.setArtName(request.getImageArt().getOriginalFilename());
            }

            art = artRepository.save(art);
            return artMapper.toResponse(art);
        }
        throw new RuntimeException("Art not found with id: " + id);
    }


    public void deleteArt(UUID id) {
        if (!artRepository.existsById(id)) {
            throw new RuntimeException("Art not found with id: " + id);
        }
        artRepository.deleteById(id);
    }
}
