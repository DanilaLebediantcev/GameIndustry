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
@Table(name = "GENRE")
@NamedQueries({
        @NamedQuery(name = "Genre.getByName",query = "SELECT g FROM Genre g WHERE g.name = :name"),
        @NamedQuery(name = "Genre.getById",query = "SELECT g FROM Genre g WHERE g.id = :id"),
        @NamedQuery(name = "Genre.getAll",query = "SELECT g FROM Genre g")
})
@EqualsAndHashCode(of = {"id","name"})
@ToString(of = {"id","name"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Genre.class)
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "genreList", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"genreList"})
    List<Game> gameList = new ArrayList<>();

    public Genre() {
    }

}
