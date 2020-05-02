package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @Column(name = "game_id")
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_company")
    private Company company;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "GAME_GENRE", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genreList = new HashSet<>();

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

    public Set<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(Set<Genre> genreList) {
        this.genreList = genreList;
    }

    public void addGenreToGame(Genre genre) {
        genreList.add(genre);
        genre.getGameList().add(this);
    }

    public void deleteGenreFromGame(Genre genre) {
        genreList.remove(genre);
        genre.getGameList().remove(this);
    }


    @Override
    public String toString() {
        if (company == null) {
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
