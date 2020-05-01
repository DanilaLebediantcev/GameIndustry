package dao;

import entity.Game;

import java.util.List;

public interface GameDAO {
    public void addGame(Game game);
    public void updateGames(Game game);
    public void deleteGame(Game game);
    public Game getGameById(Game game);
    public List<Game> getAllGames();
}
