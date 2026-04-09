package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.Comment;
@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer>{

}
