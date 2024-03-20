package com.example.testapp.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserInput {

    private String name;
    private String email;
}
