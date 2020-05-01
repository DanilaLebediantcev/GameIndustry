import Reposiroty.CompanyRepository;
import Reposiroty.GameRepository;
import Reposiroty.PersonRepository;
import db.DBConnection;
import db.HibernateConnection;
import entity.Company;
import entity.Game;
import entity.Person;
import org.hibernate.Session;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();

        //Create Leads
        Person ubisoftLead = new Person("Jhon");
        Person eaLead = new Person("Martin");

        //Create company
        Company ubisoft = new Company("Ubisoft",1000,5000000);
        ubisoft.setBoss(ubisoftLead);
        Company ea =  new Company("EA",2300,80000000);

        //Create game
        Game assassins = new Game("AssassinsCreed",ubisoft);
        Game anno = new Game("Anno2025",ubisoft);
        Game princeOfPersia = new Game("Prince of Persia", ubisoft);

        Game fifa = new Game("FIFA2020");
        Game battlefield = new Game("BattlefieldV");


        //Repositories
        CompanyRepository companyRepository = new CompanyRepository();
        GameRepository gameRepository = new GameRepository();
        PersonRepository personRepository = new PersonRepository();



        ubisoft.addGameToGamesList(assassins);
        ubisoft.addGameToGamesList(anno);

        companyRepository.addCompany(ubisoft);


//        companyRepository.addGameToGamesList(assassins);
//        companyRepository.addGameToGamesList(anno);



        System.out.println("/--------------------------------------------------/");
        System.out.println("All companies");
        companyRepository.getAllCompany().forEach(company -> System.out.println(company.toString()));

        System.out.println("All games");
        gameRepository.getAllGames().forEach(game -> System.out.println(game.toString()));

        ubisoft.setName("UBIIIIIIIIIII");
        companyRepository.updateCompany(ubisoft);

        System.out.println("/--------------------------------------------------/");
        System.out.println("All companies");
        companyRepository.getAllCompany().forEach(company -> System.out.println(company.toString()));

        System.out.println("All games");
        gameRepository.getAllGames().forEach(game -> System.out.println(game.toString()));
        System.out.println("/--------------------------------------------------/");
        System.out.println("Change game's name , assassins -> LALALALALALA ,  anno ->GIGIGIGIGI, change boss name");
        assassins.setName("LALALALALLALA");
        anno.setName("GIGIGIGIGIIGIG");

        ubisoft.setBoss(eaLead);
        companyRepository.updateCompany(ubisoft);


        gameRepository.updateGames(anno);
        gameRepository.updateGames(assassins);

        System.out.println("All companies");
        companyRepository.getAllCompany().forEach(company -> System.out.println(company.toString()));

        System.out.println("All games");
        gameRepository.getAllGames().forEach(game -> System.out.println(game.toString()));

        System.out.println("All bosses");
        personRepository.getAllPersons().forEach(person -> System.out.println(person.toString()));


        System.out.println("/--------------------------------------------------/");
        System.out.println("Delete game");

        ubisoft.deleteGameFromGamesList(assassins);
        companyRepository.updateCompany(ubisoft);

        System.out.println("All companies");
        companyRepository.getAllCompany().forEach(company -> System.out.println(company.toString()));

        System.out.println("All games");
        gameRepository.getAllGames().forEach(game -> System.out.println(game.toString()));


        System.out.println("/--------------------------------------------------/");
        System.out.println("Delete company");
        companyRepository.deleteCompany(ubisoft);

        System.out.println("All companies");
        companyRepository.getAllCompany().forEach(company -> System.out.println(company.toString()));

        System.out.println("All games");
        gameRepository.getAllGames().forEach(game -> System.out.println(game.toString()));

        System.out.println("All bosses");
        personRepository.getAllPersons().forEach(person -> System.out.println(person.toString()));

    }
}
