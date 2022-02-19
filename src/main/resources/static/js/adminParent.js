document.querySelector('.editarPadre').addEventListener("click", () => {
    /*<![CDATA[*/

    var padres = [[${parents}]];
    console.log(padres);
    /*]]>*/
    for(let i=0;i<padres.length();i++){
        if(padres[i].getId().equals($(this).attr('id'))){
            $('#id').val($(this).attr('id'));
            $('#edit-given-names').val(padres[i].getGivenNames());
            $('#edit-first-last-name').val(padres[i].getGivenNames());
            $('#edit-second-last-name').val(padres[i].getGivenNames());

        }
    }
});
