package com.example.sintegracebusca.dto;

import com.example.sintegracebusca.enums.TipoPagamento;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Getter
@Value
@Builder
public class PagamentoDTO {

    @NotNull
    String valor;
    @NotNull
    String descricao;
    @NotNull
    TipoPagamento tipo;
    Long compraId;
    Long agendamentoId;

}
