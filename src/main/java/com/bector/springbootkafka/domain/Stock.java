package com.bector.springbootkafka.domain;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Stock
{
    @NotNull
    private Integer stockId;

    @NotBlank
    private String stockName;

    @NotBlank
    private String stockDescription;

}
