$(document).ready(function () {
    var url = 'get-dream';
    var data = {
        limit: 10
    };
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: data,
        success: function (data) {
            render(data);
        }
    });
});

$('.tags-input').keydown(function (e) {
    if (e.keyCode == '32' || e.keyCode == 190) {
        var data = {
            tags: $(this).val().split(new RegExp('[., ]', 'g'))
        };
        var url = 'getIcons';
        $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            data: data,
            success: function (data) {
                var iconsImg = [];
                for (var i = 0; i < data.icons.length; i++) {
                    var img = '<img src="../icons/' + data.icons[i] + '" width="30"/>';
                    iconsImg.append(img);
                }
                $('#container-icons').html(iconsImg);
            }
        });
    }
});

function render() {
    for (var i = 0; i < data.dreams.length; i++) {
        var block = '<div class="thumbnail">' +
            '<img data-holder-rendered="true" src="../img/notImg.jpg" style="height: 200px; display: block;"alt="100%x200">' +
            '<div class="caption">' +
            '<div id="container-icons" style="padding-bottom: 5px"></div>' +
            '<input type="text" class="form-control tags-input" placeholder="Tags string..." value="' + data.dreams[i].tags + '" readonly/>' +
            '<h4>Толкование</h4>' +
            '<p></p></div></div>';
        $('#container-episod').append(block);
    }
}
$('#final').on('click', function () {
    window.location = 'journal';
});


