package com.springboot.controller;

import com.springboot.model.Reservation;
import com.springboot.service.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by Administrator on 2017/8/9.
 */
@RefreshScope
@RestController
@RequestMapping("/reservation")
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

    @RequestMapping("/list")
    Collection<Reservation> reservations() {
        return this.reservationRepository.findAll();
    }

    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    Reservation saveReservation(@Valid @RequestBody Reservation reservation) throws Exception {
        System.out.println(reservation);
        return this.reservationRepository.save(reservation);
    }

    @RequestMapping("/name/{name}")
    Reservation findReservation(@PathVariable String name) {
        return this.reservationRepository.findAll()
                .stream().filter(s -> s.getReservationName().equals(name))
                .findFirst()
                .orElse(null);
    }


    @RequestMapping("/id/{id}")
    Reservation findReservation(@PathVariable Long id) throws Exception {
        return this.reservationRepository.findAll()
                .stream().filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("sorry,there is no reservation id is  "+id));
    }
}
