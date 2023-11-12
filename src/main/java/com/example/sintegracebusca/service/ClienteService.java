package com.example.sintegracebusca.service;

import com.example.sintegracebusca.component.SintegraComponent;
import com.example.sintegracebusca.domain.Cliente;
import com.example.sintegracebusca.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final SintegraComponent sintegraComponent;


    public Cliente save(String cnpj) {

        return clienteRepository.findByCnpj(cnpj).orElseGet(() -> {
                var client = sintegraComponent.buscaCliente(cnpj);

                if (nonNull(client))
                    client = clienteRepository.save(client);

                return client;
            }
        );

    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
}
