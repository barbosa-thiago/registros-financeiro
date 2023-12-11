$(document).ready(function () {
    $(".currency-input").val("0,00");
})

$(document).on("input", ".currency-input", function () {
    let inputValue = $(this).val();

    inputValue = inputValue.replace(/[^0-9.]/g, '');
    inputValue = inputValue.replace(',', '.');

    let periodPosition = inputValue.indexOf('.');
    if (periodPosition !== -1) {
        inputValue = inputValue.slice(0, periodPosition + 1) + inputValue.slice(periodPosition + 1).replace(/\./g, '');
    }

    $(this).val(inputValue);
});
