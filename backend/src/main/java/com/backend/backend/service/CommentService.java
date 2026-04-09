package com.backend.backend.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.CommentRequest;
import com.backend.backend.model.Comment;
import com.backend.backend.model.Room;
import com.backend.backend.model.User;
import com.backend.backend.repo.CommentRepo;
import com.backend.backend.repo.RoomRepo;
import com.backend.backend.repo.UserRepo;

@Service
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoomRepo roomRepo;

    public @Nullable Object getComments() throws Exception {
        try {
            List<Comment> comments=commentRepo.findAll();
            if(comments.size()>0){
                return comments;
            }
            return "No comment found";
        } catch (Exception e) {
            throw new Exception("hello");
        }
    }

    public @Nullable Object createComment(CommentRequest commentRequest) throws Exception {
        try {
            User user=userRepo.findById(commentRequest.getUserId()).orElse(null);
            Room room=roomRepo.findByRoomNo(commentRequest.getRoomNo()).orElse(null);
            if (user==null){
                throw new Exception("No user found with id "+commentRequest.getUserId());
            }
            else if (room==null){
                throw new Exception("No room found with id "+commentRequest.getRoomNo());
            }
            else{
                Comment comment=Comment.builder()
                .comment(commentRequest.getComment())
                .user(user)
                .room(room)
                .build();
                commentRepo.save(comment);
                return commentRepo.findAll();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public @Nullable Object getCommentById(Integer id) throws Exception {
        try {
            Comment comment=commentRepo.findById(id).orElse(null);
            if(comment==null){
                return "No comment found with this id "+id;
            }
           return comment;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
