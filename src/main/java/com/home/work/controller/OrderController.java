package com.home.work.controller;

import com.home.work.domain.Orders;
import io.swagger.annotations.*;
import com.home.work.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequiredArgsConstructor
@Log
public class OrderController {

    @Autowired
    OrderService orderService;

    @ApiOperation(value = "해당 회원 주문 조회")
    @GetMapping("/order/{memberId}")
    public ResponseEntity<List<Orders>> OrderDetail(@PathVariable("memberId") String memberId) {
        List<Orders> result = orderService.getOrderDetail(memberId);
        if(result.size() == 0){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "회원 주문 등록")
    @PostMapping("/order/{memberId}")
    public ResponseEntity<Map<String, String>> setOrders(@RequestBody Orders orders, @PathVariable("memberId") String memberId) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 90; // letter 'z'
        int targetStringLength = 4;
        Random random = new Random();
        LocalDate ddd = LocalDate.now();
        String yyyyMMdd = ddd.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        String generatedString = random.ints(leftLimit,rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        orders.setOrderNumber(yyyyMMdd+"-"+generatedString);
        double result =  orderService.setOrders(orders, memberId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "주문이 완료 되었습니다.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Not Found")
    @ExceptionHandler(NoSuchElementException.class)
    public void conflict() { }

}
