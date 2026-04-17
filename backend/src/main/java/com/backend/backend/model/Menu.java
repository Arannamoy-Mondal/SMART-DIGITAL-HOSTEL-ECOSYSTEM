package com.backend.backend.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"day", "meal_type_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuId;

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

    private String menuName;

    @Enumerated(EnumType.STRING)
    private Day day;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meal_type_id")
    @JsonManagedReference
    private MealType mealType;

    @ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(
        name = "menu_items_mapping", 
        joinColumns = @JoinColumn(name = "menu_id"), 
        inverseJoinColumns = @JoinColumn(name = "menu_item_id")
    )
    @JsonManagedReference
    private List<MenuItem> menuItems;

}


/* Example:  Sunday Breakfast Menu, 
Monday Lunch Menu, 
Tuesday Breakfast Menu, 
Wednesday Lunch Menu, 
Thursday Dinner Menu, 
Friday Lunch Menu, 
Saturday Lunch Menu

FKs: Day MealType
*/