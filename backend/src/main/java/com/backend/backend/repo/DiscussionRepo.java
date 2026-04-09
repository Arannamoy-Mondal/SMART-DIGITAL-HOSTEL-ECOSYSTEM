package com.backend.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.model.Discussion;

public interface DiscussionRepo extends JpaRepository<Integer,Discussion>{

}
