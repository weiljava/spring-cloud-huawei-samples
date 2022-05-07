package com.huaweicloud.samples.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    private Long id;
    private String name;
    private int age;
    private int score;

    @Override
    public String toString() {
        return "I am " + name + ',' + age + "years old, score is " + score;
    }
}
