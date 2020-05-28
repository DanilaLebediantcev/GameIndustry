package com.epam.bh.services.serviceImpl;

import com.epam.bh.dao.GenreDAO;
import com.epam.bh.entities.Genre;
import com.epam.bh.services.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service("GenreServiceImpl")
public class GenreServiceImpl implements ServiceDAO<Genre> {
    private static final Logger log = Logger.getLogger(CompanyServiceImpl.class.getName());

    @Autowired
    private GenreDAO genreDAO;

    public GenreServiceImpl(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    @Override
    public Genre add(Genre genre) {
        Genre addGenre = this.genreDAO.save(genre);
        log.info("Genre " + genre.getName() + " was successfully added. Genre details: " + genre.toString());
        return addGenre;
    }

    @Override
    public boolean update(Genre genre) {
        Optional<Genre> byId = this.genreDAO.findById(genre.getId());
        if (!byId.isPresent()){
            return false;
        }
        Genre updateGenre = this.genreDAO.save(genre);
        log.info("Genre " + updateGenre.getName() + " was successfully updated. Genre details: " + updateGenre.toString());
        return true;
    }

    @Override
    public void delete(long id) {
        Optional<Genre> genre = this.genreDAO.findById(id);
        log.info("All genres list size: " + this.genreDAO.findAll().size());
        this.genreDAO.deleteById(id);
        log.info("Genre " + genre.get().getName() + " was successfully deleted. After removing the size of the list of all genres: " + this.genreDAO.findAll().size());
    }

    @Override
    public Genre getById(long id) {
        Optional<Genre> genre = this.genreDAO.findById(id);
        log.info("Genre with id [" + id + "] details: " + genre.get().toString());
        return genre.get();
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> genreList = this.genreDAO.findAll();
        log.info("All genre list size: " + genreList.size());
        return genreList;
    }
}
