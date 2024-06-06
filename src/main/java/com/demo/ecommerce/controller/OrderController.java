package com.demo.ecommerce.controller;


import com.demo.ecommerce.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
@CrossOrigin
public class OrderController {
    @Autowired
    private final OrderService orderService;
}
