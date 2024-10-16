package com.manganoob.mangaservice.service;

import com.manganoob.mangaservice.dto.request.manga_req.ContentWarningRequest;
import com.manganoob.mangaservice.dto.request.manga_req.FormatRequest;
import com.manganoob.mangaservice.dto.request.manga_req.GenresRequest;
import com.manganoob.mangaservice.dto.request.manga_req.ThemeRequest;
import com.manganoob.mangaservice.dto.response.manga_res.ContentWarningResponse;
import com.manganoob.mangaservice.dto.response.manga_res.FormatResponse;
import com.manganoob.mangaservice.dto.response.manga_res.GenresResponse;
import com.manganoob.mangaservice.dto.response.manga_res.ThemeResponse;
import com.manganoob.mangaservice.entity.ContentWarning;
import com.manganoob.mangaservice.entity.Format;
import com.manganoob.mangaservice.entity.Genres;
import com.manganoob.mangaservice.entity.Theme;
import com.manganoob.mangaservice.mapper.TagMapper;
import com.manganoob.mangaservice.repository.ContentWarningRepository;
import com.manganoob.mangaservice.repository.FormatRepository;
import com.manganoob.mangaservice.repository.GenresRepository;
import com.manganoob.mangaservice.repository.ThemeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagService {
    GenresRepository genresRepository;
    ThemeRepository themeRepository;
    FormatRepository formatRepository;
    ContentWarningRepository contentWarningRepository;
    TagMapper tagMapper;

//Genres
    @PreAuthorize("hasRole('ADMIN')")
    public GenresResponse createGenres(GenresRequest request) {
        Genres genres = tagMapper.toGenres(request);
        genres = genresRepository.save(genres);
        return tagMapper.toGenresResponse(genres);
    }

    public List<GenresResponse> getAllGenres() {
        List<Genres> genresList = genresRepository.findAll();
        return genresList.stream().map(tagMapper::toGenresResponse).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteGenres(String genres_name) {
        genresRepository.deleteById(genres_name);
    }

//Theme

    @PreAuthorize("hasRole('ADMIN')")
    public ThemeResponse createTheme(ThemeRequest request) {
        Theme theme = tagMapper.toTheme(request);
        theme = themeRepository.save(theme);
        return tagMapper.toThemeResponse(theme);
    }

    public List<ThemeResponse> getAllThemes() {
        List<Theme> themeList = themeRepository.findAll();
        return themeList.stream().map(tagMapper::toThemeResponse).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTheme(String theme_name) {
        themeRepository.deleteById(theme_name);
    }
// Format
    @PreAuthorize("hasRole('ADMIN')")
    public FormatResponse createFormat(FormatRequest request) {
        Format format = tagMapper.toFormat(request);
        format = formatRepository.save(format);
        return tagMapper.toFormatResponse(format);
}

    public List<FormatResponse> getAllFormats() {
        List<Format> formatList = formatRepository.findAll();
        return formatList.stream().map(tagMapper::toFormatResponse).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteFormat(String format_name) {
        themeRepository.deleteById(format_name);
    }
// ContentWarning
    @PreAuthorize("hasRole('ADMIN')")
    public ContentWarningResponse createContentWarning(ContentWarningRequest request) {
        ContentWarning contentWarning = tagMapper.toContentWarning(request);
        contentWarning = contentWarningRepository.save(contentWarning);
        return tagMapper.toContentWarningResponse(contentWarning);
    }

    public List<ContentWarningResponse> getAllContentWarnings() {
        List<ContentWarning> contentWarningList = contentWarningRepository.findAll();
        return contentWarningList.stream().map(tagMapper::toContentWarningResponse).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteContentWarning(String content_name) {
        themeRepository.deleteById(content_name);
    }

}
