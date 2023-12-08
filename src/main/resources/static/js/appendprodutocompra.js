let count = 0
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

    let childListContainer = $('#produto-list-container');

    console.log("nome: " + produto.name);

    childListContainer.append(
        `<div class="col">
            <label class="form-label">Produto</label>
            <input id="nome-produto-display" class="form-control" type="text" required="true" value="${produto.name}"/>
        </div>
        <div class="col-2">
            <label class="form-label">Quantidade</label>
            <input id="quantidade-produto-display" class="form-control" type="text" required="true" value="${produto.quantidade}"/>
        </div>
        <div class="col-2">
            <label class="form-label">Preco</label>
            <input id="preco-produto-display" class="form-control" type="text" required="true" value="${produto.preco}"/>
        </div>
        <div class="col-2">
            <label class="form-label">Subtotal</label>
            <input id="subtotal-produto-display" class="form-control" type="text" required="true" value="${produto.preco * produto.quantidade}"/>
        </div>`
    );

    console.log(count)

    console.log("limpando valores");
    $('#produto-modal').on('hidden.bs.modal', function () {
        $(this).find('form').trigger('reset');
    });

    count++;
}

function getValues() {
    console.log("chamando funcao");
    console.log(document.getElementById("nome-produto-display").value);

    const compra = {
        descricao: document.getElementById("compra-descricao-display").value,
        fornecedor: document.getElementById("compra-fornecedor-display").value,
        produtos: [
            {
                nome: document.getElementById("nome-produto-display").value,
                preco: document.getElementById("preco-produto-display").value,
                quantidade: document.getElementById("quantidade-produto-display").value,
            }
        ]
    }
    
    
    console.log(compra)

    $.ajax({
        type: 'POST',
        url: '/compras', // Replace with your actual endpoint
        contentType: 'application/json',
        data: JSON.stringify(compra),
        success: function(response) {
            console.log('Success:', response);

        },
        error: function(error) {
            // Handle error response
            console.error('Error:', error);
        }
    });
}