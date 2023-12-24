//TODO o metodo ficara num arquivo separado do HTML?
$(document.body).on('input', ".input-altera-valor", function (event) {

    console.log("fired no documento");
    let elementoPai = $(event.target).parent().parent();

    let prodQuant = elementoPai.find(".produto-input-altera-quant");
    let prodPreco = elementoPai.find(".produto-input-altera-preco");

    console.log(prodPreco.val().replace(",", "."));

    let subtotal = (prodPreco.val().replace(",", ".") * prodQuant.val());
    elementoPai.find(".subtotal").val(roundTwoDecimalPlaces(subtotal));


    let soma = 0;
    Array.from($(".subtotal")).forEach((el) => soma += parseFloat(el.value));

    $("#total").val(roundTwoDecimalPlaces(soma));

});
