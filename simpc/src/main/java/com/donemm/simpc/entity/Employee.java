package com.donemm.simpc.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//pojo->plain old java object
@Entity
@Table(name = "employee")
public class Employee {
    //components of the post
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "ename")
    private String ename;
    
    @Column(name = "dname")
    private String dname;

    public Employee(String ename, String dept) {
        this.ename = ename;
        this.dname = dept;
      }
    
    public Employee() {
		// TODO Auto-generated constructor stub
	}



    public String getDept() {
        return dname;
    }

    public void setDept(String dept) {
        this.dname = dept;
    }
//getter and setter for

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }
}
