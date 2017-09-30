package com.springboot.controller;

import com.springboot.model.Reservation;
import com.springboot.service.ReservationRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by yuanwenjie on 2017/8/9.
 */
@RefreshScope
@RestController
@RequestMapping("/reservation")
@Api(value = "ReservationController",
        tags = "ReservationController",
        description = "预约接口",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiResponses({@ApiResponse(code = 200, message = "调用成功"),
        @ApiResponse(code = 400, message = "业务异常"),
        @ApiResponse(code = 401, message = "您没有登录"),
        @ApiResponse(code = 403, message = "权限不够,不允许访问"),
        @ApiResponse(code = 405, message = "请求的方法不支持"),
        @ApiResponse(code = 500, message = "系统内部错误"),
        @ApiResponse(code = 502, message = "网关超时"),
        @ApiResponse(code = 503, message = "服务不可达")})
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;

    /*@Value("${message}")
    String message;
*/
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ApiOperation(value = "主页", notes = "-主页-")
    String message() {
        return "successful";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "预约列表", notes = "-预约列表-")
    Collection<Reservation> reservations() {
        return this.reservationRepository.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "添加预约", notes = "-添加预约-")
    Reservation saveReservation(@ApiParam(name="reservation",value="reservation",required=true) @Valid @RequestBody Reservation reservation) throws Exception {
        return this.reservationRepository.save(reservation);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    @ApiOperation(value = "根据姓名查询", notes = "-根据姓名查询-")
    Reservation findReservation(@ApiParam(name="name",value="-姓名-",required=true) @PathVariable String name) {
        return this.reservationRepository.findAll()
                .stream().filter(s -> s.getReservationName().equals(name))
                .findFirst()
                .orElse(null);
    }


    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID查询", notes = "-根据ID查询-")
    Reservation findReservation(@PathVariable Long id) throws Exception {
        return this.reservationRepository.findAll()
                .stream().filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("sorry,there is no reservation id is  " + id));
    }
}
