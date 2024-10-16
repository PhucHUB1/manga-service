package com.manganoob.mangaservice.mapper;

import com.manganoob.mangaservice.dto.request.manga_req.ChapterRequest;
import com.manganoob.mangaservice.dto.response.manga_res.ChapterResponse;
import com.manganoob.mangaservice.entity.Chapters;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChaptersMapper {

    @Mapping(target = "page_number", source = "pageNumber")
    @Mapping(target = "release_date", source = "releaseDate")
    @Mapping(target = "manga_chapters.id", source = "mangaId")
    Chapters toChapter(ChapterRequest request);

    @Mapping(target = "pageNumber", source = "page_number")
    @Mapping(target = "manga", source = "manga_chapters")
    @Mapping(target = "chapterImages", source = "chapterImages")
    @Mapping(target = "comments", source = "chapterComments")
    ChapterResponse toChapterResponse(Chapters chapters);

    List<ChapterResponse> toChapterResponseList(List<Chapters> chaptersList);


}
