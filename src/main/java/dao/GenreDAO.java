package dao;

import entity.Genre;

import java.util.List;

public interface GenreDAO {
    public void addGenre(Genre genre);
    public void updateGenre(Genre genre);
    public void deleteGenre(Genre genre);
    public Genre getGenreById(int id);
    public List<Genre> getAllGenres();
}
