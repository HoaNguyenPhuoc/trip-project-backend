package com.apps.trip.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String username;

    @Column(name = "img_preview", columnDefinition="LONGTEXT")
    private String imgPreview;

    private String shortDescription;

    @Column(name = "description", columnDefinition="LONGTEXT")
    private String description;

    private boolean status;

    @Column(name = "created_time")
    private String createdTime;
}
