package com.example.sintegracebusca.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Value
@Builder
public class AgendamentoReturnDTO {

    Long id;
    //TODO so numeros, virgula e ponto devem ser permitidos na string
    @NotNull
    String valor;
    @NotNull
    String fornecedor;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate dataPagamento;
    Long compraId;
    Boolean pago;

}
