package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@RedisHash
public class Student implements Serializable {
    @Id
    private String id;
    private String name;

    @Reference
    private Set<Book> books = new HashSet<Book>();

    public void addBook(Book book) {
        books.add(book);
    }
}
