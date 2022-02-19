
function callApi(url, data, element) {
    $.getJSON(
        url,
        data,
        load(data, element)
    );
}

function load(data, id) {
    let html = '';
    let len = data.length;
    for (let i = 0; i < len; i++) {
        html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
    }
    html += '</option>';
    $(id).append(html);
}