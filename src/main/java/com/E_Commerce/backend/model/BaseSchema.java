package com.E_Commerce.backend.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class BaseSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Changed from Integer to Long

//    @Column(name = "created_at", updatable = false)
//    private ZonedDateTime createdAt;
//
//    @Column(name = "updated_at")
//    private ZonedDateTime updatedAt;
//
//    @PrePersist
//    public void prePersist() {
//        this.createdAt = ZonedDateTime.now();  // Using ZonedDateTime for time with timezone
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        this.updatedAt = ZonedDateTime.now();  // Using ZonedDateTime for time with timezone
//    }
}
