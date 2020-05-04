package entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "PERSON")
@NamedQueries({
        @NamedQuery(name = "Person.getByName",query = "SELECT p FROM Person p WHERE p.name = :name"),
        @NamedQuery(name = "Person.getById",query = "SELECT p FROM Person p WHERE p.id = :id"),
        @NamedQuery(name = "Person.getAll",query = "SELECT p FROM Person p")
}
)
public class Person {
    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "boss",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Company company;

    @PreRemove
    public void deleteCompanyFromPerson(){
        this.company = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", company=" + company +
                '}';
    }
}
