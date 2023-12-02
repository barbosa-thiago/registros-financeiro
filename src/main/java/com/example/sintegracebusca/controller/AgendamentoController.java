package com.example.sintegracebusca.controller;

import com.example.sintegracebusca.domain.Agendamento;
import com.example.sintegracebusca.dto.AgendamentoTotalDia;
import com.example.sintegracebusca.dto.DataTotalPagamentos;
import com.example.sintegracebusca.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Controller
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping("/agendamentos")
    public String save(Model model) {
        model.addAttribute("agendamento", new Agendamento());
        return "agendamento/agendamento";
    }

    @PostMapping("/agendamentos")
    public String save(@ModelAttribute Agendamento agendamento, BindingResult bindingResult, Model model) {
        var agendamentoSalvo = agendamentoService.save(agendamento);
        if (isNull(agendamentoSalvo))
            return "erro";
        model.addAttribute("agendamento", agendamentoSalvo);
        return "agendamento/agendamentoResult";
    }

    @GetMapping("/agendamentos/list")
    public String listarPagamentos(Model model) {
        var agendamentoMap = agendamentoService.listAgendamento();
        model.addAttribute("somaMap", agendamentoMap);


//        model.addAttribute("totalPages", agendamentoMap.getTotalPages());
//        model.addAttribute("pageNumber", agendamentoMap.getNumber());
//        model.addAttribute("hasNext", agendamentoMap.hasNext());
//        model.addAttribute("totalItems", agendamentoMap.getTotalElements());
        return "agendamento/agendamentosDoMes";
    }

    @PostMapping("/agendamentos/update/{id}")
    public String update(@PathVariable Long id, @RequestParam boolean pago) {
        agendamentoService.update(id, pago);
        return "redirect:/agendamentos/list";
    }

}