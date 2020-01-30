const sideBarMeun = document.getElementById('side_bar_meun');
const meunSubjects = sideBarMeun.getElementsByClassName('meun-subject');
// 为每个父级菜单添加'onclick'事件
var flag = true;
for (var i = 0; i < meunSubjects.length; i++) {
    var s = $C.fns.getSub(meunSubjects[i], 'son-meun')
    if (s != null) {
        if (s.style.display != 'block') {
            s.style.display = 'none';
        }
        $C.bindEvent.add(meunSubjects[i], 'onclick', function() {
            if (flag) {
                flag = false;
                // 显示OR隐藏子菜单
                var ico = this.getElementsByTagName('i')[0];
                var son = $C.fns.getSub(this, 'son-meun');
                var lis = son.getElementsByTagName('li');
                if (son.style.display == 'none') {
                    son.style.display = 'block';
                    ico.className = 'hide-ico';
                    for (var x = 0; x < lis.length; x++) {
                        lis[x].className = 'show-item';
                    }
                    flag = true;
                } else {
                    ico.className = 'show-ico';
                    for (var x = 0; x < lis.length; x++) {
                        lis[x].className = 'hide-item';
                    }
                    setTimeout(function() {
                        son.style.display = 'none';
                        flag = true;
                    }, 250);
                }
            }
        });
    }
}