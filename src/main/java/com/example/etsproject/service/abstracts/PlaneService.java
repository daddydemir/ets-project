package com.example.etsproject.service.abstracts;

import com.example.etsproject.entity.Plane;
import com.example.etsproject.utils.DataResult;
import com.example.etsproject.utils.Result;

import java.util.Collection;
import java.util.List;

public interface PlaneService {

    DataResult<List<Plane>> getAllPlanes();

    DataResult<Plane> getPlaneById(int id);

    Result add(Plane plane);

    Result update(Plane plane);

    Result delete(int id);

}
