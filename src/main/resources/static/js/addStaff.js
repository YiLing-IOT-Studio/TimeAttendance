(function() {
    $.ajax({
        type: "GET",
        url: "",
        dataType: "json",
        data: {

        },
        success: function(data) {
            var oTbody = $('.tbody');
            for (var i = 0; i < data.length; i++) {
                var member = $('<tr>' +
                    '<td>' + data[i].staffId + '</td>' +
                    '<td>' + data[i].staffName + '</td>' +
                    '<td><button class = "btn btn-primary signIn show" >签到</button></td>' +
                    '<td>< button class = "btn btn-danger signOut hidden" > 签退 < /button></td >' +
                    '</tr>')
                oTbody.append(member);
            }
            var inBtn = $('.signIn');
            var outBtn = $('.signOut');
            for (var i = 0; i < inBtn.length; i++) {
                addHandler(inBtn[i], 'click', function() {
                    var _this = this;
                    $.ajax({

                    })
                })
            }
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