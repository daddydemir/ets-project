package com.example.etsproject.service.concrete;

import com.example.etsproject.entity.Plane;
import com.example.etsproject.repository.PlaneRepository;
import com.example.etsproject.service.abstracts.PlaneService;
import com.example.etsproject.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PlaneServiceManager implements PlaneService {

    private final PlaneRepository planeRepository;

    @Override
    public DataResult<List<Plane>> getAllPlanes() {
        return new SuccessDataResult<>(planeRepository.findAll());
    }

    @Override
    public DataResult<Plane> getPlaneById(int id) {
        var result = planeRepository.findById(id);
        if(result == null){
            return new ErrorDataResult<>("Böyle bir firma bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public Result add(Plane plane) {
        planeRepository.save(plane);
        return new SuccessResult("Firma başarıyla kaydedildi.");
    }

    @Override
    public Result update(Plane plane) {
        planeRepository.save(plane);
        return new SuccessResult("Firma güncellendi.");
    }

    @Override
    public Result delete(int id) {
        planeRepository.delete(getPlaneById(id).getData());
        return new SuccessResult("Firma silindi.");
    }
}
