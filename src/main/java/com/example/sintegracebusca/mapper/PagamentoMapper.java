package com.example.sintegracebusca.mapper;

import com.example.sintegracebusca.domain.Pagamento;
import com.example.sintegracebusca.dto.PagamentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {

    @Mapping(source = "compraId", target = ".", ignore = true)
    @Mapping(source = "agendamentoId", target = ".", ignore = true)
    @Mapping(source = "valor", target = "valor", qualifiedByName = "valorMapper")
    Pagamento dtoToPagamento(PagamentoDTO pagamentoDTO);

    @Named("valorMapper")
    default double valorMapper(String valor) {

        var valorAjustado = valor.replace(",", "");
        var bigDecimal = BigDecimal.valueOf(Double.valueOf(valorAjustado))
            .setScale(2, RoundingMode.HALF_UP);

        return bigDecimal.doubleValue();
    }
}
