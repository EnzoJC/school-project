document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("form-pay").addEventListener('submit', validarFormulario);
});

function validarFormulario(evento) {
    evento.preventDefault();
    const fullNames = document.getElementById('full-names').value;
    if (fullNames.length > 0) {
        const regexFullNames = /^[a-zA-ZÀ-ÿ\u00f1\u00d1]+(\s*[a-zA-ZÀ-ÿ\u00f1\u00d1]*)*[a-zA-ZÀ-ÿ\u00f1\u00d1]+$/i;
        if (!regexFullNames.test(fullNames)) {
            alert("Nombre inválido");
            return false;
        }
    }
    const cardNumber = document.getElementById('card-number').value;
    if (cardNumber.length > 0) {
        const regexCardNumber = /^([0-9]{4})+(-)+([0-9]{4})+(-)+([0-9]{4})+(-)+([0-9]{4})$/;
        if (!regexCardNumber.test(cardNumber)) {
            alert("Número de tarjeta inválido");
            return false;
        }
    }
    const month = document.getElementById("month").value;
    if (month.length > 0) {
        const regexMonth = /^[0-9]{2}$/;
        if (!regexMonth.test(month)) {
            alert("Mes inválido");
            return false;
        }
    }
    const year = document.getElementById("year").value;
    if (year.length > 0) {
        const regexYear = /^[0-9]{2}$/;
        if (!regexYear.test(year)) {
            alert("Año inválido");
            return false;
        }
    }
    const cvv = document.getElementById("cvv").value;
    if (cvv.length > 0) {
        const regexCvv = /^[0-9]{3}$/;
        if (!regexCvv.test(cvv)) {
            alert("CVV inválido");
            return false;
        }
    }
    this.submit();
}

function tarjeta(valor) {
    if (valor.match(/^\d{4}$/) !== null) {
        return valor + '-';
    } else if (valor.match(/^\d{4}\-\d{4}$/) !== null) {
        return valor + '-';
    } else if (valor.match(/^\d{4}\-\d{4}\-\d{4}$/) !== null) {
        return valor + '-';
    }
    return valor;
}