function ajax(url, data, sucFunc) {
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: data,
        success: function (data) {
            var div = 'data-reservation-id="I" ' +
                ' data-reservation-employee-id="EI"' +
                ' data-reservation-target="TRG"';
            div = div.replace("I", data.id);
            div = div.replace("EI", data.employee.id);
            div = div.replace("TRG", data.target);
            $(currentBtn).html("<div " + div + "><label class='td-message' style='font-weight: 700'>" +
                data.employee.firstName + " " +
                data.employee.secondName +
                "</label><br/>" + data.target + "</div>");
            $(currentBtn).addClass('edit-reservation');
            $(currentBtn).removeClass('create-reservation');
            update();
        }
    });
}