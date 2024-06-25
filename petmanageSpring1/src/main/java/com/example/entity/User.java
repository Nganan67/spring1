package com.example.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("user")
public class User {
    @TableId(type= IdType.AUTO)
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String icon;

    @TableField(exist = false)
    private List<Pet> pets;
}
