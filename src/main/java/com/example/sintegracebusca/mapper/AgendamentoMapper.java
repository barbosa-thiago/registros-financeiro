package com.example.sintegracebusca.mapper;

import com.example.sintegracebusca.domain.Agendamento;
import com.example.sintegracebusca.dto.AgendamentoDTO;
import com.example.sintegracebusca.dto.AgendamentoReturnDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper {

    @Mapping(source = "compraId", target = ".", ignore = true)
    @Mapping(source = "valor", target = "valor", qualifiedByName = "valorToDoubleMapper")
    Agendamento dtoToAgendamento(AgendamentoDTO agendamentoDTO);

    @Mapping(target = "compraId", source = "compra.id")
    AgendamentoDTO agendamentoToDto(Agendamento agendamento);

    @IterableMapping(elementTargetType = Agendamento.class)
    List<AgendamentoReturnDTO> agendamentosToDtos(List<Agendamento> agendamentos);

    @Named("valorToDoubleMapper")
    default double valorMapper(String valor) {

        var valorAjustado = valor.replace(",", "");
        var bigDecimal = BigDecimal.valueOf(Double.parseDouble(valorAjustado))
            .setScale(2, RoundingMode.HALF_UP);

        return bigDecimal.doubleValue();
    }
}
