package com.apps.trip.controllers;

import com.apps.trip.models.Tour;
import com.apps.trip.payload.response.ResponseJson;
import com.apps.trip.service.WhiteListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.apps.trip.controllers.UserController.SUCCESS;

@RestController
@RequestMapping("white-list")
public class WhiteListController {
    private final WhiteListService whiteListService;

    public WhiteListController(WhiteListService whiteListService) {
        this.whiteListService = whiteListService;
    }

    @PostMapping("{tourId}")
    public ResponseEntity<ResponseJson> save(@PathVariable("tourId") int tourId) {
        boolean isSuccess = whiteListService.addWhiteList(tourId);
        if (isSuccess) {
            return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
        }
        return ResponseEntity.ok(new ResponseJson(400, "User already add this tour"));
    }

    @GetMapping
    public ResponseEntity<List<Tour>> findByUsername() {
        return ResponseEntity.ok(whiteListService.findByUsername());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseJson> delete(@PathVariable("id") long id) {
        boolean b = whiteListService.deleteById(id);
        if (b){
            return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
        }
        return ResponseEntity.ok(new ResponseJson(400, "Fails"));

    }
}
