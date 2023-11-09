package com.apps.trip.controllers;

import com.apps.trip.dto.CommentDto;
import com.apps.trip.payload.response.ResponseJson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.apps.trip.controllers.UserController.SUCCESS;

@RestController
@RequestMapping(name = "images")
public class ImagesController {

    @PostMapping("{tourId}")
    public ResponseEntity<ResponseJson> addComment(@PathVariable("tourId") Integer tourId, @RequestBody CommentDto commentDto) {
        commentService.addCommentToTour(tourId, commentDto);
        return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<ResponseJson> delete(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
    }
}
