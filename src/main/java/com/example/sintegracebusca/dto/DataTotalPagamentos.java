package com.example.sintegracebusca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Value
@Builder
public class DataTotalPagamentos {
    LocalDate data;
    Double somaDoDia;
}
