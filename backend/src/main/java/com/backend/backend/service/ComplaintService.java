package com.backend.backend.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.dto.ComplaintRequest;
import com.backend.backend.model.Complaint;
import com.backend.backend.model.ComplaintStatus;
import com.backend.backend.model.Room;
import com.backend.backend.model.User;
import com.backend.backend.repo.ComplaintRepo;
import com.backend.backend.repo.ComplaintStatusRepo;
import com.backend.backend.repo.RoomRepo;
import com.backend.backend.repo.UserRepo;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepo complaintRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ComplaintStatusRepo complaintStatusRepo;

    @Autowired
    private RoomRepo roomRepo;

    public @Nullable Object getAllComplaint() {
        return complaintRepo.findAll();
    }

    public @Nullable Object createComplaint(ComplaintRequest complaintRequest) throws Exception {
        User user = userRepo.findByUserName(complaintRequest.user()).orElseThrow(() -> new Exception("No user found"));
        Room room = roomRepo.findByRoomNo(complaintRequest.room()).orElseThrow(() -> new Exception("No room found"));
        Complaint complaint = Complaint.builder()
                .title(complaintRequest.title())
                .description(complaintRequest.description())
                .user(user)
                .room(room)
                .build();
        List<ComplaintStatus> complaintStatusList = complaintStatusRepo.findAll();
        for (ComplaintStatus tmp : complaintStatusList) {
            if (tmp.getStatus() == "Inprogress") {
                complaint.setComplaintStatus(tmp);
                break;
            }
        }

        return complaintRepo.save(complaint);
    }

    public @Nullable Object getComplaintByUserName(String username) throws Exception {
        User user = userRepo.findByUserName(username).orElseThrow(() -> new Exception("No user found"));
        return complaintRepo.findByUser(user);
    }

    public @Nullable Object updateComplaint(Integer id, String status) throws Exception {
        Complaint complaint = complaintRepo.findById(id).orElseThrow(() -> new Exception("No complaint found"));
        List<ComplaintStatus> complaintStatusList = complaintStatusRepo.findByStatus(status);
        System.out.println(complaint.getTitle());
        complaint.setComplaintStatus(complaintStatusList.get(0));
        return complaintRepo.save(complaint);
    }

}
