package com.manganoob.mangaservice.mapper;

import com.manganoob.mangaservice.dto.request.RatingsRequest;
import com.manganoob.mangaservice.dto.response.RatingsResponse;
import com.manganoob.mangaservice.entity.Ratings;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingsMapper {

     Ratings toEntity(RatingsRequest request);

     RatingsResponse toResponse(Ratings ratings);
}
