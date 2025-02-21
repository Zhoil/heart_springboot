package com.example.mind.DTO.UserResDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserDTOResponse {
    @JsonProperty("users")
    private List<UserDTO> users;

    public UserDTOResponse(List<UserDTO> users) {
        this.users = users;
    }
}
