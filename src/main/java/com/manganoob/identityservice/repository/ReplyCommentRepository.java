package com.manganoob.identityservice.repository;

import com.manganoob.identityservice.entity.ReplyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReplyCommentRepository extends JpaRepository<ReplyComment, UUID> {
}
