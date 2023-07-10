package com.donemm.simpc.repository;

import com.donemm.simpc.entity.Employee;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	  List<Employee> findByEnameIs(String ename);
	  
	  Optional<Employee> findById(Long Id);
}
