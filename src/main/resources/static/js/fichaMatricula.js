const prevBtns = document.querySelectorAll(".prevBtn");
const nextBtns = document.querySelectorAll(".nextBtn");
const items = document.querySelectorAll(".item");
const formSteps = document.querySelectorAll(".step-form");

let formStepsNum = 0;

nextBtns.forEach((btn) => {
    btn.addEventListener("click", () => {
        formStepsNum++;
        updateFormSteps();
        updateProgressbarNext();
    });
});

prevBtns.forEach((btn) => {
    btn.addEventListener("click", () => {
        formStepsNum--;
        updateFormSteps();
        updateProgressbarBack();
    });
});

function updateFormSteps() {
    formSteps.forEach((formStep) => {
        formStep.classList.remove("step-form-active");
    });
    formSteps[formStepsNum].classList.add("step-form-active");
}

function updateProgressbarBack() {
    items[formStepsNum + 1].classList.remove("active")
}

function updateProgressbarNext() {
    items[formStepsNum].classList.add("active")
}

$('#province').change(
    function () {
        updateDistrics();
    });
$('#department').change(
    function () {
        updateProvinces();
        setTimeout(function () {
            updateDistrics()
        }, 100);
    });

function updateProvinces() {
    var p;
    $.getJSON("http://localhost:8080/api/province", {
        department: $('#department').val(),
        ajax: 'true'
    }, function (data) {
        let html = '';
        let len = data.length;
        p = data;
        for (let i = 0; i < len; i++) {
            html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
        }
        html += '</option>';
        $('#province').html(html);
    });
}

function updateDistrics() {
    $.getJSON("http://localhost:8080/api/districts", {
        province: $('#province').val(),
        ajax: 'true'
    }, function (data) {
        let html = '';
        let len = data.length;
        for (let i = 0; i < len; i++) {
            html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
        }
        html += '</option>';
        $('#district').html(html);
    });
}