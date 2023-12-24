package com.example.sintegracebusca.service;

import com.example.sintegracebusca.domain.Pagamento;
import com.example.sintegracebusca.dto.PagamentoDTO;
import com.example.sintegracebusca.mapper.PagamentoMapper;
import com.example.sintegracebusca.repository.PagamentoRepository;
import com.example.sintegracebusca.util.DateUtil;
import com.example.sintegracebusca.util.FormatarValor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper mapper;
    private final CompraService compraService;


    public Pagamento save(PagamentoDTO pagamentoDTO) {
        FormatarValor.formatar(pagamentoDTO.getValor());

        var pagamento = mapper.dtoToPagamento(pagamentoDTO);

        if(nonNull(pagamentoDTO.getCompraId()))
            pagamento.setCompra(compraService.findById(pagamentoDTO.getCompraId()));

        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> listarPagamentos(String mes) {
        LocalDate primeiroDiaPesquisa = DateUtil.getPrimeiroDiaMes(mes);
        LocalDate ultimoDiaPesquisa = DateUtil.getUltimoDiaMes(mes);

        return pagamentoRepository.findByDataPagamentoMonth(primeiroDiaPesquisa, ultimoDiaPesquisa);
    }

    public Double pagamentosDashboard() {
        List<Pagamento> byDataPagamentoMonth = pagamentoRepository.findByDataPagamentoMonth(LocalDate.now().minusMonths(1), LocalDate.now().plusDays(1));

        return byDataPagamentoMonth.stream()
            .mapToDouble(Pagamento::getValor).sum();
    }

    public Pagamento findById(Long id) {
        return pagamentoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
}
