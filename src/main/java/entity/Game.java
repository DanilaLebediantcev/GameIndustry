package entity;

import javax.persistence.*;

@Entity
@Table(name = "GAME")
public class Game {

    public Game() {
    }

    public Game(String name) {
        this.name = name;
    }

    public Game(String name, Company company) {
        this.name = name;
        this.company = company;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name" , nullable = false ,unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_company")
    private Company company;

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
        if(company == null){
            return "Game{" +
                    "id=" + id +
                    ", name='" + name +
                    ", company=null" +
                    '}';
        } else {
            return "Game{" +
                    "id=" + id +
                    ", name='" + name +
                    ", company=" + company.getName() +
                    '}';
        }
    }
}
