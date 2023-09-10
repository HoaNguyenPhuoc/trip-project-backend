package com.apps.trip.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name = "tours")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String country;

    private String duration;

    private String type;

    private String scale;

    private String place;

    @Column(columnDefinition="TEXT")
    private String description;

    private int price;

    private float rating;

    @Column(name = "count_rating")
    private int countRating;
}
