package com.example.testapp.entity.dto;

import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class User {

    private Long id;
    private String name;
    private String email;
}
