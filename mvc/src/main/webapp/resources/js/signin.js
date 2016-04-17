$('#signup-btn').on('click', function () {
    var url = 'singin';
    var data = {
        useremail: $('#email').val(),
        pwd: $('#password').val()
    };

    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: data,
        success: function (data) {
            if (data === 'Success') {
                window.location = 'index'
            } else {
                $('#error-singin').show();
            }
        },
        error: function (data) {
            $('#error-singup').show();
        }

    });
});

$('#error-email button').on('click', function () {
    $('#error-email').hide();
});

$('#error-singup button').on('click', function () {
    $('#error-singup').hide();
});