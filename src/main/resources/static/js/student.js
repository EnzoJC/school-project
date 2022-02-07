// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
    'use strict';
    window.addEventListener('load', function () {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        let forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        let validation = Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();

(function () {
    $.getJSON("http://localhost:8080/api/level", {
        ajax: 'true'
    }, function (data) {
        let html = '';
        let len = data.length;
        for (let i = 0; i < len; i++) {
            html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
        }
        html += '</option>';
        $('#level').append(html);
    });
})();


$('#level').change(
    function () {
        $.getJSON("http://localhost:8080/api/grade", {
            level: $(this).val(),
            ajax: 'true'
        }, function (data) {
            let html = '';
            let len = data.length;
            for (let i = 0; i < len; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
            }
            html += '</option>';
            $('#grade').append(html);
        });
    });

(function () {
    $.getJSON("http://localhost:8080/api/gender", {
        ajax: 'true'
    }, function (data) {
        let html = '';
        let len = data.length;
        for (let i = 0; i < len; i++) {
            html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
        }
        html += '</option>';
        $('#gender').append(html);
    });
})();

(function () {
    $.getJSON("http://localhost:8080/api/nationality", {
        ajax: 'true'
    }, function (data) {
        let html = '';
        let len = data.length;
        for (let i = 0; i < len; i++) {
            html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
        }
        html += '</option>';
        $('#nationality').append(html);
    });
})();

(function () {
    $.getJSON("http://localhost:8080/api/document-type", {
        ajax: 'true'
    }, function (data) {
        let html = '';
        let len = data.length;
        for (let i = 0; i < len; i++) {
            html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
        }
        html += '</option>';
        $('#document-type').append(html);
    });
})();

(function () {
    $.getJSON("http://localhost:8080/api/relationship", {
        ajax: 'true'
    }, function (data) {
        let html = '';
        let len = data.length;
        for (let i = 0; i < len; i++) {
            html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
        }
        html += '</option>';
        $('#relationship').append(html);
    });
})();