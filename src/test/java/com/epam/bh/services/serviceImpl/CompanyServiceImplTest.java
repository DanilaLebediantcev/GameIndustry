package com.epam.bh.services.serviceImpl;

import com.epam.bh.dao.CompanyDAO;
import com.epam.bh.entities.Company;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.epam.bh.SpringCoreApplication.class)
class CompanyServiceImplTest {
    @Autowired
    CompanyServiceImpl companyService;

    @MockBean
    CompanyDAO companyDAO;

    Company companyTest1 = new Company( 1L,"Ubisoft", 1000, 800000L);
    Company companyTest2 = new Company( 2L,"EA", 5000, 654000L);
    Company companyTest3 = new Company( 3L,"Blizzard", 4000, 46545400L);

    @Test
    void add() {
        doReturn(companyTest1).when(companyDAO).save(any(Company.class));
        Company addCompany = companyService.add(companyTest1);
        Assert.assertEquals(companyTest1,addCompany);
        Mockito.verify(companyDAO,times(1)).save(companyTest1);
    }

    @Test
    void update() {
        doReturn(Optional.of(companyTest2)).when(companyDAO).findById(anyLong());
        companyTest2.setName(companyTest2.getName()+"_Update");
        boolean methodReturnTrueIfCompanyWasSuccessfullySaved = companyService.update(companyTest2);
        Assert.assertTrue(methodReturnTrueIfCompanyWasSuccessfullySaved);
        Mockito.verify(companyDAO,times(1)).findById(companyTest2.getId());
        Mockito.verify(companyDAO,times(1)).save(companyTest2);
    }

    @Test
    void delete() {
        doNothing().when(companyDAO).deleteById(anyLong());
        doReturn(List.of(companyTest1,companyTest2,companyTest3)).when(companyDAO).findAll();
        doReturn(Optional.of(companyTest3)).when(companyDAO).findById(anyLong());
        companyService.delete(companyTest3.getId());
        Mockito.verify(companyDAO,times(1)).deleteById(companyTest3.getId());
        Mockito.verify(companyDAO,times(1)).findById(companyTest3.getId());
        Mockito.verify(companyDAO,times(2)).findAll();

    }

    @Test
    void getById() {
        doReturn(Optional.of(companyTest2)).when(companyDAO).findById(anyLong());
        Company getByIdCompany = companyService.getById(2);
        Assert.assertEquals(companyTest2,getByIdCompany);
        Mockito.verify(companyDAO,times(1)).findById(companyTest2.getId());
    }

    @Test
    void getAll() {
        doReturn(List.of(companyTest1,companyTest2,companyTest3)).when(companyDAO).findAll();
        List<Company> getAllCompanies = companyService.getAll();
        Assert.assertEquals(List.of(companyTest1,companyTest2,companyTest3),getAllCompanies);
        Mockito.verify(companyDAO,times(1)).findAll();
    }
}