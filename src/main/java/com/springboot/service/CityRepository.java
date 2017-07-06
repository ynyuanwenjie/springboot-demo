package com.springboot.service;

import com.springboot.model.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yuanwenjie on 2017/6/1.
 */
public interface CityRepository extends  JpaRepository<CityEntity,Long> {
}
