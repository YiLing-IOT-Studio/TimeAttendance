(function() {
    function addStaff() {
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
                        '<td><button class = "btn btn-primary signIn ' + data[i].mark1 + '" >签到</button></td>' +
                        '<td><button class = "btn btn-danger signOut ' + data[i].mark2 + '">签退</button></td>' +
                        '</tr>')
                    oTbody.append(member);
                }
                var inBtn = $('.signIn');
                var outBtn = $('.signOut');
                for (var i = 0; i < inBtn.length; i++) {
                    addHandler(inBtn[i], 'click', function() {
                        var _this = $(this);
                        $.post("", {
                            staffName: _this.parent().prev().text()
                        }, function() {
                            addStaff();
                        })
                    })
                    addHandler(outBtn[i], 'click', function() {
                        var _this = $(this);
                        $.post("", {
                            staffName: _this.parent().prev().prev().text()
                        }, function() {
                            addStaff();
                        })
                    })
                }
            },
            error: function(jqXHR, textStatus) {
                console.log(jqXHR + ", " + textsStatus);
                alert("请刷新重试");
            }
        })
    }

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