package ru.lapinlisss.olympic_api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.lapinlisss.olympic_api.service.UploadService;

@Slf4j
@RestController
@RequestMapping("upload")
@RequiredArgsConstructor
public class UploadController {

    private final UploadService resultService;

    @GetMapping("")
    public ResponseEntity<String> getAllDecks() {
        log.info("Income request to get test");
        return ResponseEntity.ok().body("Hello!");
    }

    @PostMapping("")
    public ResponseEntity<String> uploadData(@RequestParam("file") MultipartFile file) {
        resultService.store(file);
        return ResponseEntity.ok().body("");
    }

}
