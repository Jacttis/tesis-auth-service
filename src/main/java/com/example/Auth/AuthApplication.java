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
					"Juan Pérez", "María López", "Carlos Martínez", "Ana Rodríguez", "Pedro García",
					"Laura Sánchez", "Miguel Fernández", "Sofía Ramírez", "Luis González", "Elena Díaz",
					"Francisco Herrera", "Isabel Torres", "José Jiménez", "Carmen Vargas", "Javier Moreno",
					"Raquel Castro", "Alejandro Ruiz", "Patricia Gómez", "Diego Molina", "Natalia Ortega",
					"Ricardo Medina", "Adriana Silva", "Fernando Mendoza", "Beatriz Paredes", "Gabriel Jiménez",
					"Monica Ramos", "Hugo Rojas", "Silvia Flores", "Alberto Varela", "Lorena Jiménez"
			};

			for(int i = 1; i<=5; i++){
				if(!workerRepository.existsById("worker" + i + "@example.com"))
					workerManager.createUser(new Worker("worker" + i + "@example.com", "asdasd", nombresApellidos[i-1], -38.7176522, -62.2654871, "Bahia Blanca", "29841291", "Gardener", new Date(2001,06,12)));
			}

			for(int i = 6; i<=10; i++){
				if(!workerRepository.existsById("worker" + i + "@example.com"))
					workerManager.createUser(new Worker("worker" + i + "@example.com", "asdasd", nombresApellidos[i-1], -38.7176522, -62.2654871, "Bahia Blanca", "29112331", "Electrician", new Date(1980,07,10)));
			}

			for(int i = 11; i<=15; i++){
				if(!workerRepository.existsById("worker" + i + "@example.com"))
					workerManager.createUser(new Worker("worker" + i + "@example.com", "asdasd", nombresApellidos[i-1], -38.7176522, -62.2654871, "Bahia Blanca", "298312291", "Painter", new Date(1986,02,10)));
			}

			for(int i = 16; i<=20; i++){
				if(!workerRepository.existsById("worker" + i + "@example.com"))
					workerManager.createUser(new Worker("worker" + i + "@example.com", "asdasd", nombresApellidos[i-1], -38.8804693, -62.0744033, "Punta Alta", "29841291", "Bricklayer", new Date(1990,07,12)));
			}

			for(int i = 21; i<=25; i++){
				if(!workerRepository.existsById("worker" + i + "@example.com"))
					workerManager.createUser(new Worker("worker" + i + "@example.com", "asdasd", nombresApellidos[i-1], -38.8804693, -62.0744033, "Punta Alta", "29841291", "Plumber", new Date(2002,03,10)));
			}

			for(int i = 26; i<=30; i++){
				if(!workerRepository.existsById("worker" + i + "@example.com"))
					workerManager.createUser(new Worker("worker" + i + "@example.com", "asdasd", nombresApellidos[i-1], -38.8804693, -62.0744033, "Punta Alta", "29841291", "Tutor", new Date(1970,02,11)));
			}

		}
	}
}
