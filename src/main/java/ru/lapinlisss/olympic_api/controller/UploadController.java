package ru.lapinlisss.olympic_api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.lapinlisss.olympic_api.exception.UploadException;
import ru.lapinlisss.olympic_api.service.UploadService;

@Slf4j
@RestController
@RequestMapping("upload")
@RequiredArgsConstructor
public class UploadController {

    private final UploadService resultService;

    @PostMapping("")
    public ResponseEntity<String> uploadData(@RequestParam("file") MultipartFile file) {
        ResponseEntity<String> response;
        try {
            response = new ResponseEntity<>(resultService.store(file), HttpStatus.OK);
        } catch (UploadException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}
