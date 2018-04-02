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
            type: "get",
            url: "",
            dataType: "json",
            data: {
                "startDate": $("#ECalendar_date").val(),
                "endDate": $("#ECalendar_date1").val()
            },
            success: function(data) {
                console.log(data);
                for (i in data) {
                    var dateList = $("<tr>" +
                        "<td>" + data[i].num + "</td>" +
                        "<td>" + data[i].dateNum + "</td>" +
                        "<td>" + data[i].inTime + "</td>" +
                        "<td>" + data[i].outTime + "</td>" +
                        "<td>" + data[i].attendance + "</td>" +
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
        var page,
            onePage = 5,
            startPage = 0,
            endPage,
            curPage = 1;
        requestEmail();
        //刷新、标为已读、删除
        $(".refresh").click(function() {
            startPage = 0;
            curPage = 1;
            requestEmail();
            $(".glyphicon-chevron-right").css("display", "block");
        });
        var arrEyes = [];
        $(".email input[type = 'checkbox']").click(function() {
            if (arrEyes.length == 0) {
                $(".markRead").attr("disabled", true);
            } else {
                $(".markRead").attr("disabled", false);
            }
            if (this.checked && $(this).hasClass("unRead")) {
                if ($.inArray($(this).attr("id"), arrEyes) == -1) {
                    arrEyes.push($(this).attr("id"));
                }
            } else {
                if ($.inArray($(this).attr("id"), arrEyes) != -1) {
                    arrEyes.splice($.inArray($(this).attr("id"), arrEyes), 1);
                }
            }
        });
        $(".markRead").click(function() {
            $.ajax({
                type: "post",
                url: "/read_state",
                traditional: true,
                data: {
                    "markRead": arrEyes
                },
                success: function(data) {
                    requestEmail();
                },
                error: function(error) {
                    console.log(error);
                    // alert("操作失败，请重试");
                }
            })
        });
        $(".delete").click(function() {
            var arr = [];
            $(".email input[type = 'checkbox']:checked").each(function() {
                arr.push($(this).attr('index'));
            })
            $.ajax({
                type: "post",
                url: "/delete_vocation",
                traditional: true,
                data: {
                    "markRead": arr
                },
                success: function(data) {
                    requestEmail();
                },
                error: function(error) {
                    console.log(error);
                    // alert("操作失败，请重试");
                }
            })
        });
        //下一页，上一页
        $(".glyphicon-chevron-left").click(function() {
            if (curPage - 1 == 1) {
                $(this).css("display", "none");
            }
            if (curPage > 0) {
                startPage -= onePage;
                curPage -= 1;
                requestEmail();
                $(".glyphicon-chevron-right").css("display", "block");
            }

        });
        $(".glyphicon-chevron-right").click(function() {
            if ((curPage + 1) == page) {
                $(this).css("display", "none");
            }
            if (curPage < page) {
                startPage += onePage;
                curPage += 1;
                requestEmail();
                $(".glyphicon-chevron-left").css("display", "block");
            }

        });

        function requestEmail() {
            // $(".email").empty();
            //获取邮件
            $.ajax({
                type: "get",
                url: "/vocations",
                dataType: "json",
                data: {

                },
                success: function(data) {
                    var noReadEmail = [];
                    var allEmail = [];
                    for (var i in data) {
                        if (data[i].read_state == 'unRead') {
                            noReadEmail.push(data[i]);
                            allEmail.push(data[i]);
                        }
                    }
                    for (var i in data) {
                        if (data[i].read_state != 'unRead') {
                            allEmail.push(data[i]);
                        }
                    }
                    $(".mailNum").html(noReadEmail.length);
                    if (data.length == 0) {
                        $(".noEmail").css("display", "block");
                    } else {
                        page = Math.floor(data.length / onePage) + 1;
                        if (page == 1 || curPage == page) {
                            endPage = data.length;
                        } else {
                            endPage = startPage + onePage;
                        }
                        for (var i = startPage; i < endPage; i++) {
                            var oneEmail = $('<div class="one clearfix ' + allEmail[i].read_state + '">' +
                                '<div class="col-sm-1 pull-left text-center">' +
                                '<input type="checkbox" name="emailCheck" class="emailCheck" index = "' + allEmail[i].id + '">' +
                                '</div>' +
                                '<div class="col-sm-2 pull-left text-center">' +
                                '<span>发件人：</span><strong>' + allEmail[i].admin + '</strong>' +
                                '<div>' +
                                '<p>' + allEmail[i].time + '</p>' +
                                '<p>' + allEmail[i].date + '</p>' +
                                '</div>' +
                                '</div>' +
                                '<div class="col-sm-8 pull-left">' +
                                '<div>' +
                                '<span>请假日期：' + allEmail[i].leave_date + '</span>' +
                                '<p>' + allEmail[i].all_content + '</p>' +
                                '</div>' +
                                '<span class="toggleMark glyphicon glyphicon-chevron-up"></span>' +
                                '</div>' +
                                '</div>');
                            $(".email").append(oneEmail);
                        }
                        $('.emailCheck').iCheck({
                            checkboxClass: 'icheckbox_square-blue',
                            radioClass: 'iradio_square-blue',
                            increaseArea: '20%'
                        });
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
                        $(".toggleMark").trigger("click");
                    }
                },
                error: function(error) {
                    console.log(error);
                }
            })
        }
    })
});