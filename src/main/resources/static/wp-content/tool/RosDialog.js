(function() {

    function buildCloseBox(box, title) {
        var closeBox = document.createElement('div');
        closeBox.className = 'dialog-close';
        if (title != undefined && title != null) {
            closeBox.innerHTML = "<span class='title'>" + title + "</span>";
        }
        var close = document.createElement('i');
        close.className = 'close';

        close.innerHTML = "<i></i>";
        closeBox.appendChild(close);
        // 点击右上角的X图标关闭对话框
        $C.bindEvent.add(close, 'onclick', function() {
            RosDialog.dialog.close(box);
        });
        return closeBox;
    }

    function initBox(data) {
        RosDialog.dialog.close();
        var wraps = document.getElementsByClassName('dialog-wrap');
        var dialog = document.createElement('div');
        if (wraps != undefined && wraps != null && wraps.length > 0) {
            dialog.id = 'dialog_' + wraps.length + 1;
        } else {
            dialog.id = 'dialog_1';
        }
        RosDialog.item.add(dialog.id);
        dialog.className = 'dialog-wrap';
        var box = document.createElement('div');
        if (data.boxName != undefined && data.boxName != null && data.boxName != '') {
            box.className = data.boxName + ' dialog-box';
        } else {
            box.className = 'dialog-box';
        }
        // 添加close条
        box.appendChild(buildCloseBox(dialog, data.title));
        var contentBox = document.createElement('div');
        contentBox.className = "content-box";
        if ($C.fns.isFunction(data.content)) {
            contentBox.appendChild(data.content());
        } else {
            contentBox.innerHTML = data.content;
        }
        // contentBox.appendChild(data.content);
        box.appendChild(contentBox);
        // 添加底部按钮
        if (data.button != undefined && data.button != null) {
            var buttonBox = document.createElement('div');
            buttonBox.className = 'button-box';
            if ($C.fns.isFunction(data.button)) {
                contentBox.appendChild(data.button());
            } else {
                buttonBox.innerHTML = data.button;
            }
            box.appendChild(buttonBox);
        }
        dialog.appendChild(box);
        document.body.appendChild(dialog);
        return dialog;
    }

    const DialogItem = new Array();

    const RosDialog = {
        item: {
            add: function(id) {
                DialogItem.push(id);
            },
            remove: function(id) {
                if (id != undefined && id != null && id != '') {
                    for (var i = 0; i < DialogItem.length; i++) {
                        if (DialogItem[i] == id) {
                            DialogItem[i].remove(i);
                        }
                    }
                }
            }
        },
        dialog: {
            close: function(box) {
                if (box != undefined && box != null) {
                    box.parentNode.removeChild(box);
                }
            },
            open: function() {
                initBox({
                    content: '对话框测试！',
                });
            },
            login: function() {
                var usernameInput;
                var passwrodinput;
                var submitButton;
                const FormState = {
                    username: false,
                    password: false,
                    formState: true,
                };


                // 初始化登录框
                initBox({
                    title: '登录',
                    boxName: 'login-box',
                    content: function() {
                        var loginForm = document.createElement('form');
                        loginForm.className = 'login-form';
                        var ul = document.createElement('ul');

                        var li_1 = document.createElement('li');
                        li_1.className = "item";
                        var li_1_i1 = document.createElement('div');
                        li_1_i1.className = 'username-box';
                        var li_1_put = document.createElement('input');
                        li_1_put.type = 'text';
                        li_1_put.name = 'username';
                        li_1_put.placeholder = '账号';
                        usernameInput = li_1_put;
                        li_1_i1.appendChild(li_1_put);
                        var li_1_i2 = document.createElement('span');
                        li_1_i2.className = 'tips';
                        li_1.appendChild(li_1_i1);
                        li_1.appendChild(li_1_i2);
                        ul.appendChild(li_1);

                        var li_2 = document.createElement('li');
                        li_2.className = "item";
                        var li_2_i1 = document.createElement('div');
                        li_2_i1.className = 'password-box';
                        var li_2_put = document.createElement('input');
                        li_2_put.type = 'password';
                        li_2_put.name = 'password';
                        li_2_put.placeholder = '密码';
                        passwrodinput = li_2_put;

                        li_2_i1.appendChild(li_2_put);
                        var li_2_i2 = document.createElement('span');
                        li_2_i2.className = 'tips';
                        li_2.appendChild(li_2_i1);
                        li_2.appendChild(li_2_i2);
                        ul.appendChild(li_2);

                        // var li_3 = document.createElement('li');
                        // li_3.className = "item";
                        // var li_3_i1 = document.createElement('div');
                        // li_3_i1.className = 'checkcode-box';
                        // var li_3_put = document.createElement('input');
                        // li_3_put.type = 'text';
                        // li_3_put.name = 'checkcode';
                        // li_3_put.placeholder = '验证码';
                        // li_3_i1.appendChild(li_3_put);
                        // var li_3_img = document.createElement('img');
                        // li_3_img.className = 'checkcode-img';
                        // li_3_img.src = '/sys/get/img/checkcode.jpeg';
                        // li_3_i1.appendChild(li_3_img);
                        // var li_3_i2 = document.createElement('span');
                        // li_3_i2.className = 'tips';
                        // li_3.appendChild(li_3_i1);
                        // li_3.appendChild(li_3_i2);
                        // ul.appendChild(li_3);

                        var item = document.createElement('li');
                        var button = document.createElement('a');
                        button.className = "login-button";
                        button.innerHTML = "登 录";
                        submitButton = button;
                        item.appendChild(button);
                        ul.appendChild(item);
                        loginForm.appendChild(ul);
                        return loginForm;
                    },
                    button: function() {
                        var link = document.createElement('a');
                        link.className = 'box-link';
                        link.innerHTML = "账号注册";
                        link.href = '/register';
                        return link;
                    }
                });

                $C.bindEvent.add(usernameInput, 'oninput', $C.fns.clearBlank);
                $C.bindEvent.add(passwrodinput, 'oninput', $C.fns.clearBlank);
                $C.bindEvent.add(submitButton, 'onclick', function() {
                    var username = usernameInput.value;
                    var password = passwrodinput.value;
                    var uf = $C.fns.paramIsNull(usernameInput, function(e, f) {
                        var tips = $C.fns.getSub(e.parentNode, 'tips');
                        if (f) {
                            $C.fns.setTips(tips, 'tips', '', true);
                        } else {
                            $C.fns.setTips(tips, 'tips', '账号不能为空！', false);
                        }
                    });
                    var pf = $C.fns.paramIsNull(passwrodinput, function(e, f) {
                        var tips = $C.fns.getSub(e.parentNode, 'tips');
                        if (f) {
                            $C.fns.setTips(tips, 'tips', '', true);
                        } else {
                            $C.fns.setTips(tips, 'tips', '密码不能为空！', false);
                        }
                    });
                    // 参数验证通过
                    if (uf && pf) {
                        // 弹出验证码对话框，输入验证码！
                        RosDialog.dialog.checkcode();
                    }
                });


            },
            checkcode: function() {
                console.log("弹出验证码对话框！");
            }
        }
    }

    window.RosScript = RosDialog;
})();
