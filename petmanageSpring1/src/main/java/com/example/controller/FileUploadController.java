package com.example.controller;

import com.example.entity.Pet;
import com.example.mapper.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class FileUploadController {
    @Autowired
    private PetMapper petMapper;

    @PostMapping("/uploadUserImg")
    public String uploadUserIcon(@RequestParam("username") String username, @RequestParam("image") MultipartFile image, HttpServletRequest request) throws IOException {
        System.out.println(username);
        System.out.println(image.getOriginalFilename());
        System.out.println(image.getContentType());

        String path = "D:/petmanageSpring/userImg/";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = path + image.getOriginalFilename();
        File dest = new File(filePath);

        image.transferTo(dest);

//        UserImage userImage = new UserImage();
//        userImage.setUsername(username);
//        userImage.setImage(filePath);
//        userImageMapper.insert(userImage);

        return "上传成功";
    }

    @PostMapping("/uploadPetImg")
    public String uploadPetIcon(@RequestParam("petname") String petname, @RequestParam("breed") String breed, @RequestParam("image") MultipartFile image, HttpServletRequest request) throws IOException {
        System.out.println(petname);
        System.out.println(image.getOriginalFilename());
        System.out.println(image.getContentType());

        String path = "D:/petmanageSpring/petImg/";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = path + image.getOriginalFilename();
        File dest = new File(filePath);

        image.transferTo(dest);

        Pet pet = new Pet();
        pet = petMapper.selectPetByPetnameAndBreed(petname, breed);
        if (pet != null) {
            pet.setImage(filePath);
            petMapper.updateById(pet);
        } else {
            // 插入新宠物
            pet = new Pet();
            pet.setPetname(petname);
            pet.setBreed(breed);
            pet.setImage(filePath);
            petMapper.insert(pet);
        }
        return "上传成功";
    }

    public void saveFile(MultipartFile photo, String path) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(path + photo.getOriginalFilename());
        photo.transferTo(file);
    }
}
