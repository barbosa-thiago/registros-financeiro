package com.example.sintegracebusca.controller;

import com.example.sintegracebusca.domain.Cliente;
import com.example.sintegracebusca.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sintegra")
@Slf4j
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/{cnpj}")
    public ResponseEntity<?> save(@PathVariable String cnpj) {
        return ResponseEntity.ok(clienteService.save(cnpj));
    }

    @GetMapping("/client")
    public ResponseEntity<List<Cliente>> getClients() {
        return ResponseEntity.ok(clienteService.findAll());
    }


}
