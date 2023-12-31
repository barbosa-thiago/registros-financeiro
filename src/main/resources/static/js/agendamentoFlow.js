function openModal(li) {
    let date = li.getAttribute('data-agendamento-date');

    let dateParts = date.split('-');
    let year = parseInt(dateParts[0]);
    let month = parseInt(dateParts[1]) - 1;
    let day = parseInt(dateParts[2]);

    const dataAgendamento = document.getElementById('data-ag-modal');
    if (dataAgendamento !== null) {
        dataAgendamento.value = Intl.DateTimeFormat("fr-FR", {
            day: "2-digit",
            month: "2-digit",
            year: "numeric"
        }).format(new Date(year, month, day));
    }
}

document.addEventListener("DOMContentLoaded", function () {
    let clickableDivs = document.querySelectorAll(".card-agendamento");

    $('.card-agendamento').on('click', function () {
        $('#confirma-pagamento-modal').modal('show');

        $("#valor-pagamento-confirmacao").val($(this).attr("data-agendamento-valor"));
        $("#data-pagamento-confirmacao").val($(this).attr("data-agendamento-date"));
        $("#fornecedor-pagamento-confirmacao").val($(this).attr("data-agendamento-fornecedor"));
        $("#agendamento-foi-pago").val($(this).attr("data-agendamento-pago"));
        $("#pagando-agendamento-id").val($(this).attr("data-agendamento-id"));
    });
});

function submitChildForm() {
    let pagamentoValor = $('#valor-pagamento-confirmacao').val()
    let pagamentoData = $('#data-pagamento-confirmacao').val()
    let pagamentoFornecedor = $('#fornecedor-pagamento-confirmacao').val();
    let agendamentoId = $('#pagando-agendamento-id').val();

    let pagamento = {
        valor: pagamentoValor,
        data: pagamentoData,
        descricao: pagamentoFornecedor,
        tipo: "AGENDADO"
    };

    $.ajax({
        type: 'POST',
        url: `/agendamentos/update/${agendamentoId}`,
        contentType: 'application/json',
        data: JSON.stringify(pagamento),
        success: function (response) {
            console.log('Success: agendamento atualizado');
            window.location.reload();
        },
        error: function (xhr, status, error) {
            alert('Error: ' + JSON.parse(xhr.responseText).message);
        }
    });
}