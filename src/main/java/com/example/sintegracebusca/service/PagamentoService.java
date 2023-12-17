package com.example.sintegracebusca.service;

import com.example.sintegracebusca.domain.Pagamento;
import com.example.sintegracebusca.repository.PagamentoRepository;
import com.example.sintegracebusca.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;

    public Pagamento save(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> listarPagamentos(String mes) {
        LocalDate primeiroDiaPesquisa = DateUtil.getPrimeiroDiaMes(mes);
        LocalDate ultimoDiaPesquisa = DateUtil.getUltimoDiaMes(mes);

        return pagamentoRepository.findByDataPagamentoMonth(primeiroDiaPesquisa, ultimoDiaPesquisa);
    }

    public Double pagamentosDashboard(String mes) {

        return pagamentoRepository.findByDataPagamentoMonth(LocalDate.now().minusMonths(1), LocalDate.now()).stream()
            .mapToDouble(Pagamento::getValor).sum();
    }
}
