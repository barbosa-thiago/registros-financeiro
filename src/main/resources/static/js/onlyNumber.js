
$(document).on("input", ".only-numbers", function () {
    let inputValue = $(this).val();

    inputValue = inputValue.replace(/[^0-9.]/g, '');
    $(this).val(inputValue.toLocaleString());
});
