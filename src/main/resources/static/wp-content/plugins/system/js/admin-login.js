(function() {

    // 本地验证的提示信息
    const Tips = {
        attr: 'tips',
        username: {
            isNull: '账号不能为空！',
        },
        password: {
            isNull: '密码不能为空！',
        },
        checkcode: {
            isNull: '验证码不能为空！',
        }
    }

    // 表单状态码
    const FormState = {
        submit: true,
        username: false,
        password: false,
        checkcode: false
    }

    // DOM
    const form = document.getElementById("form");
    const inputUsername = document.getElementById("login_username");
    const inputPassword = document.getElementById("login_password");
    const inputCheckcode = document.getElementById("login_checkcode");
    const checkcodeImage = document.getElementById("checkcode_img");
    const buttonSubmit = document.getElementById("login_button");

    // 为input文本框绑定去除空格事件,禁止输入空格
    $C.bindEvent.add(inputUsername, 'oninput', $C.fns.clearBlank);
    $C.bindEvent.add(inputPassword, 'oninput', $C.fns.clearBlank);
    $C.bindEvent.add(inputCheckcode, 'oninput', $C.fns.clearBlank);
    $C.bindEvent.add(checkcodeImage, 'onclick', function() {
        $C.fns.getCheckCodeImg(checkcodeImage);
    });
    // 文本框失去焦点触发
    $C.bindEvent.add(inputUsername, 'onblur', function() {
        verifyParam(this, Tips.username, 'username');
    });
    $C.bindEvent.add(inputPassword, 'onblur', function() {
        verifyParam(this, Tips.password, 'password');
    });
    $C.bindEvent.add(inputCheckcode, 'onblur', function() {
        verifyParam(this, Tips.checkcode, 'checkcode');
    });

    // 点击登录按钮提交表单数据
    $C.bindEvent.add(buttonSubmit, 'onclick', function() {
        submitForm();
    });
    // 按下回车(Enter)键后提交表单数据
    $C.key.pressEnter(function() {
        submitForm();
    });

    /**
     * 验证表单数据
     * @param e DOM
     * @param t Tips
     * @param f state
     */
    function verifyParam(e, t, stateName) {
        var v = e.value;
        var son = $C.fns.getSub(e.parentNode, Tips.attr);
        // 验证是否为空值
        if ($C.fns.valueIsNull(v)) {
            $C.fns.setTips(son, Tips.attr, '', true);
            FormState[stateName] = true;
            return;
        } else {
            $C.fns.setTips(son, Tips.attr, t.isNull, false);
        }
        FormState[stateName] = false;
    }

    /**
     * 提交表单数据
     */
    function submitForm() {
        // 本地验证表达数据
        if (!FormState.username) {
            verifyParam(inputUsername, Tips.username, 'username');
        }
        if (!FormState.password) {
            verifyParam(inputPassword, Tips.password, 'password');
        }
        if (!FormState.checkcode) {
            verifyParam(inputCheckcode, Tips.checkcode, 'checkcode');
        }
        if (FormState.username && FormState.password && FormState.checkcode) {
            if (FormState.submit) {
                FormState.submit = false;
                var closeDialog = RosDialog.dialog.wait();
                // 获取表单数据
                var data = $C.fns.getParameter(form);
                $C.RSA.getPublicKey({
                    async: true,
                    success: function(key) {
                        FormState.submit = true;
                        // 使用公钥加密密码
                        var cipher = $C.RSA.encrypt(data.password, key);
                        data.password = cipher;
                        FormState.submit = false;
                        // 提交表单数据,开始登录
                        $.ajax({
                            type: 'post',
                            url: '',
                            data: data,
                            dataType: 'json',
                            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                            success: function(data) {
                                FormState.submit = true;
                                closeDialog();
                                if (data.state == 'success') {
                                    // 登录成功
                                    window.location.href = '/message.html';
                                    return;
                                }
                                $C.fns.getCheckCodeImg(checkcodeImage);
                                RosDialog.dialog.open(data.content, 5000);
                            },
                            error: function() {
                                FormState.submit = true;
                                closeDialog();
                                RosDialog.dialog.open();
                            }
                        });
                    },
                    error: function() {
                        FormState.submit = true;
                        closeDialog();
                    }
                });
            }
        }
    }
})();