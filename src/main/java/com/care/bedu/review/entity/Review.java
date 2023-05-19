package com.care.bedu.review.entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Review {
    @Id
    private String id;
    private int number;
    private String course;
    @ElementCollection
    private List<String> contents;
    private int stars;
    private String author;

    // 생성자, getter, setter 등 필요한 코드 추가
}







