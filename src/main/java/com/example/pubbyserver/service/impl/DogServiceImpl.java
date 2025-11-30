package com.example.pubbyserver.service.impl;

import com.example.pubbyserver.dao.DogDao;
import com.example.pubbyserver.entity.Dog;
import com.example.pubbyserver.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogServiceImpl implements DogService {

    @Autowired
    private DogDao dogDao;

    @Override
    public Dog getDogById(Long id) {
        return dogDao.selectById(id);
    }

    @Override
    public List<Dog> getAllDogs() {
        return dogDao.selectAll();
    }

    @Override
    public boolean createDog(Dog dog) {
        return dogDao.insert(dog) > 0;
    }

    @Override
    public boolean updateDog(Dog dog) {
        return dogDao.update(dog) > 0;
    }

    @Override
    public boolean deleteDog(Long id) {
        return dogDao.deleteById(id) > 0;
    }
}