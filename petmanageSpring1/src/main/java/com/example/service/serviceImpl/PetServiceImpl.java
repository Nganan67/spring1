package com.example.service.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.DTO.PetSearchDTO;
import com.example.entity.Pet;
import com.example.mapper.PetMapper;
import com.example.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private PetMapper petMapper;
    @Override
    public List<Pet> searchPets(PetSearchDTO searchDTO) {
        // MyBatis-Plus的条件构造器
        QueryWrapper<Pet> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchDTO.getPetname())) {//MYBATISPLUS一个工具，用途：……
            queryWrapper.like("petname", searchDTO.getPetname());
        }
        if (StringUtils.isNotBlank(searchDTO.getBreed())) {
            queryWrapper.like("breed", searchDTO.getBreed());
        }
        return petMapper.selectList(queryWrapper);
    }

    @Override
    public List<Pet> findAllPets() {
        return petMapper.selectList(null);
    }

    // PetServiceImpl中添加updatePet方法的实现
    @Override
    public void updatePet(int id, Pet petDetails) {
        petMapper.updateById(petDetails);
    }
    @Override
    public void addPet(Pet pet) {
        petMapper.insert(pet);
    }
    @Override
    public void deletePet(int id) {
        petMapper.deleteById(id);
    }

}
