//import Reposiroty.GameRepository;
//import db.DBConnection;
//import db.HibernateConnection;
//import entity.Company;
//import entity.Game;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//
//class GameRepositoryTest {
//    //Create companies
//    static Company company1 = new Company("companyTest1", 10000, 700000000);
//    static Company company2 = new Company("companyTest2",17500,6000000);
//
//    //Create games
//    static Game game1 = new Game("gameTest1");
//    static Game game2 = new Game("gameTest2");
//    static Game game3 = new Game("gameTest3");
//
//    //Create repositories
//    GameRepository gameRepository = new GameRepository();
//
//
//    @BeforeAll
//    static void setUp() {
//        DBConnection dbConnection = new DBConnection();
//        dbConnection.getConnection();
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(game1);
//        session.save(game3);
//        session.save(company1);
//        session.save(company2);
//        transaction.commit();
//        transaction = session.beginTransaction();
//        company1.addGameToGamesList(game3);
//        company2.addGameToGamesList(game1);
//        session.save(company1);
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
//        int size = gameRepository.getAll().size();
//        gameRepository.add(game2);
//        Assertions.assertEquals(size+1,gameRepository.getAll().size());
//    }
//
//    @Test
//    void update() {
//        //Проверяем, что game1 действительно имеет имя gameTest1
//        Assertions.assertEquals("gameTest1",game1.getName());
//        int id = game1.getId();
//
//        //Изменили имя
//        game1.setName("gameTest1_update");
//        gameRepository.update(game1);
//
//        //Через кверю получаем game1, убеждаемся что имя ее изменилось
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Game verifyGame = session.createNamedQuery("Game.getById",Game.class).
//                setParameter("id", id).getSingleResult();
//        session.close();
//        Assertions.assertEquals("gameTest1_update",verifyGame.getName());
//    }
//
//    @Test
//    void getById() {
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        List<Game> gameList = session.createNamedQuery("Game.getAll",Game.class).
//                getResultList();
//        int i = gameList.get(0).getId();
//        Assertions.assertEquals(gameList.get(0),gameRepository.getById(i));
//    }
//
//    @Test
//    void getAll() {
//        //Проверяем, что список игр, полученных через JPQL, такого же размера, как и возвращаемый методом
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        List<Game> gameList = session.createNamedQuery("Game.getAll",Game.class).getResultList();
//        Assertions.assertEquals(gameList.size(),gameRepository.getAll().size());
//        session.close();
//    }
//
//    @Test
//    void delete() {
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Game verifyGame = session.createNamedQuery("Game.getById",Game.class).
//                setParameter("id", game3.getId()).getSingleResult();
//        //Проверили, что игра есть в базе, что company1 содержит game3
//        Assertions.assertNotNull(verifyGame);
//        Assertions.assertTrue(company1.getGamesList().contains(verifyGame));
//        //Удаляем игру, проверяем, что такой игры больше нет
//        gameRepository.delete(game3);
//        List<Game> verifyGames = session.createNamedQuery("Game.getById",Game.class).
//                setParameter("id", game3.getId()).getResultList();
//        Assertions.assertEquals(0,verifyGames.size());
//        //Проверяем, что у компании в списке игр она тоже не содержится
//        Company checkThatCompany1DoesNotContainsGame3 = session.createNamedQuery("Company.getById",Company.class).
//                setParameter("id",company1.getId()).getSingleResult();
//        Assertions.assertFalse(checkThatCompany1DoesNotContainsGame3.getGamesList().contains(verifyGame));
//        session.close();
//    }
//}