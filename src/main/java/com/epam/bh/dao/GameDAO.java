package com.epam.bh.dao;


import com.epam.bh.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameDAO extends JpaRepository<Game,Long> {

}
