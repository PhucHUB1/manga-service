package com.manganoob.identityservice.mapper;

import com.manganoob.identityservice.dto.request.manga_req.ContentWarningRequest;
import com.manganoob.identityservice.dto.request.manga_req.FormatRequest;
import com.manganoob.identityservice.dto.request.manga_req.GenresRequest;
import com.manganoob.identityservice.dto.request.manga_req.ThemeRequest;
import com.manganoob.identityservice.dto.response.manga_res.ContentWarningResponse;
import com.manganoob.identityservice.dto.response.manga_res.FormatResponse;
import com.manganoob.identityservice.dto.response.manga_res.GenresResponse;
import com.manganoob.identityservice.dto.response.manga_res.ThemeResponse;
import com.manganoob.identityservice.entity.ContentWarning;
import com.manganoob.identityservice.entity.Format;
import com.manganoob.identityservice.entity.Genres;
import com.manganoob.identityservice.entity.Theme;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    // Mapping methods for Genres
    Genres toGenres(GenresRequest request);
    GenresResponse toGenresResponse(Genres genres);

    // Mapping methods for Format
    Format toFormat(FormatRequest request);
    FormatResponse toFormatResponse(Format format);

    // Mapping methods for Content Warning
    ContentWarning toContentWarning(ContentWarningRequest request);
    ContentWarningResponse toContentWarningResponse(ContentWarning contentWarning);

    // Mapping methods for Theme
    Theme toTheme(ThemeRequest request);
    ThemeResponse toThemeResponse(Theme theme);
}
