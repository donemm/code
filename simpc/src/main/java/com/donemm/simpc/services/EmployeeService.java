package com.donemm.simpc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.donemm.simpc.repository.EmployeeRepository;
import com.donemm.simpc.exception.RecordNotFoundException;
import com.donemm.simpc.entity.Employee;

@Service
@Component
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
	
	public EmployeeService() {
		// TODO Auto-generated constructor stub
	}

	public Employee save(Employee employee) {
		if (checkNull(employee.getId())) {
			employee = employeeRepository.save(employee);
			return employee;
		} else {
			Optional<Employee> employeeOptional = employeeRepository.findById((long) employee.getId());

			if (employeeOptional.isPresent()) {
				Employee newEntity = employeeOptional.get();
				newEntity.setDept(employee.getDept());
				newEntity.setId(employee.getId());
				newEntity.setEname(employee.getEname());

				newEntity = employeeRepository.save(newEntity);

				return newEntity;
			} else {
				throw new RecordNotFoundException("No employee record exist for given id");
			}
		}
	}

	public Employee findById(Long Id) {
		Optional<Employee> result = employeeRepository.findById(Id);
		Employee employee = null;
		
		if (result.isPresent())
			return result.get();
		else
		    return employee;
	
	}
	
	public List<Employee> findAll() {
		List<Employee> result = (List<Employee>) employeeRepository.findAll();

		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Employee>();
		}
	}

	public void deleteById(Integer id) {
		Optional<Employee> employee = employeeRepository.findById((long) id);

		if (employee.isPresent()) {
			employeeRepository.deleteById((long) id);
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	public void deleteAll() {
		employeeRepository.deleteAll();
	}
	
	public static boolean checkNull(Integer num) {
	    return num == null || num == 0;
	}

}
