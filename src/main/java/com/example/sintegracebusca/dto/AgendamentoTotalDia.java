package com.example.sintegracebusca.dto;

import com.example.sintegracebusca.domain.Agendamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class AgendamentoTotalDia {
    List<Agendamento> agendamentos;
    DataTotalPagamentos dataTotalPagamentos;
}
