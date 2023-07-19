package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RedisHash
public class Book implements Serializable {
    @Id
    private String id;
    private String author;
    private String title;
    private Integer pageCount;
}
