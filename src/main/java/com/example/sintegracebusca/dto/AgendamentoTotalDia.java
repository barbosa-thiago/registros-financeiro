package com.example.sintegracebusca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class AgendamentoTotalDia {
    List<AgendamentoReturnDTO> agendamentos;
    DataTotalPagamentos dataTotalPagamentos;
}
