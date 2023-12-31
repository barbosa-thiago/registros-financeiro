$(document).ready(function () {
    $(".date-input").val("dd/mm/aaaa");
});

$(document).on("input", ".date-input", function () {
    $(".date-input").mask("99/99/9999");
});