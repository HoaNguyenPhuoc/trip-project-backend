package com.apps.trip.repository;

import com.apps.trip.models.WhiteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WhiteListRepository extends JpaRepository<WhiteList, Long> {
    @Query("select w from WhiteList w where w.username = ?1")
    List<WhiteList> findByUsername(String username);

    @Query("select w from WhiteList w where w.username = ?1 and w.tour.id = ?2")
    Optional<WhiteList> findByUsernameAndTourId(String username, Long id);
}