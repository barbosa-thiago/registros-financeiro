

function appendChildToDiv() {
    let childListContainer = $('#produto-list-container');
    $("#produto-label").removeAttr("hidden");
    $("#total-container").css("visibility", "visible");

    childListContainer.append(
        `<div id="produto-input" class="produto-inputs mb-3 row justify-content-start">
    
            <div class="col">
                <input id="nome-produto-display" class="form-control" type="text" required="true" />
            </div>
            <div class="col-2">
                <input id="quantidade-produto-display" 
                class="form-control produto-input-altera-quant input-altera-valor only-numbers" 
                type="text" required="true" />
            </div>
            <div class="col-2">
                <input id="preco-produto-display" 
                class="form-control produto-input-altera-preco input-altera-valor currency-input" 
                type="text" required="true" />
            </div>
            <div class="col-2">
                <input id="subtotal-produto-display" class="form-control subtotal only-numbers" onchange="updateTotal()" type="text" required="true" readonly/>
            </div>
        </div>`
    )
}

function postProduto() {
    const compra = {
        descricao: document.getElementById("compra-descricao-display").value,
        fornecedor: document.getElementById("compra-fornecedor-display").value,
        produtos: []
    }
    let produtoInputs = document.getElementsByClassName("produto-inputs");

    Array.from(produtoInputs).forEach((el => {
            compra.produtos.push(
                {
                    nome: el.querySelector("#nome-produto-display").value,
                    preco: el.querySelector("#preco-produto-display").value,
                    quantidade: el.querySelector("#quantidade-produto-display").value,
                }
            );
        })
    )

    $.ajax({
        type: 'POST',
        url: '/compras',
        contentType: 'application/json',
        data: JSON.stringify(compra),
        success: function (response) {
            console.log('Success:', response);
            window.location.href = "compras/list"
        },
        error: function (error) {
            console.error('Error:', error);
        }
    });
}

