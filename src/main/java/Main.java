import Reposiroty.CompanyRepository;
import Reposiroty.GameRepository;
import Reposiroty.GenreRepository;
import Reposiroty.PersonRepository;
import db.HibernateConnection;
import entity.Company;
import entity.Game;
import entity.Genre;
import entity.Person;
import org.hibernate.Session;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        //Create Leads
        Person ubisoftLead = new Person("Jhon");
        Person eaLead = new Person("Martin");

        //Create company
        Company ubisoft = new Company("Ubisoft", 1000, 5000000);
        Company ea = new Company("EA", 2300, 80000000);


        //Create game
        Game assassins = new Game("AssassinsCreed", ubisoft);
        Game anno = new Game("Anno2025", ubisoft);
        Game princeOfPersia = new Game("Prince of Persia", ubisoft);


        Game fifa = new Game("FIFA2020", ea);
        Game battlefield = new Game("BattlefieldV", ea);

        //Create genre
        Genre mmo = new Genre("MMO");
        Genre rpg = new Genre("RPG");

        //Repositories
        CompanyRepository companyRepository = new CompanyRepository();
        GameRepository gameRepository = new GameRepository();
        PersonRepository personRepository = new PersonRepository();
        GenreRepository genreRepository = new GenreRepository();

        Session session = HibernateConnection.getSessionFactory().openSession();

        System.out.println("/-----------------------------------------------------/");
        System.out.println("Проверяем методы для главы компании");
        List<Person> personList = session.createNamedQuery("Person.getAll",Person.class).getResultList();
        System.out.println("Количество людей : " + personList.size()+ " ." + personList);
        System.out.println("Добавляем пользователя, проверка метода add");
        personRepository.add(ubisoftLead);
        personList = session.createNamedQuery("Person.getAll",Person.class).getResultList();
        System.out.println("Количество людей : " + personList.size()+ " ." + personList);
        System.out.println();

        System.out.println("Апдейтим босса, проверяем метод update");
        System.out.println(ubisoftLead.toString());
        ubisoftLead.setName("updatedName");
        personRepository.update(ubisoftLead);
        System.out.println("После апдейта:" + ubisoftLead.toString());
        System.out.println();

        System.out.println("получаем по ID");
        System.out.println(personRepository.getById(ubisoftLead.getId()).toString());
        System.out.println();

        System.out.println("Получаем всех пользователей (сейчас в системе только 1 босс)");
        personRepository.getAll().forEach(person -> System.out.println(person.toString()));

        System.out.println();
        System.out.println("Удаляем босса");
        personList = session.createNamedQuery("Person.getAll",Person.class).getResultList();
        System.out.println("Количество людей : " + personList.size()+ " ." + personList);
        personRepository.delete(ubisoftLead);
        personList = session.createNamedQuery("Person.getAll",Person.class).getResultList();
        System.out.println("Количество людей после удаления : " + personList.size());

        System.out.println("/-----------------------------------------------------/");
        System.out.println("Проверяем методы для компании");
        List<Company> companyList = session.createNamedQuery("Company.getAll",Company.class).getResultList();
        System.out.println("Количество компаний : " + companyList.size()+ " ." + companyList);
        System.out.println("Добавляем компанию, проверка метода add");
        companyRepository.add(ea);
        companyList = session.createNamedQuery("Company.getAll",Company.class).getResultList();
        System.out.println("Количество компаний : " + companyList.size()+ " ." + companyList);
        System.out.println();

        System.out.println("Апдейтим компанию, проверяем метод update");
        ea.setName("EA_UPDATENAME");
        companyRepository.update(ea);
        System.out.println("После апдейта:" + ea.toString());
        System.out.println();

        System.out.println("получаем по ID");
        System.out.println(companyRepository.getById(ea.getId()).toString());
        System.out.println();

        System.out.println("Получаем все компании");
        companyRepository.getAll().forEach(company1 -> System.out.println(company1.toString()));

        System.out.println();
        System.out.println("Удаляем компанию");
        companyList = session.createNamedQuery("Company.getAll",Company.class).getResultList();
        System.out.println("Количество компаний : " + companyList.size()+ " ." + companyList);
        companyRepository.delete(ea);
        companyList = session.createNamedQuery("Company.getAll",Company.class).getResultList();
        System.out.println("Количество компаний после удаления : " + companyList.size()+ " ." + companyList);

        System.out.println("/-----------------------------------------------------/");
        System.out.println("Проверяем методы для игр (add,update,delete тк методы гет аналогичны предыдущим)");
        List<Game> gameList = session.createNamedQuery("Game.getAll",Game.class).getResultList();
        System.out.println("Количество игр : " + gameList.size()+ " ." + gameList);
        System.out.println("Добавляем игру, проверка метода add");
        companyRepository.add(ubisoft);
        gameRepository.add(assassins);
        gameList = session.createNamedQuery("Game.getAll",Game.class).getResultList();
        System.out.println("Количество игр : " + gameList.size()+ " ." + gameList);
        System.out.println();

        System.out.println("Апдейтим игру, проверяем метод update");
        assassins.setName("AssassinsUpdateName");
        gameRepository.update(assassins);
        System.out.println("После апдейта:" + assassins.toString());
        System.out.println();

        System.out.println();
        System.out.println("Удаляем игру");
        gameList = session.createNamedQuery("Game.getAll",Game.class).getResultList();
        System.out.println("Количество игр : " + gameList.size()+ " ." + gameList);
        gameRepository.delete(assassins);
        gameList = session.createNamedQuery("Game.getAll",Game.class).getResultList();
        System.out.println("Количество игр после удаления : " + gameList.size()+ " ." + gameList);

        System.out.println("/-----------------------------------------------------/");
        System.out.println("Проверяем методы для жанров (add,update,delete тк методы гет аналогичны предыдущим)");
        List<Genre> genreList = session.createNamedQuery("Genre.getAll",Genre.class).getResultList();
        System.out.println("Количество игр : " + genreList.size() + " ." + genreList);
        System.out.println("Добавляем игру, проверка метода add");
        genreRepository.add(rpg);
        genreList = session.createNamedQuery("Genre.getAll",Genre.class).getResultList();
        System.out.println("Количество игр : " + genreList.size() + " ." + genreList);

        System.out.println();

        System.out.println("Апдейтим жанр, проверяем метод update");
        rpg.setName("RPG_UPDATE");
        genreRepository.update(rpg);
        System.out.println("После апдейта:" + rpg.toString());
        System.out.println();

        System.out.println("Удаляем жанр");
        genreList = session.createNamedQuery("Genre.getAll",Genre.class).getResultList();
        System.out.println("Количество жанров : " + genreList.size());
        genreRepository.delete(rpg);
        genreList = session.createNamedQuery("Genre.getAll",Genre.class).getResultList();
        System.out.println("Количество жанров после удаления : " + genreList.size());

        System.out.println("/-----------------------------------------------------/");
        System.out.println("Проверяем зависимости между боссом и компанией");
        System.out.println("Добавляем зависимость");
        personRepository.add(ubisoftLead);
        ubisoft.setBoss(ubisoftLead);
        companyRepository.update(ubisoft);
        System.out.println("Выводим компанию: " + companyRepository.getById(ubisoft.getId()).toString());
        System.out.println("Выводим босса: " + personRepository.getById(ubisoftLead.getId()).toString() + ", " + personRepository.getById(ubisoftLead.getId()).getCompany());
        System.out.println();

        System.out.println("Апдейтим имя компании");
        ubisoft.setName("Ubisoft_update");
        companyRepository.update(ubisoft);
        System.out.println("Выводим компанию: " + companyRepository.getById(ubisoft.getId()).toString());
        System.out.println("Выводим босса: " + personRepository.getById(ubisoftLead.getId()).toString() + ", " + personRepository.getById(ubisoftLead.getId()).getCompany());
        System.out.println();

        System.out.println("Апдейтим имя босса");
        ubisoftLead.setName("ubiLead_update");
        personRepository.update(ubisoftLead);
        System.out.println("Выводим компанию: " + companyRepository.getById(ubisoft.getId()).toString());
        System.out.println("Выводим босса: " + personRepository.getById(ubisoftLead.getId()).toString() + ", " + personRepository.getById(ubisoftLead.getId()).getCompany());
        System.out.println();

        System.out.println("Удаляем компанию,должен удалиться и босс");
        companyRepository.delete(ubisoft);
        personList = session.createNamedQuery("Person.getAll", Person.class).getResultList();
        System.out.println("Количество людей : " + personList.size() + " ." + personList);
        System.out.println();

        System.out.println("Удаляем босса,компания остается");
        personRepository.add(ubisoftLead);
        ubisoft.setBoss(ubisoftLead);
        companyRepository.add(ubisoft);

        personRepository.delete(ubisoftLead);
        companyList = session.createNamedQuery("Company.getAll", Company.class).getResultList();
        System.out.println("Количество компаний : " + companyList.size() + " ." + companyList);
        System.out.println();

        System.out.println("/-----------------------------------------------------/");
        System.out.println("Проверяем зависимости между компанией и игрой");
        System.out.println("Добавляем зависимость (появляется при добавлении игры в бд)");
        gameRepository.add(assassins);
        System.out.println("Выводим компанию: " + companyRepository.getById(ubisoft.getId()).toString() + ". " + companyRepository.getById(ubisoft.getId()).getGamesList());
        System.out.println("Выводим игру: " + gameRepository.getById(assassins.getId()).toString() + ", " + gameRepository.getById(assassins.getId()).getCompany());
        System.out.println();

        System.out.println("Апдейтим имя компании");
        ubisoft.setName("Ubisoft_update");
        companyRepository.update(ubisoft);
        System.out.println("Выводим компанию: " + companyRepository.getById(ubisoft.getId()).toString());
        System.out.println("Выводим игру: " + gameRepository.getById(assassins.getId()).toString() + ", " + gameRepository.getById(assassins.getId()).getCompany());
        System.out.println();

        System.out.println("Апдейтим имя игры");
        assassins.setName("AssassinsUpdate");
        gameRepository.update(assassins);
        System.out.println("Выводим компанию: " + companyRepository.getById(ubisoft.getId()).toString() + ". " + companyRepository.getById(ubisoft.getId()).getGamesList());
        System.out.println("Выводим игру: " + gameRepository.getById(assassins.getId()).toString());
        System.out.println();

        System.out.println("Удаляем игру,компания остается");
        gameRepository.delete(assassins);
        gameList = session.createNamedQuery("Game.getAll", Game.class).getResultList();
        System.out.println("Количество игр : " + gameList.size() + " ." + gameList);
        System.out.println("Выводим компанию: " + companyRepository.getById(ubisoft.getId()).toString() + ". " + companyRepository.getById(ubisoft.getId()).getGamesList());
        System.out.println();

        System.out.println("Удаляем компанию,должна удалиться и игра");
        gameRepository.add(assassins);
        ubisoft = session.createNamedQuery("Company.getById", Company.class).setParameter("id", ubisoft.getId()).getSingleResult();
        session.close();
        companyRepository.delete(ubisoft);
        session = HibernateConnection.getSessionFactory().openSession();
        gameList = session.createNamedQuery("Game.getAll", Game.class).getResultList();
        System.out.println("Количество игр : " + gameList.size() + " ." + gameList);
        System.out.println();

        System.out.println("/-----------------------------------------------------/");
        System.out.println("Проверяем зависимости между игрой и жанром");
        ubisoft = new Company("Ubisoft", 1000, 5000000);
        companyRepository.add(ubisoft);
        assassins = new Game("AssassinsCreed", ubisoft);
        gameRepository.add(assassins);
        genreRepository.add(rpg);
        assassins.addGenreToGame(rpg);
        gameRepository.update(assassins);
        System.out.println("Выводим игру: " + gameRepository.getById(assassins.getId()).toString()
                + ", " + gameRepository.getById(assassins.getId()).getGenreList());
        System.out.println("Выводим жанр: " + genreRepository.getById(rpg.getId()).toString() + ", gameList: " + genreRepository.getById(rpg.getId()).getGameList());
        System.out.println();

        System.out.println("Удаляем жанр, игра должна остаться");
        genreRepository.delete(rpg);
        System.out.println("Выводим игру: " + gameRepository.getById(assassins.getId()).toString() + ", gameList: " + gameRepository.getById(assassins.getId()).getGenreList());
        System.out.println();

        System.out.println("Удаляем игру, жанр должен остаться");
        genreRepository.add(rpg);
        assassins.addGenreToGame(rpg);
        gameRepository.update(assassins);
        gameRepository.delete(assassins);
        System.out.println("Выводим жанр: " + genreRepository.getById(rpg.getId()).toString() + ", gameList: " + genreRepository.getById(rpg.getId()).getGameList());
        System.out.println();


        gameRepository.add(assassins);
        assassins.addGenreToGame(rpg);
        gameRepository.update(assassins);
        System.out.println("Апдейтим имя игры");
        assassins.setName("AssassinsUpdate");
        gameRepository.update(assassins);
        System.out.println("Выводим жанр: " + genreRepository.getById(rpg.getId()).toString() + ", gameList: " + genreRepository.getById(rpg.getId()).getGameList());
        System.out.println();

        System.out.println("Апдейтим имя жанра");
        rpg.setName("RPG_UPDATE");
        genreRepository.update(rpg);
        System.out.println("Выводим игру: " + gameRepository.getById(assassins.getId()).toString() + ", gameList: " + gameRepository.getById(assassins.getId()).getGenreList());
        System.out.println();
        HibernateConnection.shutDown();
    }
}
