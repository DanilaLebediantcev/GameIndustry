package dao;

import entity.Game;

import java.util.List;

public interface GameDAO {
    public void addGame(Game game);
    public void updateGame(Game game);
    public void deleteGame(Game game);
    public Game getGameById(int id);
    public List<Game> getAllGames();
}
