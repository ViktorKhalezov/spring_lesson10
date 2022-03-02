package ru.gb.orderapp;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public OrderDto showOrder() {
        return orderService.getOrderDto();
    }


    @GetMapping("/create")
    public OrderDto create() {
        return orderService.create();
    }

    @DeleteMapping("/delete")
    public void delete() {
        orderService.delete();
    }


   @PutMapping("/addToOrder" + "/{productId}")
   public OrderDto addToOrder(@PathVariable("productId") Long id, @RequestParam(value = "quantity", required = false) Integer quantity) {
       if(id != null) {
           ProductDto productDto = orderService.findById(id);
           if(productDto != null) {
               if(quantity == null) {
                   orderService.addToOrder(productDto, 1);
               } else {
                   orderService.addToOrder(productDto, quantity);
               }
           }
       }
        return orderService.getOrderDto();
   }


   @PutMapping("/deleteFromOrder" + "/{productId}")
   public OrderDto deleteFromOrder(@PathVariable("productId") Long id) {
       if(id != null) {
           ProductDto productDto = orderService.findById(id);
           if (productDto != null) {
               orderService.deleteFromOrder(productDto);
           }
       }
        return orderService.getOrderDto();
   }


}
