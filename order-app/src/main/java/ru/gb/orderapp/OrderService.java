package ru.gb.orderapp;


import lombok.Getter;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@Service
@Getter
public class OrderService {

    private OrderDto orderDto;

    private final ArrayList<ProductDto> products = new ArrayList<>(); // Вместо ProductDao

    OrderService() {
        products.add(new ProductDto(1L, "Plane", new BigDecimal("15000.00"), LocalDate.of(2020, 10, 13), "Boing"));
        products.add(new ProductDto(2L, "Car", new BigDecimal("2000.00"), LocalDate.of(2019, 9, 15), "Microsoft"));
        products.add(new ProductDto(3L, "Bus", new BigDecimal("5000.00"), LocalDate.of(2018, 8, 03), "Apple"));
        products.add(new ProductDto(4L, "Ship", new BigDecimal("10000.00"), LocalDate.of(2021, 7, 07), "Invest Bank"));
        products.add(new ProductDto(5L, "Train", new BigDecimal("12000.00"), LocalDate.of(2017, 6, 23), "Invest Company"));
    }


    public OrderDto create() {
        orderDto = OrderDto.builder()
                .orderId(1L)
                .sum(new BigDecimal(0))
                .products(new HashMap<>())
                .build();
        return orderDto;
    }


    public void delete() {
        orderDto.setOrderId(0L);
        orderDto.setProducts(new HashMap<>());
    }

    public boolean addToOrder(ProductDto productDto, Integer quantity) {
        if(products.contains(productDto)) {
            orderDto.getProducts().put(productDto, quantity);
            return true;
        }
        return false;
    }

    public boolean deleteFromOrder(ProductDto productDto) {
        if(orderDto.getProducts().containsKey(productDto)) {
            orderDto.getProducts().remove(productDto);
            return true;
        }
        return false;
    }


    public ProductDto findById(Long id) {
        for(int i = 0; i < products.size(); i++) {
            if(products.get(i).getId() == id) {
                return products.get(i);
            }
        }
        return null;
    }


}
