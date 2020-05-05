//import Reposiroty.GenreRepository;
//import db.DBConnection;
//import db.HibernateConnection;
//import entity.Game;
//import entity.Genre;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//
//class GenreRepositoryTest {
//
//    //Create games
//    static Game game1 = new Game("gameTest7");
//
//    //Create Genre
//    static Genre genre1 = new Genre("genreTest7");
//    static Genre genre2 = new Genre("genreTest8");
//    static Genre genre3 = new Genre("genreTest9");
//
//    //Create repositories
//    GenreRepository genreRepository = new GenreRepository();
//
//
//    @BeforeAll
//    static void setUp() {
//        DBConnection dbConnection = new DBConnection();
//        dbConnection.getConnection();
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(genre1);
//        session.save(genre2);
//        transaction.commit();
//        transaction = session.beginTransaction();
//        game1.addGenreToGame(genre2);
//        session.save(game1);
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
//        int size = genreRepository.getAll().size();
//        genreRepository.add(genre3);
//        Assertions.assertEquals(size+1,genreRepository.getAll().size());
//    }
//
//    @Test
//    void update() {
//        Assertions.assertEquals("genreTest7",genre1.getName());
//        int id = genre1.getId();
//        genre1.setName("genreTest7_update");
//        genreRepository.update(genre1);
//
//
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Genre verifyGenre = session.createNamedQuery("Genre.getById",Genre.class).
//                setParameter("id", id).getSingleResult();
//        session.close();
//        Assertions.assertEquals("genreTest7_update",verifyGenre.getName());
//    }
//
//    @Test
//    void getById() {
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        List<Genre> genreList = session.createNamedQuery("Genre.getAll",Genre.class).getResultList();
//        int i = genreList.get(0).getId();
//        Assertions.assertEquals(genreList.get(0),genreRepository.getById(i));
//    }
//
//    @Test
//    void getAll() {
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        List<Genre> genreList = session.createNamedQuery("Genre.getAll",Genre.class).getResultList();
//        Assertions.assertEquals(genreList.size(),genreRepository.getAll().size());
//        session.close();
//    }
//
//    @Test
//    void delete() {
//        Session session = HibernateConnection.getSessionFactory().openSession();
//        Genre verifyGenre = session.createNamedQuery("Genre.getById",Genre.class).
//                setParameter("id", genre2.getId()).getSingleResult();
//        Assertions.assertNotNull(verifyGenre);
//        genreRepository.delete(genre2);
//        List<Genre> verifyGenres = session.createNamedQuery("Genre.getById",Genre.class).
//                setParameter("id", genre2.getId()).getResultList();
//        Assertions.assertEquals(0,verifyGenres.size());
//        session.close();
//    }
//
//}