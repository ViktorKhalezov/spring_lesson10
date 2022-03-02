package ru.gb.orderapp;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    @JsonProperty(value = "id")
    private Long orderId;

    private BigDecimal sum;

    private Map<ProductDto, Integer> products;




    public void addProduct(ProductDto productDto) {
        int quantity = 0;
        if(products.containsKey(productDto)) {
            quantity = products.get(productDto) + 1;
        } else {
            quantity = 1;
        }
        products.put(productDto, quantity);
        sum = new BigDecimal(sum.doubleValue() + productDto.getCost().doubleValue() * quantity);
    }

    public void deleteProduct(ProductDto productDto) {
        if(products.containsKey(productDto)) {
            sum = new BigDecimal(sum.doubleValue() + productDto.getCost().doubleValue() * products.get(productDto));
            products.remove(productDto);
        }
    }


}
