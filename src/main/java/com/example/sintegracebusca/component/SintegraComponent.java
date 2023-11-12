package com.example.sintegracebusca.component;

import com.example.sintegracebusca.domain.Cliente;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Component
public class SintegraComponent {

    public Cliente buscaCliente(String cnpj) {
        var map = new HashMap<String, String>();
        map.put("tipodocumento", "2");
        map.put("numcnpjcgf", cnpj);

        var url = "https://consultapublica.sefaz.ce.gov.br/sintegra/consultar?tipdocumento={tipodocumento}&numcnpjcgf={numcnpjcgf}";
        var restTemplate = new RestTemplate();
        var htmlResponse = restTemplate.getForObject(url, String.class, map);

        if(htmlResponse.contains("Nenhum contribuinte encontrado")) {
            return null;
        }
        var document = Jsoup.parse(htmlResponse);
        var dadosSintegra = document.select("#dadossintegra")
            .select("td")
            .eachText();

        var enderecoSintegra = document.select("#enderecosintegara").first()
            .select("td")
            .eachText();

        var numeroComplemento = document.select("#enderecosintegara").first()
            .select("tr")
            .get(2)
            .select("td")
            .eachText();

        var bairro = document.select("#enderecosintegara").first()
            .select("tr")
            .get(3)
            .select("td")
            .eachText();

        var municipioUf = document.select("#enderecosintegara").first()
            .select("tr")
            .get(4)
            .select("td")
            .eachText();

        var cepTelefone = document.select("#enderecosintegara").first()
            .select("tr")
            .get(5)
            .select("td")
            .eachText();



        List<String> outrosDados = document.select("#enderecosintegara").last()
            .select("td")
            .eachText();

        var complemento = numeroComplemento.size() > 1 ?
            numeroComplemento.get(1) : null;

        var cliente = Cliente.builder()

            .cnpj(dadosSintegra.get(0))
            .inscricaoEstadual(dadosSintegra.get(1))
            .razaoSocial(dadosSintegra.get(2))
            .logradouro(enderecoSintegra.get(0))
            .numero(numeroComplemento.get(0))
            .complemento(complemento)
            .bairro(bairro.get(0))
            .municipio(municipioUf.get(0))
            .uf(municipioUf.get(1))
            .cep(cepTelefone.get(0))
            .telefone(cepTelefone.get(1))
            .cnae(outrosDados.get(0))
            .inicioAtividade(LocalDate.parse(outrosDados.get(3)))
            .build();


        return cliente;
    }
}
