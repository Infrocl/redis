package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Student;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public class StudentRepository {
    private final RedisOperations<String, Object> ops;

    public StudentRepository(RedisOperations<String, Object> ops) {
        this.ops = ops;
    }

    public Student findById(String id) {
        return (Student) ops.opsForValue().get(id);
    }


    public Student save(Student student) {
        ops.opsForValue().set(student.getId(), student);
        return student;
    }

    public void deleteById(String id) {
        ops.delete(id);
        System.out.println("deleted");
    }

    public Set<Book> getBooksByStudentId(String id) {
        return ((Student) ops.opsForValue().get(id)).getBooks();
    }
}
