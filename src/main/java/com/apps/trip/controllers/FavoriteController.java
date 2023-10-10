package com.apps.trip.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("favorite")
public class FavoriteController {
    private List<String> favorites = List.of(
            "Xem phim",
            "Nghe nhạc",
            "Du lịch",
            "Khám phá văn hoá",
            "Ẩm thực",
            "Học ngoại ngữ",
            "Nghệ thuật",
            "Mua sắm",
            "Viết blog",
            "Tham gia cộng đồng",
            "Xem màn hình mặt trời lặn",
            "Lướt sóng",
            "Xả stress"
    );

    @GetMapping
    public ResponseEntity<List<String>> getAll() {
        return ResponseEntity.ok(favorites);
    }
}
