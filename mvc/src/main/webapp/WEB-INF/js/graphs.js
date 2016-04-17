var data1 = {
    labels: ['jan', 'feb', 'mar'],
    datasets: [
        {
            label: "Communicative",
            data: ['36', '24', '38'],
            backgroundColor: [
                "#F7464A",
                "#46BFBD",
                "#FDB45C"
            ],
            hoverBackgroundColor: [
                "#FF5A5E",
                "#5AD3D1",
                "#FFC870"
            ]
        }]
};

var myPieChart = new Chart($('#g1'), {
    type: 'line',
    data: data1
});

var data2 = {
    labels: ["Брови", "Алмаз", "Забор", "Акула", "Ангкл", "Анфитамин", "Азбука"],
    datasets: [
        {
            label: "Users Tags Frequency",

            // The properties below allow an array to be specified to change the value of the item at the given index
            // String  or array - the bar color
            backgroundColor: "rgba(55,120,64,0.7)",

            // String or array - bar stroke color
            borderColor: "rgba(220,220,220,1)",

            // Number or array - bar border width
            borderWidth: 1,

            // String or array - fill color when hovered
            hoverBackgroundColor: "rgba(220,220,220,0.2)",

            // String or array - border color when hovered
            hoverBorderColor: "rgba(220,220,220,1)",

            // The actual data
            data: [234, 154, 144, 102, 88, 55, 40],

            // String - If specified, binds the dataset to a certain y-axis. If not specified, the first y-axis is used.
            yAxisID: "y-axis-0",
        }

    ]
};

var myBarChart = new Chart($('#g2'), {
    type: 'bar',
    data: data2
});

var data3 = {
    datasets: [{
        data: [
            15,
            10,
            7,
            14,
            5,
            6,
            1,
            10
        ],
        backgroundColor: [
            "#F7464A",
            "#46BFBD",
            "#FDB45C",
            "#949FB1",
            "#4D5360",
            "#4D0360",
            "#9D0360",
            "#FD0360",
        ],
        label: 'My dataset' // for legend
    }],
    labels: [
        "Мечта",
        "Кошмар",
        "Осознаный",
        "Эротический",
        "Творческий",
        "Повторяющийся",
        "Летаргичкский",
        "Без снов"
    ]
};

new Chart($('#g3'), {
    data: data3,
    type: 'polarArea'
});




