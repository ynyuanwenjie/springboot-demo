package com.springboot.controller;

import com.springboot.model.Reservation;
import com.springboot.service.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by yuanwenjie on 2017/8/11.
 */
@Controller
@RequestMapping("/reservation/thymeleaf")
public class ReservationThymeleafController {
    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String listReservation(Model model) {
        List<Reservation> reservationList = reservationRepository.findAll();
        if(null != reservationList && reservationList.size() > 0) {
            model.addAttribute("reservations", reservationList);
        }
        return "reservationList";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveReservation(@Valid Reservation reservation, Errors errors,HttpServletRequest request) throws Exception {
        if (errors.hasErrors()) {
            for (FieldError fieldError : errors.getFieldErrors()) {
                throw new Exception(fieldError.getField()+"["+fieldError.getDefaultMessage()+"]");
            }
        }
        reservationRepository.save(reservation);
        return "redirect:/reservation/thymeleaf/list";
    }
}
