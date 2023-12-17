package com.example.sintegracebusca.service;

import com.example.sintegracebusca.domain.Compra;
import com.example.sintegracebusca.repository.CompraRepository;
import com.example.sintegracebusca.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class CompraService {
    private final CompraRepository compraRepository;

    public Compra save(Compra compra) {
        if (nonNull(compra.getProdutos())) {
            compra.setValor(
                compra.getProdutos().stream()
                    .mapToDouble(produto -> produto.getPreco() * produto.getQuantidade())
                    .sum()
            );
        } else {
            compra.setValor(0D);
        }

        return compraRepository.save(compra);
    }

    public Compra findById(Long id) {
        return compraRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    public List<Compra> listarCompras(String mes) {
        LocalDate primeiroDiaPesquisa = DateUtil.getPrimeiroDiaMes(mes, LocalDate.now().minusMonths(1));
        LocalDate ultimoDiaPesquisa = DateUtil.getUltimoDiaMes(mes);

        return compraRepository.findByDataPagamentoMonth(primeiroDiaPesquisa, ultimoDiaPesquisa);
    }

    public List<Compra> comprasNaoAgendadas() {
        return compraRepository.findByDataPagamentoMonth(LocalDate.now().minusMonths(1), LocalDate.now()).stream()
            .filter(compra -> !compra.getAgendado())
            .collect(Collectors.toList());
    }
}
