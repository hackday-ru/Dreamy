$('#signup-btn').on('click', function () {
    if (checkData()) {
        var url = 'singup';
        var data = {
            username: $('#email').val().split('@')[0],
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
                    window.location = 'singin'
                } else {
                    $('#error-singup').show();
                }
            },
            error: function (data) {
                $('#error-singup').show();
            }

        });
    }
});

$('#error-email button').on('click', function () {
    $('#error-email').hide();
});

$('#error-pwd button').on('click', function () {
    $('#error-pwd').hide();
});

function checkData() {
    var useremail = $('#email').val();
    var pwd = $('#password').val();
    var cpwd = $('#confirm-password').val();
    var isGood = true;

    if (pwd != cpwd || pwd == '' || cpwd == '') {
        $('#error-pwd').show();
        isGood = false;
    } else {
        $('#error-pwd').hide();
    }

    if (!validateEmail(useremail)) {
        $('#error-email').show();
        isGood = false;
    } else {
        $('#error-email').hide();
    }
    return isGood;
}

function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}