package com.epam.bh.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(of = {"id","name","numberOfEmployees","profit"})
@ToString(of = {"id","name","numberOfEmployees","profit"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Company.class)
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Getter
    @Setter
    @Column(name = "number_of_emplyees")
    private int numberOfEmployees;

    @Getter
    @Setter
    @Column(name = "profit")
    private Long profit;

    @Getter
    @Setter
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "boss_id")
    private Person boss;

    @Getter
    @Setter
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game> gamesList = new ArrayList<>();

    public Company() {
    }

}
