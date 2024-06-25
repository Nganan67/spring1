package com.example.controller;


import com.example.DTO.PetSearchDTO;
import com.example.entity.Pet;
import com.example.mapper.PetMapper;
import com.example.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
@CrossOrigin(origins = "http://localhost:8080")
public class PetController {
    @Autowired
    private PetService petService;
    @GetMapping("/findAll")
    public ResponseEntity<List<Pet>> findAllPets() {
        List<Pet> pets = petService.findAllPets();
        return ResponseEntity.ok(pets);
    }
    // 搜索宠物的接口
    @GetMapping("/search")
    public ResponseEntity<List<Pet>> searchPets(
            @RequestParam(value = "petname", required = false) String petname,
            @RequestParam(value = "breed", required = false) String breed) {
        // 创建 PetSearchDTO 对象
        PetSearchDTO searchDTO = new PetSearchDTO();
        searchDTO.setPetname(petname);
        searchDTO.setBreed(breed);
        List<Pet> pets = petService.searchPets(searchDTO);
        return ResponseEntity.ok(pets);
    }

    @Autowired
    private PetMapper petMapper;

    @GetMapping("/list")
    public List<Pet> getPetList() {
        return petMapper.selectPetList();
    }


    @PostMapping("/add")
    public ResponseEntity<?> addPet(@RequestBody Pet pet) {
        petService.addPet(pet);
        return ResponseEntity.ok().build();
    }

    // PUT请求用于更新宠物信息
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@PathVariable("id") int id, @RequestBody Pet petDetails) {
        Pet pet = petMapper.selectById(id);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        pet.setPetname(petDetails.getPetname());
        pet.setBreed(petDetails.getBreed());
        pet.setType(petDetails.getType());
        pet.setAge(petDetails.getAge());
        // 如果有上传新图片，更新图片路径
        if (petDetails.getImage() != null) {
            pet.setImage(petDetails.getImage());
        }
        int result = petMapper.updateById(pet);
        if (result > 0) {
            return ResponseEntity.ok("更新成功");
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable int id) {
        petService.deletePet(id);
        return ResponseEntity.ok().build();
    }
}
