(function() {
    function toDouble(n) {
        if (n < 10) {
            return '0' + n;
        } else {
            return '' + n;
        }
    }
    var oTime = document.querySelector('.time');
    var aImg = oTime.querySelectorAll('img');
    //时间
    function tick() {
        var oDate = new Date();
        var arr = toDouble(oDate.getHours()) + toDouble(oDate.getMinutes()) +
            toDouble(oDate.getSeconds());
        for (var i = 0; i < aImg.length; i++) {
            aImg[i].className = "num num" + arr[i];
        }
    }
    setInterval(tick, 1000);
    //日期
    (function newDate() {
        var oDate = new Date();
        var oYear = document.querySelector('.year');
        var oMonth = document.querySelector('.month');
        var oDay = document.querySelector('.day');
        var oWeek = document.querySelector('.week');
        var aWeek = ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日']
        oYear.value = oDate.getFullYear() + '年';
        oMonth.value = oDate.getMonth() + 1 + '月';
        oDay.value = oDate.getDate() + '日';
        oWeek.innerHTML = aWeek[oDate.getDay()];
    })();
    //查找
    (function search() {
        var oSearchBtn = document.querySelector('.searchBtn');
        var oSearchText = document.querySelector('.search input');

        function addHandler(element, type, handler) {
            if (element.addEventListener) {
                element.addEventListener(type, handler, false);
            } else if (element.attachEvent) {
                element.attachEvent('on' + type, handler);
            } else {
                element['on' + type] = handler;
            }
        }
        var handler = function() {
            var arr = oSearchText.value.toLowerCase().split(' ');
            var oTab = document.querySelector('table');
            // if (oSearchText.value != '') {
            for (var i = 0; i < oTab.tBodies[0].rows.length; i++) {
                for (var t = 0; t < arr.length; t++) {
                    if (oTab.tBodies[0].rows[i].cells[1].innerHTML.toLowerCase().search(arr[t]) != -1 && oSearchText.value != '') {
                        oTab.tBodies[0].rows[i].style.background = 'white';
                    } else {
                        oTab.tBodies[0].rows[i].style.background = '';
                    }
                }
            }
            // }
        }
        addHandler(oSearchBtn, 'click', handler);

    })();
})();