package de.seven.fate.model.builder.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

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

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getGiftWrap() {
        return giftWrap;
    }

    public void setGiftWrap(Boolean giftWrap) {
        this.giftWrap = giftWrap;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String[] getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String[] descriptions) {
        this.descriptions = descriptions;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
