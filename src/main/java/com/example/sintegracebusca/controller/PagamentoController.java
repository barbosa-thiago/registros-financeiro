package com.example.sintegracebusca.controller;

import com.example.sintegracebusca.domain.Agendamento;
import com.example.sintegracebusca.domain.Pagamento;
import com.example.sintegracebusca.enums.TipoPagamento;
import com.example.sintegracebusca.service.AgendamentoService;
import com.example.sintegracebusca.service.CompraService;
import com.example.sintegracebusca.service.PagamentoService;
import com.example.sintegracebusca.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static java.util.Objects.isNull;

@Controller
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoService pagamentoService;
    private final CompraService compraService;
    private final AgendamentoService agendamentoService;

    @GetMapping("/pagamentos")
    public String save(Model model) {
        model.addAttribute("pagamento", new Pagamento());
        model.addAttribute("compras", compraService.listarCompras());

        return "pagamento/pagamento";
    }


    @PostMapping("/pagamentos")
    public String save(@ModelAttribute Pagamento pagamento, BindingResult bindingResult, Model model) {
        var pagamentoSalvo = pagamentoService.save(pagamento);
        if(isNull(pagamentoSalvo))
            return "erro";
        model.addAttribute("pagamento", pagamentoSalvo);
        return "pagamento/pagamentoResult";
    }

    @PostMapping("/pagamentos/agendado/{agendamentoId}")
    public String saveAgendado(@PathVariable Long agendamentoId,
                               @ModelAttribute Agendamento agendamento,
                               BindingResult bindingResult,
                               Model model) {
        var agendamentoUpdate = agendamentoService.update(agendamentoId, !agendamento.getPago());

        var pagamento = Pagamento.builder()
            .dataPagamento(LocalDate.now())
            .tipo(TipoPagamento.AGENDADO)
            .valor(agendamentoUpdate.getValor())
            .agendamento(agendamentoUpdate)
            .descricao(agendamentoUpdate.getFornecedor())
            .build();

        var pagamentoSalvo = pagamentoService.save(pagamento);
        if(isNull(pagamentoSalvo))
            return "erro";
        return "redirect:/agendamentos/list";
    }

    @GetMapping("pagamentos/list")
    public String listarPagamentos(Model model, @RequestParam(required = false) String mes) {
        model.addAttribute("pagamentos", pagamentoService.listarPagamentos(mes));
        model.addAttribute("meses", DateUtil.listarMeses());
        return "pagamento/pagamentosDoMes";
    }
}
