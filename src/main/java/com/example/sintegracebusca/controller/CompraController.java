package com.example.sintegracebusca.controller;

import com.example.sintegracebusca.domain.Compra;
import com.example.sintegracebusca.domain.Produto;
import com.example.sintegracebusca.service.CompraService;
import com.example.sintegracebusca.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CompraController {
    private final CompraService compraService;

    @GetMapping("/compras")
    public String save(Model model) {
        var compra = new Compra();
        model.addAttribute("compra", new Compra());
        model.addAttribute("produto", new Produto());
        model.addAttribute("produtos", compra.getProdutos() );
        return "compra/compra";
    }


    @PostMapping("/compras")
    public ResponseEntity<Compra> save(@RequestBody Compra compra, BindingResult bindingResult, Model model) {
        var compraSalva = compraService.save(compra);

        model.addAttribute("compra", compra);
        model.addAttribute("produto", new Produto());
        return ResponseEntity.ok(compraSalva);
    }

    @GetMapping("/compras/list")
    public String listarPagamentos(Model model, @RequestParam(required = false) String mes) {
        model.addAttribute("compras", compraService.listarCompras(mes));
        model.addAttribute("meses", DateUtil.listarMeses());
        return "compra/comprasDoMes";
    }
}
