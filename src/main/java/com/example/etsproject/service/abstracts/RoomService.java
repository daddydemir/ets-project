package com.example.etsproject.service.abstracts;

import com.example.etsproject.entity.Room;
import com.example.etsproject.utils.DataResult;
import com.example.etsproject.utils.Result;

import java.util.List;

public interface RoomService {

    DataResult<Room> getById(int id);

    Result add(Room room);

    Result delete(int id);

    DataResult<List<Room>> search(int adult, int child);
}
