(function() {
    $.ajax({
        type: "GET",
        url: "http://localhost:7070/get",
        dataType: "json",
        data: {

        },
        success: function(data) {
            // alert(data);
            var oTbody = $('tbody');
            for (var i = 0; i < data.length; i++) {
                var member = $('<tr>' +
                    '<td>' + data[i].staffId + '</td>' +
                    '<td>' + data[i].staffName + '</td>' +
                    '<td><button class = "btn btn-primary signIn" >签到</button></td>' +
                    '<td><button class = "btn btn-danger signOut hidden">签退</button></td>' +
                    '</tr>')
                oTbody.append(member);
            }
            var inBtn = $('.signIn');
            var outBtn = $('.signOut');
            for (var i = 0; i < inBtn.length; i++) {
                inBtn.eq(i).attr('index', i);
                outBtn.eq(i).attr('index', i);
                addHandler(inBtn[i], 'click', function() {
                    var oDate = new Date();
                    var index = $(this).attr('index');
                    inBtn.eq(index).toggleClass("hidden");
                    outBtn.eq(index).toggleClass("hidden");
                    // $.post("", {
                    //     staffId: $(this).attr('index'),
                    //     inTime: ''
                    // })
                })
                addHandler(outBtn[i], 'click', function() {
                    var oDate = new Date();
                    var index = $(this).attr('index');
                    inBtn.eq(index).toggleClass("hidden");
                    outBtn.eq(index).toggleClass("hidden");
                    // $.post("", {
                    //     staffId: $(this).attr('index'),
                    //     outTime: ''
                    // })
                })
            }
        },
        error: function(jqXHR, textStatus) {
            alert("请刷新重试");
        }
    })

    function addHandler(element, type, handler) {
        if (element.addEventListener) {
            element.addEventListener(type, handler, false);
        } else if (element.attachEvent) {
            element.attachEvent('on' + type, handler);
        } else {
            element['on' + type] = handler;
        }
    }
})();