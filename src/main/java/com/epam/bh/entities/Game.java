package com.epam.bh.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GAME")
@NamedQueries({
        @NamedQuery(name = "Game.getByName",query = "SELECT g FROM Game g WHERE g.name = :name"),
        @NamedQuery(name = "Game.getById",query = "SELECT g FROM Game g WHERE g.id = :id"),
        @NamedQuery(name = "Game.getAll",query = "SELECT g FROM Game g")
})
@EqualsAndHashCode(of = {"id","name"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Game.class)
public class Game {

    public Game() {
    }

    public Game(String name, Company company) {
        this.name = name;
        this.company = company;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_company")
    private Company company;

    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "GAME_GENRE", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genreList = new ArrayList<>();

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
