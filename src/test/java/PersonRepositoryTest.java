//
//import Reposiroty.PersonRepository;
//import db.DBConnection;
//import db.HibernateConnection;
//import entity.Company;
//import entity.Person;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//class PersonRepositoryTest {
//    //Create companies
//    static Company company1 = new Company("Тестовая1", 10000, 700000000);
//    static Company company2 = new Company("Тестовая2", 17500, 6000000);
//
//    //Create Persons
//    static Person person1 = new Person("Иванов Иван");
//    static Person person2 = new Person("Семенов Семен");
//    static Person person3 = new Person("Романов Роман");
//
//
//    //Create repositories
//    PersonRepository personRepository = new PersonRepository();
//
//
//    @BeforeAll
//    static void setUp() {
//        DBConnection dbConnection = new DBConnection();
//        dbConnection.getConnection();
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(person1);
//        session.save(person3);
//        session.save(company1);
//        transaction.commit();
//        transaction = session.beginTransaction();
//        company1.setBoss(person3);
//        company2.setBoss(person1);
//        session.save(company1);
//        session.save(company2);
//        transaction.commit();
//        session.close();
//    }
//
////    @AfterAll
////    static void tearDown(){
////        HibernateConnection.shutDown();
////    }
//
//    @Test
//    void add() {
//        int size = personRepository.getAll().size();
//        personRepository.add(person2);
//        Assertions.assertEquals(size + 1, personRepository.getAll().size());
//    }
//
//    @Test
//    void update() {
//        Assertions.assertEquals("Иванов Иван", person1.getName());
//        int id = person1.getId();
//        person1.setName("Петров Петр");
//        personRepository.update(person1);
//
//
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Person verifyPerson = session.createNamedQuery("Person.getById", Person.class).
//                setParameter("id", id).getSingleResult();
//        //Проверили что изменилось имя у босса
//        Assertions.assertEquals("Петров Петр", verifyPerson.getName());
//        //Проверили, что в компании company2 тоже изменилось имя босса
//        int companyId = verifyPerson.getCompany().getId();
//        Company verifyCompany = session.createNamedQuery("Company.getById", Company.class).
//                setParameter("id", companyId).getSingleResult();
//        Assertions.assertEquals("Петров Петр", verifyCompany.getBoss().getName());
//        session.close();
//    }
//
//    @Test
//    void getById() {
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        List<Person> personList = session.createNamedQuery("Person.getAll", Person.class).getResultList();
//        int i = personList.get(0).getId();
//        Assertions.assertEquals(personList.get(0), personRepository.getById(i));
//    }
//
//    @Test
//    void getAll() {
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        List<Person> personList = session.createNamedQuery("Person.getAll", Person.class).getResultList();
//        Assertions.assertEquals(personList.size(), personRepository.getAll().size());
//        session.close();
//    }
//
//    @Test
//    void delete() {
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Person verifyPerson = session.createNamedQuery("Person.getById", Person.class).
//                setParameter("id", person3.getId()).getSingleResult();
//        int companyId = verifyPerson.getCompany().getId();
//        Assertions.assertNotNull(verifyPerson);
//        personRepository.delete(person3);
//        //Проверили, что босс удалился
//        List<Person> verifyPersons = session.createNamedQuery("Person.getById", Person.class).
//                setParameter("id", person3.getId()).getResultList();
//        Assertions.assertEquals(0, verifyPersons.size());
//        session.close();
//        //Но компания с ним связанная осталась
//        session = HibernateConnection.getSessionFactory().openSession();
//        Company verifyCompany = session.createNamedQuery("Company.getById", Company.class).
//                setParameter("id", companyId).getSingleResult();
//        Assertions.assertNull(verifyCompany.getBoss());
//        session.close();
//    }
//}