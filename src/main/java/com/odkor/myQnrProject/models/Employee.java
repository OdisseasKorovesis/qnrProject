package com.odkor.myQnrProject.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "job")
    private String job;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "salary")
    private double salary;

    @Column(name = "commissions")
    private double commissions;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
