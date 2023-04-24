package cat.itacademy.barcelonactiva.rue.xavier.s05.dicegame.repository;

import cat.itacademy.barcelonactiva.rue.xavier.s05.dicegame.models.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    Optional<Player> findByName(String username);

    boolean existsByName(String name);
}
