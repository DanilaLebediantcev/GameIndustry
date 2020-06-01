package com.epam.bh.dao;


import com.epam.bh.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreDAO extends JpaRepository<Genre,Long> {

}
