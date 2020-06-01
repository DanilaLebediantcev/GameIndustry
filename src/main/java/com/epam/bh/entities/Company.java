package com.epam.bh.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(of = {"id","name","numberOfEmployees","profit"})
@ToString(of = {"id","name","numberOfEmployees","profit"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Company.class)
@NamedQueries({
        @NamedQuery(name = "Company.getByName",query = "SELECT g FROM Company g WHERE g.name = :name"),
        @NamedQuery(name = "Company.getById",query = "SELECT g FROM Company g WHERE g.id = :id"),
        @NamedQuery(name = "Company.getAll",query = "SELECT g FROM Company g")
})
public class Company {
    public Company() {
    }

    public Company(Long id, String name, int numberOfEmployees, Long profit) {
        this.id = id;
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
        this.profit = profit;
    }
    public Company(String name, int numberOfEmployees, Long profit) {
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
        this.profit = profit;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "company_name", nullable = false, unique = true)
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
    @JsonIgnoreProperties("company")
    private Person boss;

    @Getter
    @Setter
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnoreProperties("company")
    private List<Game> gamesList = new ArrayList<>();

}
