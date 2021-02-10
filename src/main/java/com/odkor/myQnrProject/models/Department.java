package com.odkor.myQnrProject.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Employee> employees;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
