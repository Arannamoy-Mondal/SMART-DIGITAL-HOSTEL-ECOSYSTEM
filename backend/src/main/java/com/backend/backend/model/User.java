package com.backend.backend.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="\"users\"")
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    
    @Column(unique = true)
    private String userName;

    @JsonIgnore
    private String password;

    // @Lob
    private byte[] profileImage;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime addedTime;

    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonManagedReference
    private Set<Role> roles = new HashSet<>();

    private String firstName;
    private String lastName;
    
    @Column(unique = true)
    private String email;

    private String contactNo;
    private String emergencyContactNo;
    private LocalDate birthDate;
    private String permanentAddress;
    private String passportId;
    
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Complaint> complaints;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Discussion> discussions;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Review> reviews;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<RoomRentInformation> roomRentInformations;
}