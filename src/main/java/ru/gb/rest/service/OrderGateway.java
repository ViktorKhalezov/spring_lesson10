package ru.gb.rest.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.gb.rest.dto.OrderDto;


@FeignClient(url = "localhost:8735/order", value = "orderGateway")
public interface OrderGateway {

    @GetMapping(produces = "application/json;charset=UTF-8")
    OrderDto showOrder();

    @GetMapping(value = "/create", produces = "application/json;charset=UTF-8")
    OrderDto create();

    @DeleteMapping(value = "/delete", produces = "application/json;charset=UTF-8")
    void delete();

    @PutMapping("/addToOrder" + "/{productId}")
    OrderDto addToOrder(@PathVariable("productId") Long id, @RequestParam(value = "quantity", required = false) Integer quantity);


    @PutMapping("/deleteFromOrder" + "/{productId}")
    OrderDto deleteFromOrder(@PathVariable("productId") Long id);


}
