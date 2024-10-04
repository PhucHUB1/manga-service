package com.manganoob.identityservice.mapper;

import com.manganoob.identityservice.dto.request.manga_req.MangaRequest;
import com.manganoob.identityservice.dto.response.manga_res.AuthorResponse;
import com.manganoob.identityservice.dto.response.manga_res.MangaResponse;
import com.manganoob.identityservice.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class})
public interface MangaMapper {

    // Mapping from MangaRequest to Manga entity
    @Mapping(target = "manga_author", source = "authorId", qualifiedByName = "mapAuthorById")
    @Mapping(target = "chapters", ignore = true)
    @Mapping(target = "artList", ignore = true)
    @Mapping(target = "manga_genres", source = "genreName", qualifiedByName = "mapGenres")
    @Mapping(target = "manga_format", source = "formatName", qualifiedByName = "mapFormats")
    @Mapping(target = "manga_theme", source = "themeName", qualifiedByName = "mapThemes")
    @Mapping(target = "manga_content_warning", source = "contentWarningName", qualifiedByName = "mapContentWarnings")
    Manga toManga(MangaRequest request);

    // Mapping from Manga entity to MangaResponse
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "author", source = "manga_author")
    @Mapping(target = "chapters", source = "chapters")
    @Mapping(target = "artList", source = "artList")
    @Mapping(target = "comments", source = "mangaCommentList")
    @Mapping(target = "genresList", source = "manga_genres")
    @Mapping(target = "formatList", source = "manga_format")
    @Mapping(target = "themeList", source = "manga_theme")
    @Mapping(target = "contentWarningList", source = "manga_content_warning")
    MangaResponse toMangaResponse(Manga manga);

    List<MangaResponse> toMangaResponseList(List<Manga> mangaList);

    // Custom mapping methods for converting between Set<String> and the entity sets
    @Named("mapGenres")
    default Set<Genres> mapGenres(Set<String> genreNames) {
        if (genreNames == null) {
            return new HashSet<>();
        }
        return genreNames.stream().map(name -> {
            Genres genre = new Genres();
            genre.setGenres_name(name);
            return genre;
        }).collect(Collectors.toSet());
    }

    @Named("mapFormats")
    default Set<Format> mapFormats(Set<String> formatNames) {
        if (formatNames == null) {
            return new HashSet<>();
        }
        return formatNames.stream().map(name -> {
            Format format = new Format();
            format.setFormat_name(name);
            return format;
        }).collect(Collectors.toSet());
    }

    @Named("mapThemes")
    default Set<Theme> mapThemes(Set<String> themeNames) {
        if (themeNames == null) {
            return new HashSet<>();
        }
        return themeNames.stream().map(name -> {
            Theme theme = new Theme();
            theme.setTheme_name(name);
            return theme;
        }).collect(Collectors.toSet());
    }

    @Named("mapContentWarnings")
    default Set<ContentWarning> mapContentWarnings(Set<String> contentWarningNames) {
        if (contentWarningNames == null) {
            return new HashSet<>();
        }
        return contentWarningNames.stream().map(name -> {
            ContentWarning contentWarning = new ContentWarning();
            contentWarning.setContent_name(name);
            return contentWarning;
        }).collect(Collectors.toSet());
    }
    @Named("mapAuthorById")
    default Author mapAuthorById(Long authorId) {
        // You can use a service to fetch the Author by id here if needed
        Author author = new Author();
        author.setId(authorId);  // Assuming you have a setter for ID
        return author;
    }

    // Map an Author entity to AuthorResponse
    @Named("mapAuthorToResponse")
    default AuthorResponse mapAuthorToResponse(Author author) {
        if (author == null) {
            return null;
        }
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(author.getId());
        authorResponse.setName(author.getName());
        authorResponse.setBiography(author.getBiography());
        authorResponse.setAuthorAvatar(Base64.getEncoder().encodeToString(author.getAuthorAvatar())); // Assuming authorAvatar is a byte[]
        return authorResponse;
    }
}
