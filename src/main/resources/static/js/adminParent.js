
$(document).ready(function(){
    $('body').on('click','.editarPadre',function(){

        var id= $(this).attr('id');

        $.getJSON("http://localhost:8080/api/parent/"+id, {
            ajax: 'true'
        }, function (data) {
            console.log(data);
            $('#id').val(id);
            $('#edit-given-names').val(data.givenNames);
            $('#edit-first-last-name').val(data.firstLastName);
            $('#edit-second-last-name').val(data.secondLastName);
            $('#edit-document-number').val(data.documentNumber);
            $('#edit-document-type').val(data.documentType.id);
            $('#edit-birth-date').val(data.birthDate);
            $('#edit-gender').val(data.gender.id);
            $('#edit-nationality').val(data.nationality.id);
            $('#edit-address').val(data.address);
            $('#edit-email-address').val(data.email);
            $('#edit-phone-number').val(data.phone);

        });

    });
});
/*
document.querySelector('.editarPadre').addEventListener("click", () => {
    for(let i=0;i<padres.length();i++){
        if(padres[i].getId().equals($(this).attr('id'))){
            $('#id').val($(this).attr('id'));
            $('#edit-given-names').val(padres[i].getGivenNames());
            $('#edit-first-last-name').val(padres[i].getGivenNames());
            $('#edit-second-last-name').val(padres[i].getGivenNames());

        }
    }
});
*/