//三大板块
$(function() {
    $(".mainMsg:eq(0)").css("display", "block");
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
    $(".lookMail").click(function() {
        $(".asideBar>ul>li:last").trigger("click");
    })
    $(".staffName").html();
    //考勤情况
    $(".chooseDate>input[type = 'button']").click(function() {
        $.ajax({
            type: "post",
            url: "",
            dataType: "json",
            data: {
                "startDate": $("#ECalendar_date").val(),
                "endDate": $("#ECalendar_date1").val()
            },
            success: function(data) {
                for (i in data) {
                    var dateList = $("<tr>" +
                        "<td>" + data.num + "</td>" +
                        "<td>" + data.dateNum + "</td>" +
                        "<td>" + data.inTime + "</td>" +
                        "<td>" + data.outTime + "</td>" +
                        "<td>" + data.attendance + "</td>" +
                        +"</tr>");
                    $(".attendanceTable").append(dateList);
                }
            },
            error: function() {
                alert("连接失败，请刷新重试");
            }
        });
    });
    //请假申请
    $(function() {
            var reg = [/^[\u4e00-\u9fa5]+$/, /^[0-9]{1,}$/, /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/];
            $(".applyForm input").each(function(i, val) {
                if (i == 2) {
                    $(val).blur(function() {
                        var dateArr = $(val).val().split(" ");
                        console.log(dateArr);
                        var flag = true;
                        for (var j = 0; j < dateArr.length; j++) {
                            flag = reg[i].test(dateArr[j]) ? true : false;
                        }
                        if (flag) {
                            $(val).next().css("display", "block");
                        } else {
                            $(val).next().css("display", "none");
                        }
                    })
                } else {
                    $(val).blur(function() {
                        if (reg[i].test($(val).val())) {
                            $(val).next().css("display", "block");
                        } else {
                            $(val).next().css("display", "none");
                        }
                    })
                }
            });
            $(".applyForm textarea").blur(function() {
                if ($(this).val() != '') {
                    $(this).next().css("display", "block");
                } else {
                    $(this).next().css("display", "none");
                }
            });
            $(".applyForm button[type='submit']").click(function() {
                var isTrue = $('.form-control-feedback');
                for (var i = 0; i < isTrue.length - 1; i++) {
                    if (isTrue.eq(i).css("display") == 'none') {
                        alert("请按示例格式填写");
                        return false;
                    }
                }
                if ($(".applyForm textarea").val() == '') {
                    alert("请按示例格式填写");
                    return false;
                }
            })
        })
        //私信
    $(function() {
        var page;
        requestEmail(0, 5);
        //刷新、标为已读、删除
        $(".refresh").click(function() {
            requestEmail(0, 5);
        });
        $(".markRead").click(function() {
            var arr = [];
            $(".email input[type = 'checkbox']").each(function(i, val) {
                if (val.checked) {
                    arr.push(val);
                }
            })
            $.ajax({
                type: "post",
                url: "",
                dataType: "json",
                data: {
                    "markRead": arr
                },
                success: function(data) {

                },
                error: function(error) {
                    console.log(error);
                    // alert("操作失败，请重试");
                }
            })
        });

        function requestEmail(startMail, endMail) {
            //获取员工信息
            $.ajax({
                    type: "get",
                    url: "",
                    dataType: "json",
                    data: {},
                    success: function(data) {
                        $(".staffName").html(data.staffName);
                        $(".staffId").html(data.staffId);
                        $(".mailNum").html(data.mailNum);
                    },
                    error: function(error) {
                        console.log(error);
                    }
                })
                //获取邮件
            $.ajax({
                type: "post",
                url: "",
                dataType: "json",
                data: {

                },
                success: function(data) {
                    page = data.length / onePage + 1;
                    if (data.length == 0) {
                        $(".noEmail").css("display", "block");
                    } else {
                        for (var i = startMail; i < endMail; i++) {
                            var oneEmail = $('<div class="one clearfix">' +
                                '<div class="col-sm-1 pull-left text-center' + data[i].status + '">' +
                                '<input type="checkbox" name="emailCheck" class="emailCheck">' +
                                '</div>' +
                                '<div class="col-sm-2 pull-left text-center">' +
                                '<span>发件人：</span><strong>' + data[i].sender + '</strong>' +
                                '<div>' +
                                '<p>' + data[i].time + '</p>' +
                                '<p>' + data[i].date + '</p>' +
                                '</div>' +
                                '</div>' +
                                '<div class="col-sm-8 pull-left">' +
                                '<div>' +
                                '<span>' + data[i].mainContent + '</span>' +
                                '<p>' + data[i].allContent + '</p>' +
                                '</div>' +
                                '<span class="toggleMark glyphicon glyphicon-chevron-up"></span>' +
                                '</div>' +
                                '</div>');
                            $(".email").append(oneEmail);
                        }
                        $(".toggleMark").click(function() {
                            if ($(this).hasClass("glyphicon-chevron-up")) {
                                $(this).prev().children('p').slideUp();
                                $(this).parent().prev().children('div').slideUp();
                                $(this).removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
                            } else {
                                $(this).prev().children('p').slideDown();
                                $(this).parent().prev().children('div').slideDown();
                                $(this).removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
                            }
                        });
                    }
                },
                error: function(error) {
                    console.log(error);
                }
            })
        }
    })
});