package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.model.Comment;

public interface CommentRepo extends JpaRepository<Integer,Comment>{

}
