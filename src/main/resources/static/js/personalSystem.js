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
});