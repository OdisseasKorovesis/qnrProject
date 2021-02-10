package com.odkor.myQnrProject.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "locations")
public class Location extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private Set<Department> departments;
}
