package cat.itacademy.barcelonactiva.rue.xavier.s05.dicegame.models.domain;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Random;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("players")
public class Player implements UserDetails {
    @Id
    private String id;
    private String name;
    private String password;
    @CreatedDate
    private LocalDateTime dateOfRegister;
    private Role role;
    private List<Game> games;
    private float winrate;


    public float updateWinrate() {
        if(!games.isEmpty()){
            float victories = games.stream().filter(result -> result.getResult().equals("You win!")).count();
            float ratio = (float) victories /(float) games.size();
            winrate = ratio*100;
        } else {
            winrate = 0.0f;
        }

        return winrate;
    }


    public void rollTheDices() {
        Game game = new Game();
        Random random = new Random();

        game.setDice1(random.nextInt(6) + 1);
        game.setDice2(random.nextInt(6) + 1);
        game.setDicesSum(game.getDice1() + game.getDice2());
        if(game.getDicesSum() == 7) {
            game.setResult("You win!");
        } else {
            game.setResult("You lose :(");
        }

        games.add(game);

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
