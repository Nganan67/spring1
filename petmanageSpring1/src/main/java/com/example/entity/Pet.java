package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("pet")
public class Pet {
    @TableId(type= IdType.AUTO)
    private int id;
    private String petname;
    private String breed;
    private String type;
    private String image;
    private int age;

    @TableField(exist = false)
    private User user;

}
