package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.dto.DiscussionRequest;
import com.backend.backend.service.DiscussionService;

@RestController
@RequestMapping("/discussion")
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;
    @GetMapping("/get/all")
    public ResponseEntity<?> getDiscussions(
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(discussionService.getDiscussions());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }

    @PostMapping("/create")
    public ResponseEntity<?> createDiscussion(
        @RequestBody DiscussionRequest discussionRequest
                ) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(discussionService.createDiscussion(discussionRequest));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
    
        }
}
