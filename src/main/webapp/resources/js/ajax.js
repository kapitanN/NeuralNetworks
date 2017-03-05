/**
 * Created by nikita on 24.01.2017.
 */

function call() {
    var msg   = $('#formx').serialize();
    $.ajax({
        type: 'POST',
        url: 'login',
        data: msg,
        success: function(data) {
            $('#results').html(data);
        },
        error:  function(xhr, str){
            alert('Возникла ошибка: ' + xhr.responseCode);
        }
    });
}