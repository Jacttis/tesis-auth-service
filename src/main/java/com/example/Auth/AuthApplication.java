package com.example.Auth;

import com.example.Auth.document.Worker;
import com.example.Auth.repository.WorkerRepository;
import com.example.Auth.service.WorkerManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.Auth.repository.ClientRepository;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {ClientRepository.class, WorkerRepository.class})
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner populateWorkers() {
		return new WorkerPopulatorRunner();
	}

	public class WorkerPopulatorRunner implements CommandLineRunner {
		@Resource
		private WorkerManager workerManager;
		@Resource
		private WorkerRepository workerRepository;

		@Override
		public void run(String... args) throws Exception {
			String[] nombresApellidos = {
					"Julian Acttis", "Leandro Moran", "Cachito Acttis", "Gully Moran", "Lorena Acttis",
					"Marcela Cianci", "Muzza"
			};

			if(!workerRepository.existsById("julian@example.com"))
				workerManager.createUser(new Worker("julian@example.com", "asdasd", nombresApellidos[0], -38.7176522, -62.2654871, "Bahia Blanca", "29841291", "Gardener", new Date(2001,06,12)));

			if(!workerRepository.existsById("lean@example.com"))
				workerManager.createUser(new Worker("lean@example.com", "asdasd", nombresApellidos[1], -38.7176522, -62.2654871, "Bahia Blanca", "29841291", "Plumber", new Date(2001,06,12)));

			if(!workerRepository.existsById("cacho@example.com"))
				workerManager.createUser(new Worker("cacho@example.com", "asdasd", nombresApellidos[2], -38.7176522, -62.2654871, "Bahia Blanca", "29841291", "Bricklayer", new Date(2001,06,12)));

			if(!workerRepository.existsById("gully@example.com"))
				workerManager.createUser(new Worker("gully@example.com", "asdasd", nombresApellidos[3], -38.8804693, -62.0744033, "Punta Alta", "29841291", "Gardener", new Date(2001,06,12)));

			if(!workerRepository.existsById("lore@example.com"))
				workerManager.createUser(new Worker("lore@example.com", "asdasd", nombresApellidos[4], -38.7176522, -62.2654871, "Bahia Blanca", "29841291", "Gardener", new Date(2001,06,12)));

			if(!workerRepository.existsById("marcela@example.com"))
				workerManager.createUser(new Worker("marcela@example.com", "asdasd", nombresApellidos[5], -38.8804693, -62.0744033, "Punta Alta", "29841291", "Gardener", new Date(2001,06,12)));

			if(!workerRepository.existsById("muzza@example.com"))
				workerManager.createUser(new Worker("muzza@example.com", "asdasd", nombresApellidos[6], -38.8804693, -62.0744033, "Punta Alta", "29841291", "Singer", new Date(2001,06,12)));

		}
	}
}
