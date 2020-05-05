//import Reposiroty.CompanyRepository;
//import Reposiroty.PersonRepository;
//import db.DBConnection;
//import db.HibernateConnection;
//import entity.Company;
//import entity.Person;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//import org.junit.jupiter.api.*;
//
//import java.util.List;
//
//
//class CompanyRepositoryTest {
//
//    //Create companies
//    Company ubisoft = new Company("Ubisoft", 10000, 700000000);
//    Company ea = new Company("Electronic Arts",17500,6000000);
//    Company valve = new Company("Valve",13200,1000000000);
//    static Company cdProjectRed = new Company("CDProjectRed",7000,4560000);
//
//    //Create Persons
//    Person ubisoftLead = new Person("ubiLead");
//    Person eaLead = new Person("eaLead");
//    static Person cdLead = new Person("cdLead");
//
//    //Create repositories
//    CompanyRepository companyRepository = new CompanyRepository();
//    PersonRepository personRepository = new PersonRepository();
//
//
//    @BeforeAll
//    static void prerequisites(){
//        DBConnection dbConnection = new DBConnection();
//        dbConnection.getConnection();
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(cdProjectRed);
//        session.save(cdLead);
//        transaction.commit();
//        transaction = session.beginTransaction();
//        cdProjectRed.setBoss(cdLead);
//        session.save(cdProjectRed);
//        transaction.commit();
//        session.close();
//
//    }
//
////    @AfterAll
////    void tearDown() {
////        HibernateConnection.shutDown();
////    }
//
//    @Test
//    void add() {
//        int size = companyRepository.getAll().size();
//        companyRepository.add(ubisoft);
//        Assertions.assertEquals(size+1,companyRepository.getAll().size());
//    }
//
//    @Test
//    void update() {
//        companyRepository.add(ea);
//        //Проверили, что к добавленной компании нет босса
//        Assertions.assertEquals(null,ea.getBoss());
//        //Добавили босса
//        personRepository.add(eaLead);
//        int eaBoss_id = eaLead.getId();
//        ea.setBoss(eaLead);
//        companyRepository.update(ea);
//
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Company companyFromDB = session.createNamedQuery("Company.getByName",Company.class).setParameter("name","Electronic Arts").getSingleResult();
//        //Проверили, что у компании есть босс
//        Assertions.assertEquals(eaLead.getId(),companyFromDB.getBoss().getId());
//
//        //Проверяем, что и боссу проставилась компания
//        Person verifyPerson = session.createNamedQuery("Person.getById",Person.class).setParameter("id",eaBoss_id).getSingleResult();
//        Assertions.assertEquals("Electronic Arts",verifyPerson.getCompany().getName());
//        session.close();
//    }
//
//    @Test
//    void getById() {
//        companyRepository.add(valve);
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Company company = session.find(Company.class,valve.getId());
//        Assertions.assertEquals(company.getId(),valve.getId());
//    }
//
//    @Test
//    void getAll() {
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        List<Company> companyList = session.createNamedQuery("Company.getAll",Company.class).getResultList();
//        Assertions.assertEquals(companyList.size(),companyRepository.getAll().size());
//        session.close();
//    }
//
//    @Test
//    void delete() {
//
//        //Начинаем проверку
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Query sql = session.createNamedQuery("Company.getByName").setParameter("name","CDProjectRed");
//        Company company = (Company) sql.getSingleResult();
//        int boss_id = company.getBoss().getId();
//        Assertions.assertNotNull(company);
//        companyRepository.delete(cdProjectRed);
//        sql = session.createNamedQuery("Company.getByName").setParameter("name","CDProjectRed");
//        List<Company> companyList = sql.getResultList();
//        //Проверяем, что компания удалена
//        Assertions.assertEquals(0,companyList.size());
//
//        //А также удалился и ее босс
//        List<Person> personList =  session.createNamedQuery("Person.getById",Person.class).setParameter("id", boss_id).getResultList();
//        Assertions.assertEquals(0,personList.size());
//    }
//}