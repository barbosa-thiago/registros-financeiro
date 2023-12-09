function submitChildForm() {
    let prodNome = document.getElementById('nome-produto-modal').value;
    let prodQuantidade = document.getElementById('quantidade-produto-modal').value;
    let prodPreco = document.getElementById('preco-produto-modal').value;

    console.log("preco: " + prodPreco);

    let produto = {
        name: prodNome,
        quantidade: prodQuantidade,
        preco: prodPreco
    };

    // console.log("limpando valores");
    // $('#produto-modal').on('hidden.bs.modal',  () => {
    //     $(this).find('form').trigger('reset');
    // });
    appendChildToDiv(produto);
}

function appendChildToDiv() {
    let childListContainer = $('#produto-list-container');
    $("#produto-label").removeAttr("hidden");

    childListContainer.append(
        `<div id="produto-input" class="mb-3 row justify-content-start">
    
            <div class="col">
                <input id="nome-produto-display" class="form-control" type="text" required="true" />
            </div>
            <div class="col-2">
                <input id="quantidade-produto-display" 
                class="form-control produto-input-altera-quant input-altera-valor" 
                type="text" required="true" />
            </div>
            <div class="col-2">
                <input id="preco-produto-display" 
                class="form-control produto-input-altera-preco input-altera-valor" 
                type="text" required="true" />
            </div>
            <div class="col-2">
                <input id="subtotal-produto-display" class="form-control subtotal" type="text" required="true" readonly/>
            </div>
        </div>`
    )
}

function postProduto() {
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
        success: function (response) {
            console.log('Success:', response);
            console.log('Fornecedor:', response.fornecedor);
            window.location.href = "compras/list"
        },
        error: function (error) {
            // Handle error response
            console.error('Error:', error);
        }
    });
}

