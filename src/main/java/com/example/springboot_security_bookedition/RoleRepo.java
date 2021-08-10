package com.example.springboot_security_bookedition;

import com.example.springboot_security_bookedition.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Long> {
}
