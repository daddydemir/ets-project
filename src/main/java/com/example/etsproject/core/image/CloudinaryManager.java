package com.example.etsproject.core.image;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.etsproject.utils.DataResult;
import com.example.etsproject.utils.ErrorDataResult;
import com.example.etsproject.utils.SuccessDataResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public class CloudinaryManager implements ImageService{

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name" , "dpdlwo6vi",
            "api_key","416189688145921",
            "api_secret","p1D5IGMHHAT7lmbyoizOXz0GJow",
            "secure", true
    ));

    @Override
    public DataResult<?> uploadImage(MultipartFile file) {
        try{
            Map upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return new SuccessDataResult<>(upload);
        } catch (IOException e){
            e.printStackTrace();
            return new ErrorDataResult<>("Fail, try again later.");
        }
    }
}
