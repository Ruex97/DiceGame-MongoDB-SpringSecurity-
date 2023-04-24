package cat.itacademy.barcelonactiva.rue.xavier.s05.dicegame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "cat.itacademy.barcelonactiva.rue.xavier.s05.dicegame.repository")
public class S05DiceGameRueXavierApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05DiceGameRueXavierApplication.class, args);
	}

}
