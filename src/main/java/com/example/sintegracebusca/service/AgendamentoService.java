package com.example.sintegracebusca.service;

import com.example.sintegracebusca.domain.Agendamento;
import com.example.sintegracebusca.dto.*;
import com.example.sintegracebusca.mapper.AgendamentoMapper;
import com.example.sintegracebusca.repository.AgendamentoRepository;
import com.example.sintegracebusca.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgendamentoService {

    private final PagamentoService pagamentoService;
    private final CompraService compraService;
    private final AgendamentoMapper mapper;

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento save(AgendamentoDTO agendamentoDTO) {
        var agendamento = mapper.dtoToAgendamento(agendamentoDTO);
        log.info("agendamento: {}", agendamento);

        if(nonNull(agendamentoDTO.getCompraId())) {
            agendamento.setCompra(compraService.findById(agendamentoDTO.getCompraId()));
        }
        return agendamentoRepository.save(agendamento);
    }

    public TreeMap<LocalDate, AgendamentoTotalDia> listAgendamento(String mes) {

        LocalDate primeiroDiaPesquisa = DateUtil.getPrimeiroDiaMes(mes);
        LocalDate ultimoDiaPesquisa = DateUtil.getUltimoDiaMes(mes);

        List<Agendamento> agendamentoList = agendamentoRepository.findByDataPagamentoMonth(
            primeiroDiaPesquisa, ultimoDiaPesquisa
        );

        List<AgendamentoReturnDTO> agendamentoDTOS = mapper.agendamentosToDtos(agendamentoList);
        var agendamentoMap = new TreeMap<>(agendamentoDTOS
            .stream()
            .collect(Collectors.groupingBy(AgendamentoReturnDTO::getDataPagamento)));

        var agendamentoTotalDia = new TreeMap<LocalDate, AgendamentoTotalDia>();


        agendamentoMap.forEach((key, value) -> {
            double somaDoDia = value.stream()
                .mapToDouble(this::stringToDouble).sum();
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
    public Agendamento update(Long id, PagamentoDTO pagamento) {
        Agendamento agendamento = findByID(id);
        if(nonNull(agendamento.getPagamento())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agendamento já foi pago");
        }
        var pagamentoSalvo = pagamentoService.save(pagamento);
        agendamento.setPago(true);
        agendamento.setPagamento(pagamentoSalvo);
        return agendamentoRepository.save(agendamento);
    }

    public Double agendamentoDashboard() {
        return agendamentoRepository.findByDataPagamentoMonth(LocalDate.now(), LocalDate.now().plusMonths(1)).stream()
            .mapToDouble(Agendamento::getValor).sum();
    }

    public Agendamento findByID(Long id) {
        return agendamentoRepository.findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agendamento nao encontrado"));
    }

    private Double stringToDouble(AgendamentoReturnDTO agendamentoDTO) {
        return Double.parseDouble(agendamentoDTO.getValor());
    }
}
