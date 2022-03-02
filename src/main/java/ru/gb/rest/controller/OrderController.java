package ru.gb.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.rest.dto.OrderDto;
import ru.gb.rest.service.OrderGateway;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderGateway orderGateway;

    @GetMapping
    public OrderDto showOrder() {
        return orderGateway.showOrder();
    }

    @GetMapping("/create")
    public OrderDto create() {
        return orderGateway.create();
    }


   @DeleteMapping("/delete")
    public void delete() {
        orderGateway.delete();
   }

    @PutMapping("/addToOrder" + "/{productId}")
    OrderDto addToOrder(@PathVariable("productId") Long id, @RequestParam(value = "quantity", required = false) Integer quantity) {
        return orderGateway.addToOrder(id, quantity);
    }

    @PutMapping("/deleteFromOrder" + "/{productId}")
    OrderDto deleteFromOrder(@PathVariable("productId") Long id) {
        return orderGateway.deleteFromOrder(id);
    }


}

