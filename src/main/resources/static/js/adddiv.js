

function adicionarProduto() {

    // Create a div to hold the input elements
    var elementDiv = $('<div>').attr("class", "insert-produto");

    // Create and append the first input with its label
    var input1 = $('<input>').attr('type', 'text');
    var label1 = $('<label>').text('Nome: ').append(input1);
    elementDiv.append(label1);

    // Create and append the second input with its label
    var input2 = $('<input>').attr('type', 'text');
    var label2 = $('<label>').text('Quant: ').append(input2);
    elementDiv.append(label2);

    // Create and append the third input with its label
    var input3 = $('<input>').attr('type', 'text');
    var label3 = $('<label>').text('Preço: ').append(input3);
    elementDiv.append(label3);

    // Create and append the third input with its label
    var input4 = $('<input>').attr('type', 'button').val('X');
    var label4 = $('<label>').append(input4);
    input4.css("color", "red");
    input4.css("vertical-align", "bottom");
    elementDiv.append(label4);

    // Append the new element to the container using jQuery
    $('#produtos').append(elementDiv);

    label4.on('click', function() {
        elementDiv.remove();
    });
    console.log("chegou aqui");
}