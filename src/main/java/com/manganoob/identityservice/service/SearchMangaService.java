package com.manganoob.identityservice.service;

import com.manganoob.identityservice.dto.response.manga_res.MangaResponse;
import com.manganoob.identityservice.entity.Manga;
import com.manganoob.identityservice.mapper.MangaMapper;
import com.manganoob.identityservice.repository.MangaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SearchMangaService {
    MangaMapper mangaMapper;
    MangaRepository mangaRepository;

    // Search by genres
    public List<MangaResponse> searchByGenres(Set<String> genreNames) {
        List<Manga> mangaList = mangaRepository.findByGenres(genreNames);
        return mangaList.stream()
                .map(mangaMapper::toMangaResponse)
                .collect(Collectors.toList());
    }

    // Search by format
    public List<MangaResponse> searchByFormat(Set<String> formatNames) {
        List<Manga> mangaList = mangaRepository.findByFormat(formatNames);
        return mangaList.stream()
                .map(mangaMapper::toMangaResponse)
                .collect(Collectors.toList());
    }

    // Search by theme
    public List<MangaResponse> searchByTheme(Set<String> themeNames) {
        List<Manga> mangaList = mangaRepository.findByTheme(themeNames);
        return mangaList.stream()
                .map(mangaMapper::toMangaResponse)
                .collect(Collectors.toList());
    }

    // Search by content warning
    public List<MangaResponse> searchByContentWarning(Set<String> contentWarningNames) {
        List<Manga> mangaList = mangaRepository.findByContentWarning(contentWarningNames);
        return mangaList.stream()
                .map(mangaMapper::toMangaResponse)
                .collect(Collectors.toList());
    }

    // Search by author name
    public List<MangaResponse> searchByAuthorName(String authorName) {
        List<Manga> mangaList = mangaRepository.findByAuthorName(authorName);
        return mangaList.stream()
                .map(mangaMapper::toMangaResponse)
                .collect(Collectors.toList());
    }

    // Get latest manga
    public List<MangaResponse> getLatestManga() {
        List<Manga> mangaList = mangaRepository.findLatestManga();
        return mangaList.stream()
                .map(mangaMapper::toMangaResponse)
                .collect(Collectors.toList());
    }

    // Get oldest manga
    public List<MangaResponse> getOldestManga() {
        List<Manga> mangaList = mangaRepository.findOldestManga();
        return mangaList.stream()
                .map(mangaMapper::toMangaResponse)
                .collect(Collectors.toList());
    }

    // Get newly updated manga
    public List<MangaResponse> getNewlyUpdatedManga() {
        List<Manga> mangaList = mangaRepository.findNewlyUpdatedManga();
        return mangaList.stream()
                .map(mangaMapper::toMangaResponse)
                .collect(Collectors.toList());
    }
// title a-z
    public List<MangaResponse> getAllMangaSortedByTitleAsc() {
        List<Manga> mangaList = mangaRepository.findAllByTitleAsc();
        return mangaMapper.toMangaResponseList(mangaList);
    }

// title z-a
    public List<MangaResponse> getAllMangaSortedByTitleDesc() {
        List<Manga> mangaList = mangaRepository.findAllByTitleDesc();
        return mangaMapper.toMangaResponseList(mangaList);
    }
}
