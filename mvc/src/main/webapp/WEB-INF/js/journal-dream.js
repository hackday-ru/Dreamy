$(document).ready(function () {
    var url = '/app/dream/interpret';
    $.ajax({
        url: url,
        type: 'GET',
        dataType: 'json',
        data: {},
        success: function (data) {
            var block = '';
            for (var i = 0; i < data.length; i++) {
                block = block +
                    '<div class="row"><div class="col-md-2"><img src="../resources/icons/' + data[i][0] + '" width="30"/></div>' +
                    '<div class="col-md-10"><p>' + data[i][1] + '</p></div></div>';
            }
            $('#container-icons').html(block);
        }
    });
});
