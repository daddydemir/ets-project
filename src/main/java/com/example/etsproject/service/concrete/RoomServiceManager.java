package com.example.etsproject.service.concrete;

import com.example.etsproject.entity.Room;
import com.example.etsproject.repository.RoomRepository;
import com.example.etsproject.service.abstracts.RoomService;
import com.example.etsproject.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceManager implements RoomService{

    private final RoomRepository roomRepository;

    @Override
    public DataResult<Room> getById(int id) {
        var result = roomRepository.findById(id);
        if (result == null){
            return new ErrorDataResult<>("Oda bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public Result add(Room room) {
        // TODO: 11/10/2022 otelin kapasitesi artmalı ++1
        roomRepository.save(room);
        return new SuccessResult("Oda eklendi.");
    }

    @Override
    public Result delete(int id) {
        // TODO: 11/10/2022 otelin oda kapasitesi 1 azaltılmalı --1
        roomRepository.delete(getById(id).getData());
        return new SuccessResult("Oda silindi.");
    }

    @Override
    public DataResult<List<Room>> search(int adult, int child) {
        var result = roomRepository.search(adult,child);
        if(result.isEmpty()){
            return new ErrorDataResult<>(null,"Oda bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }
}
