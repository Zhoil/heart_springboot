package com.example.mind.DTO;

import com.example.mind.enumtion.Gender;
import com.example.mind.enumtion.Role;
import com.fasterxml.jackson.annotation.JsonProperty;


public class LoginResponse {
    @JsonProperty("testeduser_id")
    private String testeduser_id;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("role")
    private Role role;
    @JsonProperty
    private Gender gender;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("id_card")
    private String id_card;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("success")
    private boolean success;

    public LoginResponse(String testeduser_id,String name,Role role,Gender gender,Integer age,String id_card,String phone,boolean success) {
        this.testeduser_id = testeduser_id;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.id_card = id_card;
        this.phone = phone;
        this.success = success;
    }

    public LoginResponse(String id,String name,Role role,Boolean success) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.success = success;
    }

    public LoginResponse(int code,String message) {
    }

    public static LoginResponse error() {
        return new LoginResponse(404,"记录不存在");
    }
    public static LoginResponse pass_error() {
        return new LoginResponse(400,"密码错误");
    }

}
