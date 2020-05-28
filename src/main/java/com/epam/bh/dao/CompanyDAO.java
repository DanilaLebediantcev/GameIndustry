package com.epam.bh.dao;


import com.epam.bh.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyDAO extends JpaRepository<Company,Long> {

}
