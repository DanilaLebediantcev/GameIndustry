package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "GENRE")@NamedQueries({
        @NamedQuery(name = "Genre.getByName",query = "SELECT g FROM Game g WHERE g.name = :name"),
        @NamedQuery(name = "Genre.getById",query = "SELECT g FROM Genre g WHERE g.id = :id"),
        @NamedQuery(name = "Genre.getAll",query = "SELECT g FROM Genre g")
})

public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "genre_id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "genreList", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Set<Game> gameList = new HashSet<>();

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
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

    public Set<Game> getGameList() {
        return gameList;
    }

    public void setGameList(Set<Game> gameList) {
        this.gameList = gameList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id == genre.id &&
                name.equals(genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
