package com.apps.trip.repository;

import com.apps.trip.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query("select r from Rating r where r.username = ?1")
    Optional<Rating> findByUsername(String username);

    @Query("select r from Rating r where r.tour.id = ?1")
    List<Rating> findByTourId(Long id);

    @Query("select r from Rating r where r.username = ?1 and r.tour.id = ?2")
    Optional<Rating> findByUsernameAndTourId(String username, Long id);


}