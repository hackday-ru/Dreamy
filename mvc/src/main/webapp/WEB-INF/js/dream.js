$(document).ready(function () {
    var url = 'add';
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
        if (e.keyCode == '32' || e.keyCode == 190) {
            var tags = $(this).val().split(new RegExp('[., ]', 'g'));
            var data = {
                tags: tags,
                order: $(this).data('order')
            };
            var url = 'addEpisode';
            $.ajax({
                url: url,
                contentType: "application/json",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(data),
                success: function (data) {
                    console.log(data);
                    var iconsImg = '';
                    for (var i = 0; i < data.length; i++) {
                        var img = '<img src="../resources/icons/' + data[i] + '" width="30"/>';
                        iconsImg = iconsImg + img;
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
    window.location = 'journal';
});


