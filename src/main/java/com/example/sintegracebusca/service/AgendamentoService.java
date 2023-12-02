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
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento save(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public TreeMap<LocalDate, AgendamentoTotalDia> listAgendamento() {

        var agendamentoMap = new TreeMap<>(agendamentoRepository.findAll()
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
