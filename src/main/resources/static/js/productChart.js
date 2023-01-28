    $.ajax({
        url: '/admin/product/analysis',
        type: 'GET',
        success: function (data) {
            let options = {
                chart: {
                    type: 'line',
                    height: 350,
                },
                series: [{
                        name: 'Total Products',
                        data: data.totalData
                    },
                    {   
                        name: 'Added Products',
                        data: data.addedData
                    },
                    {
                        name: 'Active Products',
                        data: data.activeData
                    },
                    {
                        name: 'Deleted Products',
                        data: data.deletedData
                    }
                ],
                tooltip: {
                    enabled: true,
                    shared: true,
                    intersect: false,
                    fillSeriesColor: false,
                    theme: 'dark',
                    x: {
                        show: true,
                    },
                    y: {
                        title: {
                            formatter: function(seriesName) {
                                return ''
                            }
                        }
                    }
                },
                xaxis: {
                    categories: data.xaxisCategories
                }
            }
      
            var chart = new ApexCharts(document.querySelector("#lineChart"), options);
            chart.render();
        }
    });

        