package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "COMPANY")
@NamedQueries({
        @NamedQuery(name = "Company.getByName",query = "SELECT g FROM Company g WHERE g.name = :name"),
        @NamedQuery(name = "Company.getById",query = "SELECT g FROM Company g WHERE g.id = :id"),
        @NamedQuery(name = "Company.getAll",query = "SELECT g FROM Company g")
})
public class Company {

    public Company() {
    }

    public Company(String name, int numberOfEmployees, int profit) {
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
        this.profit = profit;
    }

    public Company(String name, int numberOfEmployees, int profit, Person boss) {
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
        this.profit = profit;
        this.boss = boss;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id")
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "number_of_emplyees")
    private int numberOfEmployees;

    @Column(name = "profit")
    private int profit;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "boss_id")
    private Person boss;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game> gamesList = new ArrayList<>();


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

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public Person getBoss() {
        return boss;
    }

    public void setBoss(Person person) {
        if (person == null) {
            if (this.boss != null) {
                this.boss.setCompany(null);
            }
        } else {
            person.setCompany(this);
        }

        this.boss = person;
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<Game> games) {
        this.gamesList = games;
    }

    public void addGameToGamesList(Game game) {
        gamesList.add(game);
        game.setCompany(this);
    }

    public void deleteGameFromGamesList(Game game) {
        gamesList.remove(game);
        game.setCompany(null);
    }

    @Override
    public String toString() {
        if (boss != null) {
            return "Company{" +
                    "name='" + name + '\'' +
                    ", numberOfEmployees=" + numberOfEmployees +
                    ", profit=" + profit +
                    ", boss=" + boss.getName() +
                    '}';
        } else {
            return "Company{" +
                    "name='" + name + '\'' +
                    ", numberOfEmployees=" + numberOfEmployees +
                    ", profit=" + profit +
                    '}';
        }
    }
}
