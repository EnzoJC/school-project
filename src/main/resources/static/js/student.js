$('#level').change(
    function () {
        $.getJSON("http://localhost:8080/api/grade", {
            level: $(this).val(),
            ajax: 'true'
        }, function (data) {
            let html = '';
            let len = data.length;
            html = '<option class="seleccionado" value="" selected>Seleccione un grado</option>';
            for (let i = 0; i < len; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
            }
            html += '</option>';
            $('#grade').html(html);
        });
    });