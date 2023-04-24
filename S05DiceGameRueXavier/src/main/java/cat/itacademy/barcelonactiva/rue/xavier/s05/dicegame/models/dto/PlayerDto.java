package cat.itacademy.barcelonactiva.rue.xavier.s05.dicegame.models.dto;

import cat.itacademy.barcelonactiva.rue.xavier.s05.dicegame.models.domain.Game;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class PlayerDto {

    private String id;
    private String name;
    private LocalDateTime dateOfRegister;
    private List<Game> games;
    private float winrate;

    public String showWinrate() {
        return  "Player_id: " + id +
                ", name: '" + name + '\'' +
                ", winrate: " + winrate + "%";
    }

    public String formatGames() {
        StringBuilder sb = new StringBuilder();
        for (Game game : games) {
            sb.append(game.getDicesSum());
            sb.append(", ");
        }
        // Remove the trailing comma and space
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }


}

