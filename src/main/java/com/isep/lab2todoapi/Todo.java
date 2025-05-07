package com.isep.lab2todoapi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Future;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Todo {
    @NotBlank
    public String name;

    @Future(message = "The date must be in the future")
    private Date date;
}
