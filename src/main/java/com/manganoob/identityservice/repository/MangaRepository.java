package com.manganoob.identityservice.repository;

import com.manganoob.identityservice.entity.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MangaRepository extends JpaRepository<Manga, UUID> {

    @Query("SELECT m FROM Manga m JOIN m.manga_genres g WHERE g.genres_name IN :genreNames")
    List<Manga> findByGenres(@Param("genreNames") Set<String> genreNames);

    @Query("SELECT m FROM Manga m JOIN m.manga_format f WHERE f.format_name IN :formatNames")
    List<Manga> findByFormat(@Param("formatNames") Set<String> formatNames);

    @Query("SELECT m FROM Manga m JOIN m.manga_theme t WHERE t.theme_name IN :themeNames")
    List<Manga> findByTheme(@Param("themeNames") Set<String> themeNames);

    @Query("SELECT m FROM Manga m JOIN m.manga_content_warning cw WHERE cw.content_name IN :contentWarningNames")
    List<Manga> findByContentWarning(@Param("contentWarningNames") Set<String> contentWarningNames);

    @Query("SELECT m FROM Manga m WHERE m.manga_author.name = :authorName")
    List<Manga> findByAuthorName(@Param("authorName") String authorName);

    @Query("SELECT m FROM Manga m ORDER BY m.createdAt DESC")
    List<Manga> findLatestManga();

    @Query("SELECT m FROM Manga m ORDER BY m.createdAt ASC")
    List<Manga> findOldestManga();

    @Query("SELECT m FROM Manga m ORDER BY m.updatedAt DESC")
    List<Manga> findNewlyUpdatedManga();

    @Query("SELECT m FROM Manga m ORDER BY m.title ASC")
    List<Manga> findAllByTitleAsc();

    @Query("SELECT m FROM Manga m ORDER BY m.title DESC")
    List<Manga> findAllByTitleDesc();
}
