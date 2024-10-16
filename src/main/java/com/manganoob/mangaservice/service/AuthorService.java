package com.manganoob.mangaservice.service;

import com.manganoob.mangaservice.dto.request.manga_req.AuthorRequest;
import com.manganoob.mangaservice.dto.response.manga_res.AuthorResponse;
import com.manganoob.mangaservice.entity.Author;
import com.manganoob.mangaservice.mapper.AuthorMapper;
import com.manganoob.mangaservice.repository.AuthorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorService {
    AuthorRepository authorRepository;
    AuthorMapper authorMapper;

    public AuthorResponse createAuthor(AuthorRequest authorRequest) throws IOException {
        Author author = authorMapper.toEntity(authorRequest);

        if (authorRequest.getAuthorAvatar() != null && !authorRequest.getAuthorAvatar().isEmpty()) {
            author.setAuthorAvatar(authorRequest.getAuthorAvatar().getBytes());
            author.setAuthorAvatarName(authorRequest.getAuthorAvatar().getOriginalFilename());
        }

        author = authorRepository.save(author);
        return authorMapper.toResponse(author);
    }

    public List<AuthorResponse> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::toResponse)
                .toList();
    }

    public AuthorResponse getAuthorById(Long id) {
        Optional<Author> authorOpt = authorRepository.findById(id);
        if (authorOpt.isPresent()) {
            return authorMapper.toResponse(authorOpt.get());
        }
        throw new RuntimeException("Author not found with id: " + id);
    }

    public AuthorResponse updateAuthor(Long id, AuthorRequest request) throws IOException {
        Optional<Author> authorOpt = authorRepository.findById(id);
        if (authorOpt.isPresent()) {
            Author author = authorOpt.get();
            author.setName(request.getName());
            author.setBiography(request.getBiography());

            if (request.getAuthorAvatar() != null && !request.getAuthorAvatar().isEmpty()) {
                author.setAuthorAvatar(request.getAuthorAvatar().getBytes());
                author.setAuthorAvatarName(request.getAuthorAvatar().getOriginalFilename());
            }

            author.setPixiv_link(request.getPixiv_link());
            author.setSkeb_link(request.getSkeb_link());
            author.setTumblr_link(request.getTumblr_link());
            author.setYoutube_link(request.getYoutube_link());
            author.setWeibo_link(request.getWeibo_link());
            author.setNaver_link(request.getNaver_link());
            author.setWebsite_link(request.getWebsite_link());

            author = authorRepository.save(author);
            return authorMapper.toResponse(author);
        }
        throw new RuntimeException("Author not found with id: " + id);
    }

    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}