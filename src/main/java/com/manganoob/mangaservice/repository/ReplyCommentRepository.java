package com.manganoob.mangaservice.repository;

import com.manganoob.mangaservice.entity.ReplyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReplyCommentRepository extends JpaRepository<ReplyComment, UUID> {
    List<ReplyComment> findAllByCommentId(UUID commentId);
}
