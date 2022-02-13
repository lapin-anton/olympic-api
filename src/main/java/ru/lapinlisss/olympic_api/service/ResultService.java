package ru.lapinlisss.olympic_api.service;

import org.springframework.web.multipart.MultipartFile;
import ru.lapinlisss.olympic_api.model.Result;

public interface ResultService {

    void store(MultipartFile file);

}
