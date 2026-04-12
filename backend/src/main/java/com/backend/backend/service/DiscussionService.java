package com.backend.backend.service;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.DiscussionRequest;
import com.backend.backend.model.Discussion;
import com.backend.backend.model.Room;
import com.backend.backend.model.User;
import com.backend.backend.repo.DiscussionRepo;
import com.backend.backend.repo.RoomRepo;
import com.backend.backend.repo.UserRepo;

@Service
public class DiscussionService {

    @Autowired
    private DiscussionRepo discussionRepo;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private UserRepo userRepo;

    public Object getDiscussions() {
        return discussionRepo.findAll();
    }

    public @Nullable Object createDiscussion(DiscussionRequest discussionRequest) throws Exception {
        Room room = roomRepo.findByRoomNo(discussionRequest.getRoomNo()).orElse(null);
        User user = userRepo.findById(discussionRequest.getUserId()).orElse(null);
        System.out.println(user);
        if (room == null) {
            throw new Exception("Room is not found with id " + discussionRequest.getRoomNo());
        }
        if(user==null){
            throw new Exception("User is not found with id "+discussionRequest.getUserId());
        }
        Discussion discussion = Discussion.builder()
                .description(discussionRequest.getDescription())
                .room(null)
                .user(null)
                .build();
        return discussion;
    }

}
