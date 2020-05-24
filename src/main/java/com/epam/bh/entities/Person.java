package com.epam.bh.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "PERSON")
@NamedQueries({
        @NamedQuery(name = "Person.getByName",query = "SELECT p FROM Person p WHERE p.name = :name"),
        @NamedQuery(name = "Person.getById",query = "SELECT p FROM Person p WHERE p.id = :id"),
        @NamedQuery(name = "Person.getAll",query = "SELECT p FROM Person p")
})
@EqualsAndHashCode(of = {"id","name"})
@ToString(of = {"id","name"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Person.class)
public class Person {
    public Person() {
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "person_name")
    @Getter
    @Setter
    private String name;

    @OneToOne(mappedBy = "boss", cascade = CascadeType.PERSIST)
    @Getter
    @Setter
    @JsonIgnoreProperties("boss")
    private Company company;

    @PreRemove
    public void deleteCompanyFromPerson(){
        this.company = null;
    }

}
