package cat.itacademy.barcelonactiva.rue.xavier.s05.dicegame.services;

import cat.itacademy.barcelonactiva.rue.xavier.s05.dicegame.models.domain.Game;
import cat.itacademy.barcelonactiva.rue.xavier.s05.dicegame.models.domain.Player;
import cat.itacademy.barcelonactiva.rue.xavier.s05.dicegame.models.dto.PlayerDto;
import cat.itacademy.barcelonactiva.rue.xavier.s05.dicegame.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    @Override
    public PlayerDto rollTheDices(String id) {
        Player player1, updatedPlayer;
        PlayerDto playerDto;

        Optional<Player> player = playerRepository.findById(id);
        if(player.isPresent()) {
            player1 = player.get();
            player1.rollTheDices();
            player1.updateWinrate();
            updatedPlayer = playerRepository.save(player1);
            playerDto = playerToDto(updatedPlayer);
        } else {
            playerDto = null;
        }
        return playerDto;
    }

    @Override
    public PlayerDto updatePlayer(String id, PlayerDto playerDto) {
        PlayerDto responsePlayer;
        Player player1, savedPlayer;
        Optional<Player> player = playerRepository.findById(id);

        if(player.isPresent()) {
            player1 = player.get();
            player1.setName(playerDto.getName());
            savedPlayer = playerRepository.save(player1);
            responsePlayer = playerToDto(savedPlayer);
        } else{
            responsePlayer = null;
        }

        return responsePlayer;

    }

    @Override
    public String deleteGames(String id) {
        Player player1;
        Optional<Player> player = playerRepository.findById(id);
        if(player.isPresent()) {
            player1 = player.get();
            player1.getGames().clear();
            player1.updateWinrate();
            playerRepository.save(player1);
            return "History deleted";
        }
        return "Player with id " + id +" not found";
    }


    @Override
    public List<PlayerDto> getPlayersWinrate() {
        List<PlayerDto> players = playerRepository.findAll().stream().map(this::playerToDto).collect(Collectors.toList());
        return players;
    }

    @Override
    public PlayerDto getPlayerById(String id){
        Optional<Player> player = playerRepository.findById(id);
        PlayerDto responsePlayer;

        if(player.isPresent()) {
            Player myPlayer = player.get();
            responsePlayer = playerToDto(myPlayer);
        } else{
            responsePlayer = null;
        }
        return responsePlayer;
    }

    @Override
    public Float getAverageWinrate() {
        List<Player> players = playerRepository.findAll();
        double averageWinrate = players.stream()
                .mapToDouble(Player::getWinrate)
                .average()
                .orElse(0.0);


        return (float) averageWinrate;
    }

    @Override
    public PlayerDto getWorstPlayer() {
        List<PlayerDto> players = playerRepository.findAll().stream().map(this::playerToDto).collect(Collectors.toList());
        PlayerDto loser = players.stream()
                .min(Comparator.comparingDouble(p -> p.getWinrate()))
                .orElse(null);

        return loser;
    }

    @Override
    public PlayerDto getBestPlayer() {
        List<PlayerDto> players = playerRepository.findAll().stream().map(this::playerToDto).collect(Collectors.toList());
        PlayerDto winner = players.stream()
                .max(Comparator.comparingDouble(p -> p.getWinrate()))
                .orElse(null);

        return winner;
    }

    public PlayerDto playerToDto(Player player){

        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());
        playerDto.setName(player.getName());
        playerDto.setDateOfRegister(player.getDateOfRegister());
        playerDto.setGames(player.getGames());
        playerDto.setWinrate(player.getWinrate());

        return playerDto;
    }

}
