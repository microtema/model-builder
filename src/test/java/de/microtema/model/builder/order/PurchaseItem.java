package de.microtema.model.builder.order;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class PurchaseItem extends IdBean {

    private static final AtomicInteger INDEX = new AtomicInteger();

    private String partNumber;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private Boolean giftWrap;
    private LocalDateTime localDateTime;
    private String[] descriptions;
    private int rate;
}
