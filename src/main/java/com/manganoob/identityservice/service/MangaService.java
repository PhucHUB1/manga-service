package com.manganoob.identityservice.service;

import com.manganoob.identityservice.dto.request.manga_req.MangaRequest;
import com.manganoob.identityservice.dto.response.manga_res.MangaResponse;
import com.manganoob.identityservice.entity.Author;
import com.manganoob.identityservice.entity.Manga;
import com.manganoob.identityservice.mapper.MangaMapper;
import com.manganoob.identityservice.repository.AuthorRepository;
import com.manganoob.identityservice.repository.MangaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MangaService {
    MangaRepository mangaRepository;
    MangaMapper mangaMapper;
    AuthorRepository authorRepository;

    public MangaResponse createManga(MangaRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + request.getAuthorId()));

        Manga manga = mangaMapper.toManga(request);
        manga.setManga_author(author); // Set the author

        Manga savedManga = mangaRepository.save(manga);

        return mangaMapper.toMangaResponse(savedManga);
    }

    public List<MangaResponse> getAllManga() {
        List<Manga> mangaList = mangaRepository.findAll();
        return mangaMapper.toMangaResponseList(mangaList);
    }

    public MangaResponse getMangaById(UUID id) {
        Manga manga = mangaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manga not found with id: " + id));

        return mangaMapper.toMangaResponse(manga);
    }

    public void deleteManga(UUID id) {
        Manga manga = mangaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manga not found with id: " + id));

        mangaRepository.delete(manga);
    }
}
