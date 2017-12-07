package com.springboot.service;

import com.springboot.model.Reservation;
import org.aspectj.lang.annotation.Around;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/9.
 */
@Service
public class AopService {

    @Around(value = "@annotation(com.springboot.annotation.Log)")
    public Reservation findByCustName(String agrs) {
        System.out.println("------------------");
        return null;
    }
}
