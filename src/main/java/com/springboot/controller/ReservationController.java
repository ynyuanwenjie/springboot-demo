package com.springboot.controller;

import com.springboot.model.Reservation;
import com.springboot.service.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by Administrator on 2017/8/9.
 */
@RefreshScope
@RestController
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;

    /*@Value("${message}")
    String message;
*/
    @RequestMapping("/index")
    String message() {
        return "successful";
    }

    @RequestMapping("/reservations")
    Collection<Reservation> reservations() {
        return this.reservationRepository.findAll();
    }

    @RequestMapping("/{name}/reservation")
    Reservation findReservation(@PathVariable String name) {
        return this.reservationRepository.findAll()
                .stream().filter(s -> s.getReservationName().equals(name))
                .findFirst()
                .orElse(new Reservation(null,"sorry,there is no reservation name is "+name));
    }

    @RequestMapping("/{id}/reservation")
    Reservation findReservation(@PathVariable Long id) throws Exception {
        return this.reservationRepository.findAll()
                .stream().filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("sorry,there is no reservation id is "+id));
    }
}
