package ru.lapinlisss.olympic_api.service;

import org.springframework.web.multipart.MultipartFile;
import ru.lapinlisss.olympic_api.exception.UploadException;

public interface UploadService {

    String store(MultipartFile file) throws UploadException;

}
