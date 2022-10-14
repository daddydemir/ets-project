package com.example.etsproject.service.concrete;

import com.example.etsproject.entity.Person;
import com.example.etsproject.repository.PersonRepository;
import com.example.etsproject.service.abstracts.PersonService;
import com.example.etsproject.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceManager implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public DataResult<List<Person>> findAll() {
        return new SuccessDataResult<>(personRepository.findAll());
    }

    @Override
    public DataResult<Person> findById(int id) {
        var result = personRepository.findPersonById(id);
        if (result == null){
            return new ErrorDataResult<>("Kişi bulunamadı.");
        }
        return new SuccessDataResult<>(result);
    }

    @Override
    public Result add(Person person) {
        personRepository.save(person);
        return new SuccessResult("Kişi başarıyla kaydedildi.");
    }

    @Override
    public Result update(Person person) {
        personRepository.save(person);
        return new SuccessResult("Kişi başarıyla güncellendi.");
    }

    @Override
    public Result delete(int id) {
        personRepository.delete(findById(id).getData());
        return new SuccessResult("Kişi başarıyla silindi.");
    }
}
