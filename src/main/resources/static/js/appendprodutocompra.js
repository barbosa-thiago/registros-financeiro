function appendChildToDiv() {
    let childListContainer = $('#produto-list-container');
    $("#produto-label").removeAttr("hidden");
    $("#total-container").css("visibility", "visible");

    childListContainer.append(
        `<div id="produto-input" class="produto-inputs mb-3 row justify-content-start">
    
            <div class="col px-0">
                <input id="nome-produto-display" name="nome-produto" class="form-control" type="text" required />
            </div>
            <div class="col-2 px-1">
                <input id="quantidade-produto-display" 
                class="produto-prop form-control produto-input-altera-quant input-altera-valor only-numbers" 
                type="text" required />
            </div>
            <div class="col-2 px-1">
                <input id="preco-produto-display" 
                class="produto-prop form-control produto-input-altera-preco input-altera-valor currency-input" 
                type="text" required />
            </div>
            <div class="col-2 px-1">
                <input id="produto-prop subtotal-produto-display" class="form-control subtotal only-numbers" onchange="updateTotal()" type="text" readonly/>
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
                    preco: el.querySelector("#preco-produto-display").value.replace(",", "."),
                    quantidade: el.querySelector("#quantidade-produto-display").value,
                }
            );
        })
    )

    if (validateForm()) {

        $.ajax({
            type: 'POST',
            url: '/compras',
            contentType: 'application/json',
            data: JSON.stringify(compra),
            success: function (response) {
                console.log('Compra registrada');
                window.location.href = "compras/list"
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    }

}

function validateForm() {
    let isValid = true;

    $('#form-prod [required]').each(function () {
        if (!$(this).val()) {
            const fieldName = $(this).attr('name');
            alert(`Favor preencher ${fieldName}`);
            isValid = false;
            return false;
        }
    });
    return isValid;
}

