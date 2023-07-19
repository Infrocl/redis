package com.example.demo;

import com.example.demo.entity.Book;
import com.example.demo.entity.Student;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashSet;

@SpringBootApplication
public class RedisApplication {
	@Bean
	public RedisOperations<String, Object> redisOperations(LettuceConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RedisApplication.class, args);
		BookRepository bookRepository = context.getBean(BookRepository.class);
		StudentRepository studentRepository = context.getBean(StudentRepository.class);
		Book firstBook = new Book("b1000", "А.С. Пушкин","Евгений Онегин", 272);
		Book secondBook = new Book("b2000", "М.Ю. Лермонтов", "Герой нашего времени",224);
		bookRepository.save(new Book("b1", "Л.Н. Толстой", "Анна Каренина", 768));
		bookRepository.save(firstBook);
		bookRepository.save(secondBook);
		bookRepository.deleteById("b1");
		System.out.println(bookRepository.findAll());

		HashSet<Book> books = new HashSet<>();
		books.add(firstBook);
		books.add(secondBook);
		Student firstStudent = new Student("s1000", "К.М. Иванов", books);
		studentRepository.save(firstStudent);
		System.out.println(studentRepository.findById("s1000"));
		System.out.println(studentRepository.getBooksByStudentId("s1000"));
	}
}
