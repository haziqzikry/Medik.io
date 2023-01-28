var chart;

var value = $("#product").val();
$.ajax({
    url: '/admin/stock/analysis/' + value,
    type: 'GET',
    success: function (data) {
        var options = {
            series: [{
            name: 'Stock In Month',
            type: 'column',
            data: data.stockInMonth
          }, {
            name: 'Cummulative Stock',
            type: 'line',
            data: data.stockCummulative
          }],
            chart: {
            height: 350,
            type: 'line',
          },
          stroke: {
            width: [0, 4]
          },
          title: {
            text: 'Stock Analysis'
          },
          dataLabels: {
            enabled: true,
            enabledOnSeries: [1]
          },
          xaxis: {
            categories: data.xaxisCategory
          },
          yaxis: [{
            title: {
              text: 'Stock In Month',
            },
          
          }, {
            opposite: true,
            title: {
              text: 'Cummulative Stock'
            }
          }]
          };
          chart = new ApexCharts(document.querySelector("#stockChart"), options);
          chart.render();
    }});


$("#product").change(function(){
    var value = $(this).val();

    $.ajax({
        url: '/admin/stock/analysis/' + value,
        type: 'GET',
        success: function (data) {
            var options = {
                series: [{
                name: 'Stock In Month',
                type: 'column',
                data: data.stockInMonth
              }, {
                name: 'Cummulative Stock',
                type: 'line',
                data: data.stockCummulative
              }],
                chart: {
                height: 350,
                type: 'line',
              },
              stroke: {
                width: [0, 4]
              },
              title: {
                text: 'Stock Analysis'
              },
              dataLabels: {
                enabled: true,
                enabledOnSeries: [1]
              },
              xaxis: {
                categories: data.xaxisCategory
              },
              yaxis: [{
                title: {
                  text: 'Stock In Month',
                },
              
              }, {
                opposite: true,
                title: {
                  text: 'Cummulative Stock'
                }
              }]
              };
      
              
              chart.updateOptions(options);
        }});
});
    
    