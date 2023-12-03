package com.example.sintegracebusca.service;

import com.example.sintegracebusca.domain.Agendamento;
import com.example.sintegracebusca.dto.AgendamentoTotalDia;
import com.example.sintegracebusca.dto.DataTotalPagamentos;
import com.example.sintegracebusca.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento save(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public TreeMap<LocalDate, AgendamentoTotalDia> listAgendamento(String mes) {

        LocalDate primeiroDiaPesquisa = null;
        LocalDate ultimoDiaPesquisa = null;
        Month month = null;
        if (nonNull(mes)) {
            month = Month.valueOf(Month.class, mes.toUpperCase());
            primeiroDiaPesquisa = LocalDate.of(Year.now().getValue(), month, 1);
            ultimoDiaPesquisa = LocalDate.of(Year.now().getValue(), month, month.length(Year.isLeap(YearMonth.now().getYear())));
        } else {
            primeiroDiaPesquisa = LocalDate.now().minusDays(3);
            ultimoDiaPesquisa = LocalDate.now().plusDays(45);
        }



        var agendamentoMap = new TreeMap<>(agendamentoRepository.findByDataPagamentoMonth(primeiroDiaPesquisa, ultimoDiaPesquisa)
            .stream()
            .collect(Collectors.groupingBy(Agendamento::getDataPagamento)));

        var agendamentoTotalDia = new TreeMap<LocalDate, AgendamentoTotalDia>();


        agendamentoMap.forEach((key, value) -> {
            double somaDoDia = value.stream()
                .mapToDouble(Agendamento::getValor).sum();
            agendamentoTotalDia.put(
                key,
                new AgendamentoTotalDia(
                    value,
                    new DataTotalPagamentos(key, somaDoDia)
                )
            );
        });

        return agendamentoTotalDia;
    }

    public Agendamento update(Long id, boolean pago) {
        Agendamento agendamento = findByID(id);
        agendamento.setPago(pago);
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento findByID(Long id) {
        return agendamentoRepository.findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agendamento nao encontrado"));
    }
}
