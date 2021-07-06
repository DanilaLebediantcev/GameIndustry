package com.epam.bh.services.serviceImpl;

import com.epam.bh.dao.DAO;
import com.epam.bh.entities.Genre;
import com.epam.bh.services.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service("GenreServiceImpl")
public class GenreServiceImpl implements ServiceDAO<Genre> {
    private static final Logger log = Logger.getLogger(CompanyServiceImpl.class.getName());

    @Autowired
    private DAO<Genre> genreDAO;

    public GenreServiceImpl(DAO<Genre> genreDAO) {
        this.genreDAO = genreDAO;
    }

    @Override
    public void add(Genre genre) {
        this.genreDAO.add(genre);
        log.info("Genre " + genre.getName() + "was successfully added. Genre details: " + genre.toString());
    }

    @Override
    public void update(Genre genre) {
        this.genreDAO.update(genre);
        log.info("Genre " + genre.getName() + "was successfully updated. Genre details: " + genre.toString());
    }

    @Override
    public void delete(long id) {
        Genre genre = this.genreDAO.getById(id);
        log.info("All genres list size: " + this.genreDAO.getAll().size());
        this.genreDAO.deleteById(id);
        log.info("Genre " + genre.getName() + "was successfully deleted. After removing the size of the list of all genres: " + this.genreDAO.getAll().size());
    }

    @Override
    public Genre getById(long id) {
        Genre genre = this.genreDAO.getById(id);
        log.info("Genre with id [" + id + "] details: " + genre.toString());
        return genre;
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> genreList = this.genreDAO.getAll();
        log.info("All genre list size: " + genreList.size());
        return genreList;
    }
}
