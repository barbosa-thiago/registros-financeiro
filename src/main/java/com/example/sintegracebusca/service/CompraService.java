package com.example.sintegracebusca.service;

import com.example.sintegracebusca.domain.Compra;
import com.example.sintegracebusca.repository.CompraRepository;
import com.example.sintegracebusca.util.DateUtil;
import com.example.sintegracebusca.util.FormatarValor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompraService {
    private final CompraRepository compraRepository;

    public Compra save(Compra compra) {
        log.info(" compra:  {}", compra);

        if (nonNull(compra.getProdutos())) {
            double sum = compra.getProdutos().stream()
                .mapToDouble(produto -> produto.getPreco() * produto.getQuantidade())
                .sum();
            compra.setValor(sum);

        }
        FormatarValor.formatar(compra);

        log.info("Salvando compra com valor: {}", compra.getValor());
        return compraRepository.save(compra);
    }

    public Compra findById(Long id) {
        return compraRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    public List<Compra> listarCompras(String mes) {
        LocalDate primeiroDiaPesquisa = DateUtil.getPrimeiroDiaMes(mes);
        LocalDate ultimoDiaPesquisa = DateUtil.getUltimoDiaMes(mes);

        return compraRepository.findByDataCompraMonth(primeiroDiaPesquisa, ultimoDiaPesquisa);
    }

    public List<Compra> comprasNaoAgendadas() {
        return compraRepository.findByDataCompraMonth(LocalDate.now().minusMonths(1), LocalDate.now().plusDays(1)).stream()
            .filter(compra -> !compra.getAgendado())
            .collect(Collectors.toList());
    }
}
