package com.example.sintegracebusca.controller;

import com.example.sintegracebusca.domain.Cliente;
import com.example.sintegracebusca.domain.Income;
import com.example.sintegracebusca.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("photo")
@Slf4j
@RequiredArgsConstructor
public class PhotoController {

    private final ClienteRepository clienteRepository;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Income> save(Income income) {

        log.info("payload {}", income);

        return ResponseEntity.ok(income);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<?> getClient(@PathVariable String cnpj) {
        var map = new HashMap<String, String>();
        map.put("tipodocumento", "2");
        map.put("numcnpjcgf", cnpj);

        var url = "https://internet-consultapublica.apps.sefaz.ce.gov.br/sintegra/consultar?tipdocumento={tipodocumento}&numcnpjcgf={numcnpjcgf}";
        var restTemplate = new RestTemplate();
        var htmlResponse = restTemplate.getForObject(url, String.class, map);

        var data = htmlResponse.split("<b>IDENTIFICAÇÃO</b></th>")[1]
            .split("<th><b>INFORMAÇÕES COMPLEMENTARES</b></th>")[0];

        var variableNames = new ArrayList<String>();

        for (String temp : data.split("<th>")) {
            variableNames.addAll(List.of(temp.split("</th>")));
        }

        List<String> variables = variableNames.stream()
            .filter(value -> !value.contains("<") && !value.contains("\n"))
            .collect(Collectors.toList());

        var finalValues = new ArrayList<String>();

        variableNames.stream()
            .filter(value -> value.contains("<"))
            .forEach(v -> {
                if (v.contains("<td>")) {
                    for (String temp : v.split("<td>")) {
                        var finalValue = temp.split("</td>")[0];
                        if(!finalValue.contains("\n"))
                            finalValues.add(temp.split("</td>")[0]);
                    }
                }
            });

        var finalResult = new HashMap<String, String>();
        for (int i = 0; i < variables.size(); i++) {
            finalResult.put(variables.get(i), finalValues.get(i));
        }

        return ResponseEntity.ok(finalResult);
    }

    @GetMapping("/client")
    public ResponseEntity<List<Cliente>> getClients() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }
}
