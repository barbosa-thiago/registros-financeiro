function submitChildForm() {
    let prodNome = document.getElementById('nome-produto-modal').value;
    let prodQuantidade = document.getElementById('quantidade-produto-modal').value;
    let prodPreco = document.getElementById('preco-produto-modal').value;

    console.log("preco: "+prodPreco);

    let produto = {
        name: prodNome,
        quantidade: prodQuantidade,
        preco: prodPreco
    };

    appendChildToList(produto);
}

function appendChildToList(produto) {
    // Code to dynamically add the child object to the list

    let childListContainer = $('#produto-list-container-ul');

    let li = $('<li></li>');

    console.log("nome: " + produto.name);

    li.append(
        `<div class="result mb-3">
            <label class="form-label">Nome</label>
            <input id="nome-produto-display" class="form-control" type="text" required="true" value=${produto.name} th:field="*{produto.nome}"/>
        </div>`
    );

    childListContainer.append(li);
}
