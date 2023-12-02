package com.example.sintegracebusca.controller;

import com.example.sintegracebusca.domain.Cliente;
import com.example.sintegracebusca.domain.Income;
import com.example.sintegracebusca.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@ModelAttribute String cnpj) {
        return ResponseEntity.ok(clienteService.save(cnpj));
    }

    @GetMapping("/sintegra")
    public String sintegra(Model model) {
        model.addAttribute("income", new Income());
        return "sintegra/sintegra";
    }


    @PostMapping("/sintegra")
    public String findAndSave(@ModelAttribute Income income, Model model) {
        var cliente = clienteService.save(income.getCnpj());
        if(isNull(cliente))
            return "erro";
        model.addAttribute("cliente", cliente);
        return "sintegra/result";
    }

    @GetMapping("/client")
    public ResponseEntity<List<Cliente>> getClients() {
        return ResponseEntity.ok(clienteService.findAll());
    }


}
