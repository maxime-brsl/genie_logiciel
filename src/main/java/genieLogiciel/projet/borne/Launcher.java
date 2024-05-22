package genieLogiciel.projet.borne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Launcher implements CommandLineRunner {

	@Autowired
	private Application application;

	public static void main(String[] args) {
		SpringApplication.run(Launcher.class, args);
	}

	@Override
	public void run(String... args) {
		application.runTerminalApp();
	}
}
