package com.example.sintegracebusca.service;

import com.example.sintegracebusca.domain.Agendamento;
import com.example.sintegracebusca.domain.Pagamento;
import com.example.sintegracebusca.dto.AgendamentoTotalDia;
import com.example.sintegracebusca.dto.DataTotalPagamentos;
import com.example.sintegracebusca.repository.AgendamentoRepository;
import com.example.sintegracebusca.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgendamentoService {

    private final PagamentoService pagamentoService;

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento save(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public TreeMap<LocalDate, AgendamentoTotalDia> listAgendamento(String mes) {

        LocalDate primeiroDiaPesquisa = DateUtil.getPrimeiroDiaMes(mes);
        LocalDate ultimoDiaPesquisa = DateUtil.getUltimoDiaMes(mes);

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

    @Transactional
    public Agendamento update(Long id, Pagamento pagamento) {
        Agendamento agendamento = findByID(id);
        if(nonNull(agendamento.getPagamento())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agendamento já foi pago");
        }
        var pagamentoSalvo = pagamentoService.save(pagamento);
        agendamento.setPago(true);
        agendamento.setPagamento(pagamentoSalvo);
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento findByID(Long id) {
        return agendamentoRepository.findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agendamento nao encontrado"));
    }
}
