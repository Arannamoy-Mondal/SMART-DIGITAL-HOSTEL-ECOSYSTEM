package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.model.Discussion;
@Repository
public interface DiscussionRepo extends JpaRepository<Discussion,Integer>{

}
