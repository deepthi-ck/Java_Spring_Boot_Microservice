package com.example.order.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderRequest {
    @NotNull
    private Long customerId;
    @NotEmpty
    @Valid
    private List<OrderLineRequest> lines;

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public List<OrderLineRequest> getLines() { return lines; }
    public void setLines(List<OrderLineRequest> lines) { this.lines = lines; }
}