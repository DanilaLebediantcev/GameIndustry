package entity;

import javax.persistence.*;


@Entity
@Table(name = "PERSON")
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
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", company=" + company +
                '}';
    }
}
