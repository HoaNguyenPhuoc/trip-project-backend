package com.apps.trip.controllers;

import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("favorite")
public class FavoriteController {
    private List<String> favorites = List.of(
            "Đọc sách",
            "Xem phim",
            "Nghe nhạc",
            "Du lịch",
            "Chơi thể thao",
            "Nấu ăn",
            "Trồng cây",
            "Xem truyền hình",
            "Học ngoại ngữ",
            "Đi dạo",
            "Nghệ thuật",
            "Mua sắm",
            "Viết blog",
            "Tham gia cộng đồng",
            "Làm việc tình nguyện",
            "Xem màn hình mặt trời lặn",
            "Tập yoga",
            "Lướt sóng",
            "Chơi video game",
            "Sưu tập tiền xu"
    );

    @GetMapping
    public ResponseEntity<List<String>> getAll() {
        return ResponseEntity.ok(favorites);
    }
}
