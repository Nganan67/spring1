package com.example.service;


import com.example.DTO.PetSearchDTO;
import com.example.entity.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetService {
    List<Pet> searchPets(PetSearchDTO searchDTO);
    List<Pet> findAllPets();
    void updatePet(int id, Pet petDetails);
    void addPet(Pet pet);
    void deletePet(int id);
}
