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
            $(".email").empty();
            requestEmail();
            $(".glyphicon-chevron-right").css("display", "block");
        });
        // $(".markRead").click(function() {
        //     var arr = [];
        //     $(".email input[type = 'checkbox']").each(function(i, val) {
        //         if (val.checked) {
        //             arr.push(val.attr('id'));
        //         }
        //     })
        //     $.ajax({
        //         type: "post",
        //         url: "",
        //         dataType: "json",
        //         data: {
        //             "markRead": arr
        //         },
        //         success: function(data) {
        //             requestEmail();
        //         },
        //         error: function(error) {
        //             console.log(error);
        //             // alert("操作失败，请重试");
        //         }
        //     })
        // });
        function sendArr($className, dataName) {
            $("." + $className).click(function() {
                var arr = [];
                $(".email input[type = 'checkbox']").each(function(i, val) {
                    if (val.attr(checked)) {
                        arr.push(val.attr('type'));
                    }
                })
                $.ajax({
                    type: "post",
                    url: "",
                    dataType: "json",
                    data: {
                        dataName: arr
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
        }
        sendArr("markRead", "markRead");
        sendArr("delete", "delete");
        //下一页，上一页
        $(".glyphicon-chevron-left").click(function() {
            if (curPage - 1 == 1) {
                $(this).css("display", "none");
            }
            if (curPage > 0) {
                startPage -= onePage;
                curPage -= 1;
                $(".email").empty();
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
                $(".email").empty();
                requestEmail();
                $(".glyphicon-chevron-left").css("display", "block");
            }

        });

        function requestEmail() {
            //获取邮件
            $.ajax({
                type: "get",
                url: "http://localhost:7070/post",
                dataType: "json",
                data: {

                },
                success: function(data) {
                    var noReadEmail = [];
                    for (var i in data) {
                        if (data[i].status == 'unRead') {
                            noReadEmail.push(data[i]);
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
                            var oneEmail = $('<div class="one clearfix ' + data[i].status + '" type="' + data[i].id + '">' +
                                '<div class="col-sm-1 pull-left text-center">' +
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

                    }
                },
                error: function(error) {
                    console.log(error);
                }
            })
        }
    })
});