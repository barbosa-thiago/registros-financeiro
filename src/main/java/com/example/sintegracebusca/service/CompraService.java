package com.example.sintegracebusca.service;

import com.example.sintegracebusca.domain.Compra;
import com.example.sintegracebusca.enums.TipoPagamento;
import com.example.sintegracebusca.repository.CompraRepository;
import com.example.sintegracebusca.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraService {
    private final CompraRepository compraRepository;

    public Compra save(Compra compra) {
        return compraRepository.save(compra);
    }

    public List<Compra> listarCompras(String mes) {
        LocalDate primeiroDiaPesquisa = DateUtil.getPrimeiroDiaMes(mes, LocalDate.now().minusMonths(1));
        LocalDate ultimoDiaPesquisa = DateUtil.getUltimoDiaMes(mes);

        return compraRepository.findByDataPagamentoMonth(primeiroDiaPesquisa, ultimoDiaPesquisa);
    }
}
