package com.apps.trip.controllers;

import com.apps.trip.dto.SearchRequest;
import com.apps.trip.dto.TourRequest;
import com.apps.trip.models.Tour;
import com.apps.trip.payload.response.ResponseJson;
import com.apps.trip.service.TourService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.apps.trip.controllers.UserController.SUCCESS;

@RestController
@RequestMapping("tours")
public class TourController {
    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @PostMapping
    public ResponseEntity<ResponseJson> save(@RequestBody TourRequest request) {
        tourService.save(request);
        return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
    }

    @PostMapping("filter")
    public ResponseEntity<Page<Tour>> getListUsers(@RequestParam("page") int page, @RequestParam("size") int size,
                                                   @RequestBody SearchRequest request) {
        return ResponseEntity.ok(tourService.findAll(PageRequest.of(page, size), request));
    }

    @GetMapping("{id}")
    public ResponseEntity<Tour> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tourService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseJson> update(@PathVariable("id") Long id, @RequestBody TourRequest request) {
        tourService.update(id, request);
        return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseJson> delete(@PathVariable("id") Long id) {
        tourService.delete(id);
        return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
    }
}
