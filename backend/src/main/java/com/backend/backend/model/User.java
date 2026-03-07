package com.backend.backend.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String userName;
    @NotBlank
    @NotEmpty
    @Size(max = 14,min = 4,message = "max 14 character and min 4 character")
    private String password;
    @Lob
    private byte[] profileImage;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime addedTime;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name="user_id")
        ,inverseJoinColumns = @JoinColumn(name="role_id")
    )
    @NotEmpty(message = "At least one role is required")
    private Set<Role> roles = new HashSet<>();

    @NotBlank(message = "First name Required")
    private String firstName;
    @NotBlank(message = "Last name Required")
    private String lastName;
    @Column(unique = true)
    @NotBlank(message = "Email Required")
    private String email;
    @Size(min = 13,max = 13,message = "Must be fill up with country code")
    @NotBlank(message = "Contact no required")
    private String contactNo;
    @Size(min = 13,max = 13,message = "Must be fill up with country code")
    private String emergencyContactNo;
    private LocalDate birthDate;
    private String permanentAddress;
    private String passportId;

}
