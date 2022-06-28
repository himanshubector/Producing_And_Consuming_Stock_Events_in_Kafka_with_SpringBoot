package com.bector.springbootkafka.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StockEvent
{
    private Integer stockEventId;

    private StockEventType stockEventType;

    @NotNull
    @Valid
    private Stock stock;

}
