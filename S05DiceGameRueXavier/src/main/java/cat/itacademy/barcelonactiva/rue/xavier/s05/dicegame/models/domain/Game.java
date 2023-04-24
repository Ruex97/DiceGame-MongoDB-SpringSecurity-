package cat.itacademy.barcelonactiva.rue.xavier.s05.dicegame.models.domain;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {
    private int dicesSum;
    private int dice1;
    private int dice2;
    private String result;





}