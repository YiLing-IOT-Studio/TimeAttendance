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

});