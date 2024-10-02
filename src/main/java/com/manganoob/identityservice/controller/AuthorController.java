package com.manganoob.identityservice.controller;

import com.manganoob.identityservice.dto.request.ApiResponse;
import com.manganoob.identityservice.dto.request.manga_req.AuthorRequest;
import com.manganoob.identityservice.dto.response.manga_res.AuthorResponse;
import com.manganoob.identityservice.service.AuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorController {

    AuthorService authorService;

    @PostMapping("/create")
    ApiResponse<AuthorResponse> create(@RequestBody AuthorRequest request) throws IOException {
        return ApiResponse.<AuthorResponse>builder()
                .result(authorService.createAuthor(request))
                .build();
    }

    @GetMapping("/allAuthor")
    ApiResponse<List<AuthorResponse>> getAllAuthor() {
        return ApiResponse.<List<AuthorResponse>>builder()
                .result(authorService.getAllAuthors())
                .build();
    }

    @GetMapping("get/{author_id}")
    public ApiResponse<AuthorResponse> getAuthorById(@PathVariable Long author_id) {
            AuthorResponse authorResponse = authorService.getAuthorById(author_id);
            return ApiResponse.<AuthorResponse>builder()
                    .result(authorResponse)
                    .build();
    }

    @PutMapping("update/{author_id}")
    public ApiResponse<AuthorResponse> updateAuthor(@PathVariable Long author_id, @RequestBody AuthorRequest request) throws IOException {
        AuthorResponse authorResponse = authorService.updateAuthor(author_id, request);
        return ApiResponse.<AuthorResponse>builder()
                .result(authorResponse)
                .build();
    }
    @DeleteMapping("/delete/{author_id}")
    ApiResponse<Void> delete(@PathVariable Long author_id) {
        authorService.deleteAuthor(author_id);
        return ApiResponse.<Void>builder().build();
    }
}