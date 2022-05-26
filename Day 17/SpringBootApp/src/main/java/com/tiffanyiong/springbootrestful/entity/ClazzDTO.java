package com.tiffanyiong.springbootrestful.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzDTO {
    private Long class_id;
    private String class_name;

    public ClazzDTO(Clazz clazz) {
        this.class_id = clazz.getClass_id();
        this.class_name = clazz.getClass_name();
    }



}
