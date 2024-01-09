
$(document).on("input", ".currency-input", function () {

    let value = this.value;
    value = value.replace('.', '').replace(',', '').replace(/\D/g, '');


    const options = { minimumFractionDigits: 2 }
    const inputDecimal = parseFloat(value) / 100;
    this.value = new Intl.NumberFormat('en-US', options).format(inputDecimal);
});



$(document).on('input', ".input-altera-valor", function (event) {
    let elementoPai = $(event.target).parent().parent();
    let prodQuant = elementoPai.find(".produto-input-altera-quant");

    let prodPreco = elementoPai.find(".produto-input-altera-preco");

    let subtotal = (prodPreco.val().replace(",", ".") * prodQuant.val());
    elementoPai.find(".subtotal").val(roundTwoDecimalPlaces(parseFloat(subtotal)));

    let soma = 0;
    Array.from($(".subtotal")).forEach((el) => soma += parseFloat(el.value));

    $("#total").val(roundTwoDecimalPlaces(soma));
});

function roundTwoDecimalPlaces(value) {
    const multiplier = Math.pow(10, 2);
    return Math.round(value * multiplier) / multiplier;
}