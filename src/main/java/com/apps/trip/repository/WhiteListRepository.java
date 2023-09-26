package com.apps.trip.repository;

import com.apps.trip.models.WhiteList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhiteListRepository extends JpaRepository<WhiteList, Long> {
    @Query("select w from WhiteList w where w.user.username = ?1")
    List<WhiteList> findByUserUsername(String username);

}