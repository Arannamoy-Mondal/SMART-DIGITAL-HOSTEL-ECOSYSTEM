package com.backend.backend.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomId;
    
    @Column(unique = true)
    private int roomNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_room_type")
    @JsonManagedReference
    private RoomType roomType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_floor")
    @JsonManagedReference
    private Floor floor;

    private float perDayRentFee;
    
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    @LastModifiedBy
    private String updatedBy;

    @OneToMany(mappedBy = "room")
    @JsonBackReference
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "room")
    @JsonBackReference
    private List<Comment> comments;

    @OneToMany(mappedBy = "room")
    @JsonBackReference
    private List<Discussion> discussions;

    @OneToMany(mappedBy = "room")
    @JsonBackReference
    private List<Complaint> complaints;

    @OneToMany(mappedBy = "room")
    @JsonBackReference
    private List<Review> reviews;

    @OneToMany(mappedBy = "room")
    @JsonBackReference
    private List<RoomRentInformation> roomRentInformations;
}