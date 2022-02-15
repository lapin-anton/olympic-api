package ru.lapinlisss.olympic_api.service;

import org.springframework.web.multipart.MultipartFile;
import ru.lapinlisss.olympic_api.exception.UploadException;
import ru.lapinlisss.olympic_api.model.Result;

public interface UploadService {

    String store(MultipartFile file) throws UploadException;

}
