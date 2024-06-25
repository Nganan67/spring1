package com.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Pet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PetMapper extends BaseMapper<Pet> {
    //通过宠物名称查找
    @Select("select * from pet where name=#{name}")
    Pet selectByPetName(String petname);
    //通过种类进行查找
    @Select("select * from pet where breed=#{breed}")
    Pet selectByBreed(String breed);
    @Select("select * from pet where type=#{type}")
    Pet selectByType(String type);
    @Update("UPDATE pet SET image = #{image} WHERE id = #{id}")
    int updateById(Pet pet);
    @Select("SELECT * FROM pet WHERE petname = #{petname} AND breed = #{breed}")
    Pet selectPetByPetnameAndBreed(@Param("petname") String petname, @Param("breed") String breed);
    @Select("SELECT * FROM pet")
    List<Pet> selectPetList();
    void deleteById(int id);
}
