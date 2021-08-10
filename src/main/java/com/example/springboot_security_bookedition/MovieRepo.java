package com.example.springboot_security_bookedition;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepo extends CrudRepository<Movie, Long> {
}
