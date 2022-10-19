package com.example.etsproject.core.image;

import com.example.etsproject.utils.DataResult;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    DataResult<?> uploadImage(MultipartFile file);
}
