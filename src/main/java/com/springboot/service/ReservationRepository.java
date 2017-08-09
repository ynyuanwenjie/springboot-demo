package com.springboot.service;

import com.springboot.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/8/9.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
