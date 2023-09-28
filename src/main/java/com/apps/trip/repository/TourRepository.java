package com.apps.trip.repository;

import com.apps.trip.models.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    @Query("select t from Tour t where upper(t.name) like upper(concat('%', :name, '%'))")
    Page<Tour> findByNameContainsIgnoreCase(String name, Pageable pageable);

}