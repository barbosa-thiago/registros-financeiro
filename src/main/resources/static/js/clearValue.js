document.addEventListener('DOMContentLoaded', function() {
    let inputElement = document.getElementById('total');

    if (performance.navigation.type === 1 && inputElement !== null) {
        inputElement.value = '';
    }
});