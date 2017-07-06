package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
@ComponentScan("com")
@EntityScan("com")
@EnableAutoConfiguration
public class SpringbootDemoApplication {

	/*@Bean
	CommandLineRunner runner (ReservationRepository rr) {
		return  args -> {
			System.out.println("spring boot start inti params .....");
			Arrays.asList("A,B,C,D,E,F,G".split(",")).forEach(
					s -> rr.save(new Reservation(s))
			);
			rr.findAll().stream().forEach(s -> System.out.println(s));
		};
	}*/

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
		//TestMain.test01();
	}

}

@RefreshScope
@RestController
class ReservationController {
	@Autowired
	private ReservationRepository reservationRepository;

	/*@Value("${message}")
	String message;
*/
	@RequestMapping("/message")
	String message() {
		return "Message";
	}

	@RequestMapping("/reservations")
	Collection<Reservation> reservations() {
		return this.reservationRepository.findAll();
	}

}

interface ReservationRepository extends JpaRepository<Reservation,Long> {

}

@Entity
@Table(name="t_reservation")
class Reservation {
	@Id
	@GeneratedValue
	private Long id;
	private String reservationName;

	public Reservation() {
	}

	public Reservation(String reservationName) {
		this.reservationName = reservationName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	@Override
	public String toString() {
		return "Reservation{" +
				"id=" + id +
				", reservationName='" + reservationName + '\'' +
				'}';
	}
}