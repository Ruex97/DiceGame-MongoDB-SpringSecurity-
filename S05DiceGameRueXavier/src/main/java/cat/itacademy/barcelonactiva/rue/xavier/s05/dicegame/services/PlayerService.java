package cat.itacademy.barcelonactiva.rue.xavier.s05.dicegame.services;

import cat.itacademy.barcelonactiva.rue.xavier.s05.dicegame.models.dto.PlayerDto;

import java.util.List;

public interface PlayerService {

    PlayerDto rollTheDices(String id);
    PlayerDto updatePlayer(String id, PlayerDto playerDto);
    String deleteGames(String id);
    List<PlayerDto> getPlayersWinrate();
    PlayerDto getPlayerById(String id);
    Float getAverageWinrate();
    PlayerDto getWorstPlayer();
    PlayerDto getBestPlayer();




}
