import Reposiroty.CompanyRepository;
import Reposiroty.GameRepository;
import Reposiroty.GenreRepository;
import Reposiroty.PersonRepository;
import db.DBConnection;
import db.HibernateConnection;
import entity.Company;
import entity.Game;
import entity.Genre;
import entity.Person;


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

        //Create genre
        Genre mmo = new Genre("MMO");
        Genre rpg = new Genre("RPG");

        //Repositories
        CompanyRepository companyRepository = new CompanyRepository();
        GameRepository gameRepository = new GameRepository();
        PersonRepository personRepository = new PersonRepository();
        GenreRepository genreRepository = new GenreRepository();



        HibernateConnection.shutDown();
    }
}
