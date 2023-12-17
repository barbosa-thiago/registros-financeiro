package com.example.sintegracebusca.controller;

import com.example.sintegracebusca.domain.Compra;
import com.example.sintegracebusca.domain.Pagamento;
import com.example.sintegracebusca.service.AgendamentoService;
import com.example.sintegracebusca.service.CompraService;
import com.example.sintegracebusca.service.PagamentoService;
import com.example.sintegracebusca.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Controller
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoService pagamentoService;
    private final CompraService compraService;
    private final AgendamentoService agendamentoService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("pagamentos", pagamentoService.pagamentosDashboard(null));
        model.addAttribute("agendamento", agendamentoService.agendamentoDashboard());
        model.addAttribute("comprasNaoAgendadas", compraService.comprasNaoAgendadas().size());
        model.addAttribute("comprasUltimos30Dias", compraService.listarCompras(null).stream().mapToDouble(Compra::getValor).sum());

        return "dashboard";
    }

    @GetMapping("/pagamentos")
    public String save(Model model) {
        model.addAttribute("pagamento", new Pagamento());
        model.addAttribute("compras", compraService.listarCompras(null));

        return "pagamento/pagamento";
    }


    @PostMapping("/pagamentos")
    public String save(@RequestBody Pagamento pagamento, BindingResult bindingResult, Model model) {
        var pagamentoSalvo = pagamentoService.save(pagamento);
        if(isNull(pagamentoSalvo))
            return "erro";
        model.addAttribute("pagamento", pagamentoSalvo);
        return "pagamento/pagamentoResult";
    }

    @GetMapping("pagamentos/list")
    public String listarPagamentos(Model model, @RequestParam(required = false) String mes) {
        model.addAttribute("pagamentos", pagamentoService.listarPagamentos(mes));
        model.addAttribute("meses", DateUtil.listarMeses());
        return "pagamento/pagamentosDoMes";
    }
}
