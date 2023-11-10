package com.example.sintegracebusca.service;

import com.example.sintegracebusca.component.SintegraComponent;
import com.example.sintegracebusca.domain.Cliente;
import com.example.sintegracebusca.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final SintegraComponent sintegraComponent;


    public Cliente save(String cnpj) {

        var cliente = clienteRepository.findByCnpj(cnpj).orElseGet(() -> {
            var clienteMap = sintegraComponent.buscaCliente(cnpj);

            return Cliente.builder()
                .complemento(clienteMap.get("Complemento"))
                .razaoSocial(clienteMap.get("Nome/Razão Social"))
                .logradouro(clienteMap.get("Logradouro"))
                .cnpj(cnpj)
                .telefone(clienteMap.get("Telefone"))
                .bairro(clienteMap.get("Bairro"))
                .cep(clienteMap.get("CEP"))
                .municipio(clienteMap.get("Município"))
                .numero(clienteMap.get("Número"))
                .inscricaoEstadual(clienteMap.get("Inscrição Estadual:"))
                .uf(clienteMap.get("UF"))
                .build();

        });
        return clienteRepository.save(cliente);

    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
}
