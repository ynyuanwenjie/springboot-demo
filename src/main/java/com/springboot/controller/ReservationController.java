package com.springboot.controller;

import com.springboot.annotation.Log;
import com.springboot.model.Reservation;
import com.springboot.service.MessageCommunicator;
import com.springboot.service.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by yuanwenjie on 2017/8/9.
 */
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MessageCommunicator messageCommunicator;

    /*@Value("${message}")
    String message;
*/
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    Object message() {
        return "successful";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    Collection<Reservation> reservations() {
        return this.reservationRepository.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Reservation saveReservation(@Valid @RequestBody Reservation reservation) throws Exception {
        return this.reservationRepository.save(reservation);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    Reservation findReservation(@PathVariable String name) {
        return this.reservationRepository.findAll()
                .stream().filter(s -> s.getReservationName().equals(name))
                .findFirst()
                .orElse(null);
    }


    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    Reservation findReservation(@PathVariable Long id) throws Exception {
        return this.reservationRepository.findAll()
                .stream().filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("sorry,there is no reservation id is  " + id));
    }
}
