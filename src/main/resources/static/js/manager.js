$(function() {
    //导航
    $(".mainMsg:eq(1)").css("display", "block");
    $(".asideBar>ul>li").each(function(i, val) {
            $(val).click(function() {
                $(".asideBar>ul>li").each(function(i, val) {
                    $(val).removeClass("focus");
                })
                $(this).addClass("focus");
                $(".mainMsg").each(function(i, val) {
                    $(val).css("display", "none");
                })
                $(".mainMsg").eq(i).css("display", "block");
            });
        })
        //员工信息表
        //添加
    $("#add").click(function() {
        $.post('', {
            "data": $("#addForm").serialize() //数据格式为a=1&b=123的字符串
        }, function() {
            staffAjax();
        })
    });
    staffAjax();

    function staffAjax() {
        $("tbody").empty();
        $.ajax({
            type: "get",
            url: "/getAllStaff",
            dataType: "json",
            data: {},
            success: function(data) {
                for (i in data) {
                    var staffMsg = $('<tr>' +
                        '<td>' + data[i].id + '</td>' +
                        '<td>' + data[i].staffName + '</td>' +
                        '<td>' + data[i].staffDate + '</td>' +
                        '<td><a class="deleteStaff" href="#">删除</a></td>' +
                        '</tr>');
                    $("tbody").append(staffMsg);
                }
                //删除
                $(".deleteStaff").click(function() {
                    $.post('', {
                        staffId: $(this).parent().siblings().eq(0).html()
                    }, function() {
                        staffAjax();
                    })
                })

            },
            error: function(error) {
                console.log(error);
            }
        });
    }
    var oDate = new Date();
    echartFun(oDate.getFullYear(), oDate.getMonth() + 1);

    function echartFun(year, month) {
        $.post('/work_info', {
            year: year,
            month: month
        }, function(data) {
            var myChart = echarts.init(document.getElementById('timeAttendance'));
            data = JSON.parse(data)
            var staffArr = [],
                workDay = [],
                restDay = [];
            for (i in data) {
                staffArr.push(data[i].staffName);
                workDay.push(data[i].workDay);
                restDay.push(data[i].restDay);
            }
            myChart.setOption({
                title: {
                    text: year + '年' + month + '月员工考勤一览表'
                },
                tooltip: {
                    trigger: 'axis'
                },
                toolbox: {
                    feature: {
                        dataView: {
                            show: false,
                            readOnly: false
                        },
                        magicType: {
                            show: false,
                            type: ['line', 'bar']
                        },
                        restore: {
                            show: false
                        },
                        saveAsImage: {
                            show: true
                        }
                    }
                },
                legend: {
                    data: ['出勤天数', '请假天数']
                },
                xAxis: [{
                    type: 'category',
                    data: staffArr
                }],
                yAxis: [{
                    type: 'value',
                    name: '出勤天数',
                    min: 0,
                    max: 31,
                    axisLabel: {
                        formatter: '{value} 天'
                    }
                }, {
                    type: 'value',
                    name: '请假天数',
                    min: 0,
                    max: 31,
                    axisLabel: {
                        formatter: '{value} 天'
                    }
                }],
                series: [{
                    name: '出勤天数',
                    type: 'bar',
                    itemStyle: {
                        normal: {
                            color: function(params) {
                                var colorList = [
                                    '#5e7e54', '#e44f2f', '#81b6b2', '#eba422', '#5e7e54',
                                    '#e44f2f', '#81b6b2', '#eba422', '#5e7e54', '#e44f2f'
                                ];
                                return colorList[params.dataIndex]
                            },
                            　　　　　　　　　　　　　
                        }
                    },
                    data: workDay
                }, {
                    name: '请假天数',
                    type: 'line',
                    yAxisIndex: 1,
                    itemStyle: {
                        normal: {
                            color: '#58585a',
                            lineStyle: {
                                color: '#58585a'
                            }
                        }
                    },
                    data: restDay
                }]
            });
        })
    }
    $("#searchBtn").click(function() {
        var year = $("selectYear").find("option:selected").text();
        var month = $("selectMonth").find("option:selected").text();
        echartFun(year, month);
    });
    $("searchMore").click(function() {
            $.post('', {
                searchName: $(this).prev().val()
            }, function(data) {
                var more = JSON.parse(data);
                if (more.length == 0) {
                    alert("未查询到结果，请输入完整姓名");
                    return false;
                }
                var msg = $('<div class="leave pull-left">' +
                    '<p><strong>' + data.admin + '</strong></p>' +
                    '<p>' +
                    '<span>编号:</span>' +
                    '<span>' + data.id + '</span>' +
                    '</p>' +
                    '<p>' +
                    '<span>出勤天数:</span>' +
                    '<span>' + data.workDay + '</span>' +
                    '</p>' +
                    '<p>' +
                    '<span>请假天数：</span>' +
                    '<span>' + data.restDay + '</span>' +
                    '</p>' +
                    '<p>' +
                    '<span>请假日期：</span>' +
                    '<span>' + data.leave_date + '</span>' +
                    '</p>' +
                    '</div>');
                $(".leaves").append(msg);
            })
        })
        //请假申请批复
    respond();

    function respond() {
        $.post('/vocations_admin', {}, function(data) {
            // console.log(data);
            $(".emailCheck").empty();
            for (i in data) {
                var respondEmail = $('<div class="emailList">' +
                    '<div class="sender col-12">' +
                    '<span>申请人：</span>' +
                    '<span><strong>' + data[i].applicant + '</strong></span>' +
                    '</div>' +
                    '<div class="emailBox">' +
                    '<div class="contentBox">' +
                    '<span>申请天数：</span>' +
                    '<span><strong>' + data[i].leave_days + '</strong>天</span>' +
                    '<div>' +
                    '<span>申请日期：</span>' +
                    '<span>' + data[i].leave_date + '</span>' +
                    '</div>' +
                    '<span>内容：</span>' +
                    '<p>' + data[i].leave_reason + '</p>' +
                    '</div>' +
                    '<div class="checkBox" index = "' + data[i].id + '">' +
                    '<a href="#"><span class="glyphicon glyphicon-ok"></span></a>' +
                    '<span>|</span>' +
                    '<a href="#"><span class="glyphicon glyphicon-remove"></span></a>' +
                    '</div>' +
                    '</div>' +
                    '</div>');
                $(".emailCheck").append(respondEmail);
            }
        });

        function check($name, url, result, text) {
            $("." + $name).click(function() {
                var _this = $(this).parent().parent();
                $.post(url, {
                    sender: _this.prev().find("strong").text(),
                    id: _this.attr("index"),
                    result: result
                }, function() {
                    _this.html('<button class="btn btn-danger">' + text + '</button>');
                    respond();
                })
            });
        }
        check('glyphicon-ok', '', 'true', '已通过');
        check('glyphicon-remove', '', 'false', '已驳回');
    }
})