package com.donemm.simpc;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.donemm.simpc.entity.Employee;
import com.donemm.simpc.repository.EmployeeRepository;
import com.donemm.simpc.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.donemm.simpc.controller.EmployeeController;

import static org.hamcrest.CoreMatchers.is;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

@WebMvcTest(controllers = EmployeeController.class)
@AutoConfigureMockMvc
public class EmployeeControllerSteps {
	
    @Autowired
    private MockMvc mockMvc;
    
    @InjectMocks private EmployeeService employeeService;
	@Mock private EmployeeRepository employeeRepository;
 
    private Employee employee;
    
    @Given(value="a new Employee record", priority=11)
    public void createEmployee() {
    	this.employee = new Employee();
    }
    
    
    @When(value="the Employee name is <ename>", priority=10)
    public void setEname(@Named("ename") String ename) {
    	this.employee.setEname(ename);
    }
    
    @When(value="the Employee id is <id>", priority=9)
    public void setId(@Named("id") int id) {
       this.employee.setId(id); 
    }
    
    @When(value="the Employee dept is <dept>", priority=6)
    public void setDept(@Named("dept") String dname) {
    	this.employee.setDept(dname);
    }
    
    @When(value="the method is POST", priority=5)
    public void methodIsGet() {
    	
    }

    @Then(value="the returned values should match", priority=4)
    public void checkValues() throws Exception {
	
		MockitoAnnotations.initMocks(this);
		
		when(employeeRepository.save(any(Employee.class))).thenReturn(this.employee);
        
		ObjectMapper objectMapper = new ObjectMapper();
			
        ResultActions response = this.mockMvc.perform(post("http://localhost:8080/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.employee)));
        
        response.andDo(print()).
        andExpect(status().isCreated())
        .andExpect(jsonPath("$.ename",
                is(this.employee.getEname())))
        .andExpect(jsonPath("$.dname",
                is(this.employee.getDept())));
        
    }
}