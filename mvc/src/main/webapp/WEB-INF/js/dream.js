$(document).ready(function () {
    var url = 'add/dream';
    var data = {
        date: new Date()
    };
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: data,
        success: function (data) {
        }
    });
    update();
});

function update() {
    $('.tags-input').keydown(function (e) {
        var tags = $(this).val().split(new RegExp('[., ]', 'g'));
        if (e.keyCode == '32' || e.keyCode == 190) {
            var data = {
                tags: Array[tags],
                order: $(this).data('order')
            };
            var url = 'dream/addEpisode';
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
}

$('#add-episod').on('click', function () {
    var count = $(this).parent().find('.thumbnail').length;
    var block = '<div class="thumbnail">' +
        '<img data-holder-rendered="true" src="../img/notImg.jpg" style="height: 200px; display: block;"alt="100%x200">' +
        '<div class="caption">' +
        '<div id="container-icons" style="padding-bottom: 5px"></div>' +
        '<input data-order="' + Number(count + 1) + '" type="text" class="form-control tags-input" placeholder="Tags string..."/></div></div>';
    $('#container-episod').append(block);
    update();
});

$('#final').on('click', function () {
    window.location = 'journal?id=' + getCookie('dreamId');
});

function getCookie(name) {
    var matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}

