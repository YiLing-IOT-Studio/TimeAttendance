$(function() {
    //导航
    var oDate = new Date();
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
        $.ajax({
            type : "post",
            url : "/addStaff",
            dataType : "json",
            data : $("#addForm").serialize(),
            success : function() {
                staffAjax();
            }
        });
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
                    var _this=$(this);
                    $.post('/delStaff', {
                        staffId: _this.parent().prev().prev().prev().html(),
                        staffName: _this.parent().prev().prev().html()
                    }, function() {
                        staffAjax();
                        echartFun(oDate.getFullYear(), oDate.getMonth() + 1);
                    })
                })

            },
            error: function(error) {
                console.log(error);
            }
        });
    }
    echartFun(oDate.getFullYear(), oDate.getMonth() + 1);
    $('.selectYear option').each(function(i, data) {
        var _this = $(this);
        if (_this.html() == oDate.getFullYear()) {
            _this.attr('selected', 'true');
        }
    })
    $('.selectMonth option').each(function(i, data) {
        var _this = $(this);
        if (_this.html() == (oDate.getMonth() + 1)) {
            _this.attr('selected', 'selected');
        }
    })


    function echartFun(year, month) {
        $.post('/work_info', {
            year: year,
            month: month
        }, function(data) {
            var myChart = echarts.init(document.getElementById('timeAttendance'));
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
        var year = $(".selectYear").find("option:selected").text();
        var month = $(".selectMonth").find("option:selected").text();
        echartFun(year, month);
    });
    $(".searchMore").click(function() {
            $.post('/work_info_name', {
                searchName: $(this).prev().val()
            }, function(data) {
                $(".leaves").empty();
                if (data.length == 0) {
                    alert("未查询到结果，请输入完整姓名");
                    return false;
                }
                for (i in data) {
                    var msg = $('<div class="leave pull-left">' +
                        '<p><strong>' + data[i].staffName + '</strong></p>' +
                        '<p>' +
                        '<span>编号:</span>' +
                        '<span>' + data[i].staffId + '</span>' +
                        '</p>' +
                        '<p>' +
                        '<span>出勤天数:</span>' +
                        '<span>' + data[i].workDay + '</span>' +
                        '</p>' +
                        '<p>' +
                        '<span>请假天数：</span>' +
                        '<span>' + data[i].restDay + '</span>' +
                        '</p>' +
                        '<p>' +
                        '<span>请假日期：</span>' +
                        '<span>' + data[i].leaveDate + '</span>' +
                        '</p>' +
                        '</div>');
                    $(".leaves").append(msg);
                }
            })
        })
        //请假申请批复
    respond();

    function respond() {
        $.post('/vocations_admin', {}, function(data) {
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
                    '</div>' +
                    '</div>' +
                    '</div>');
                $(".emailCheck").append(respondEmail);
                if (data[i].all_content) {
                    $('.checkBox').eq(i).html('<button class="btn btn-danger">' + data[i].all_content + '</button>');
                } else {
                    $('.checkBox').eq(i).html('<a href="#"><span class="glyphicon glyphicon-ok"></span></a>' +
                        '<span>|</span>' +
                        '<a href="#"><span class="glyphicon glyphicon-remove"></span></a>');
                }
            }

            function check($name, url, result) {
                $("." + $name).click(function() {
                    var _this = $(this).parent().parent();
                    $.post(url, {
                        id: _this.attr("index"),
                        result: result
                    }, function() {
                        respond();
                    })
                });
            }
            check('glyphicon-ok', '/handle_vocation', '已通过');
            check('glyphicon-remove', '/handle_vocation', '已驳回');
        });
    }
})