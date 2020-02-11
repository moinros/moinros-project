(function() {
    const FORM_STATE = {
        nickname: false,
        username: false,
        password: false,
        checkcode: false,
        formState: true,
    };


    /**
     * 请求服务器
     * @param value 值
     * @param callback 回调函数
     */
    function requestServer(value, url, callback, error) {
        if ($C.fns.valueIsNull(value)) {
            $.ajax({
                type: 'post',
                url: url,
                data: value,
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
                success: function(data) {
                    callback(data);
                },
                error: function() {
                    RosDialog.dialog.open();
                    $C.fns.runFunction(error);
                }
            });
        }
    }


    let box_nickname = document.getElementById("box_nickname");
    let tips_nickname = $C.fns.getSub(box_nickname.parentNode, 'tips');
    $C.bindEvent.add(box_nickname, 'oninput', function() {
        FORM_STATE.nickname = false;
        $C.fns.clearBlank(box_nickname);
    });
    $C.bindEvent.add(box_nickname, 'onblur', function() {
        verifyNickname();
    });

    function verifyNickname(callback) {
        let str = box_nickname.value;
        if ($C.fns.valueIsNull(str)) {
            if (str.length > 1 && str.length < 25) {
                if (!FORM_STATE.nickname) {
                    requestServer(str, "/is/exist/user/nickname", function(data) {
                        if (data.state == 'OK') {
                            $C.fns.setTips(tips_nickname, 'tips', '验证通过！', true);
                            FORM_STATE.nickname = true;
                            $C.fns.runFunction(callback);
                        } else {
                            $C.fns.setTips(tips_nickname, 'tips', data.content, false);
                            FORM_STATE.nickname = false;
                        }
                    });
                } else {
                    $C.fns.runFunction(callback);
                }
            } else {
                $C.fns.setTips(tips_nickname, 'tips', '昵称只能由 2 ~ 24 个字符组成！', false);
                FORM_STATE.nickname = false;
            }
        } else {
            $C.fns.setTips(tips_nickname, 'tips', '请填写昵称！', false);
            FORM_STATE.nickname = false;
        }
    }


    let box_username = document.getElementById("box_username");
    let tips_username = $C.fns.getSub(box_username.parentNode, 'tips');
    $C.bindEvent.add(box_username, 'oninput', function() {
        FORM_STATE.username = false;
        $C.fns.clearBlank(box_username);
    });
    $C.bindEvent.add(box_username, 'onblur', function() {
        verifyEmail();
    });

    function verifyEmail(callback) {
        let str = box_username.value;
        if ($C.fns.valueIsNull(str)) {
            if ($C.regex.email.test(str)) {
                if (!FORM_STATE.username) {
                    requestServer(str, "/is/exist/user/email", function(data) {
                        if (data.state == 'OK') {
                            $C.fns.setTips(tips_username, 'tips', '验证通过！', true);
                            FORM_STATE.username = true;
                            $C.fns.runFunction(callback);
                        } else {
                            $C.fns.setTips(tips_username, 'tips', data.content, false);
                            FORM_STATE.username = false;
                        }
                    });
                } else {
                    $C.fns.runFunction(callback);
                }
            } else {
                $C.fns.setTips(tips_username, 'tips', '邮箱格式不正确！', false);
                FORM_STATE.username = false;
            }
        } else {
            $C.fns.setTips(tips_username, 'tips', '请填写邮箱！', false);
            FORM_STATE.username = false;
        }
    }

    let box_password = document.getElementById("box_password");
    let tips_password = $C.fns.getSub(box_password.parentNode, 'tips');
    $C.bindEvent.add(box_password, 'oninput', function() {
        FORM_STATE.password = false;
        $C.fns.clearBlank(box_password);
    });
    $C.bindEvent.add(box_password, 'onblur', function() {
        verifyPassword();
    });

    function verifyPassword() {
        let str = box_password.value;
        if ($C.fns.valueIsNull(str)) {
            if (str.length > 5 && str.length < 25) {
                if ($C.regex.password.test(str)) {
                    $C.fns.setTips(tips_password, 'tips', '验证通过！', true);
                    FORM_STATE.password = true;
                } else {
                    $C.fns.setTips(tips_password, 'tips', '密码不能是纯数字！', false);
                    FORM_STATE.password = false;
                }
            } else {
                $C.fns.setTips(tips_password, 'tips', '密码最少6位，最多24位！', false);
                FORM_STATE.password = false;
            }
        } else {
            $C.fns.setTips(tips_password, 'tips', '请填写密码！', false);
            FORM_STATE.password = false;
        }
    }


    let box_checkcode = document.getElementById("box_checkcode");
    let tips_checkcode = $C.fns.getSub(box_checkcode.parentNode, 'tips');
    let get_checkcode = document.getElementById("get_checkcode");

    $C.bindEvent.add(box_checkcode, 'oninput', function() {
        FORM_STATE.checkcode = false;
        $C.fns.clearBlank(box_checkcode);
    });
    $C.bindEvent.add(box_checkcode, 'onblur', function() {
        verifyCheckcode();
    });

    let check = true;
    $C.bindEvent.add(get_checkcode, 'onclick', function() {
        let str = box_username.value;
        if ($C.fns.valueIsNull(str)) {
            if (FORM_STATE.username) {
                //console.log("获取验证码");
                if (check) {
                    check = false;
                    $C.fns.loopTimer(function(data) {
                        if (data.index < 60) {
                            get_checkcode.className = "button no-check";
                            get_checkcode.innerHTML = 60 - data.index + '秒后再次获取';
                        } else {
                            get_checkcode.className = "button";
                            get_checkcode.innerHTML = '点击获取验证码';
                            data.flag = false;
                            check = true;
                        }
                    }, 1000);
                }
                requestServer(str, "/sys/get/mail/checkcode", function(reply) {
                    if (reply.state == "success") {
                        RosDialog.dialog.open(reply.content, 5000);
                        $C.socket.onceTask(function(data) {
                            if (data.state == "MSG") {
                                RosDialog.dialog.msg(data.msg.content, 10000);
                            }
                        });
                    }
                });
            } else {
                RosDialog.dialog.msg("请填写正确，并且可用的邮箱！", 5000);
            }
        } else {
            $C.fns.setTips(tips_username, 'tips', '请填写邮箱！', false);
            FORM_STATE.username = false;
        }
    });

    function verifyCheckcode() {
        let code = box_checkcode.value;
        if ($C.fns.valueIsNull(code)) {
            $C.fns.setTips(tips_checkcode, 'tips', '验证通过！', true);
            FORM_STATE.checkcode = true;
        } else {
            $C.fns.setTips(tips_checkcode, 'tips', '请填写验证码！', false);
            FORM_STATE.checkcode = false;
        }
    }

    let submit_form = document.getElementById("submit_form");
    $C.bindEvent.add(submit_form, 'onclick', function() {
        verifyParams();
    });

    function verifyParams() {
        verifyCheckcode();
        verifyPassword();
        verifyEmail(submitForm);
        verifyNickname(submitForm);
    }


    function submitForm() {
        var params = $C.fns.getParameter(form);
        if (FORM_STATE.nickname && FORM_STATE.username && FORM_STATE.password && FORM_STATE.checkcode) {
            if (FORM_STATE.formState) {
                FORM_STATE.formState = false;
                RosDialog.dialog.slider(function() {
                    let closeWait = RosDialog.dialog.wait();
                    console.log("提交表单数据");
                    $.ajax({
                        type: 'post',
                        url: '',
                        data: params,
                        dataType: 'json',
                        contentType: $C.ajax.contentType.form,
                        success: function(reply) {
                            FORM_STATE.formState = true;
                            closeWait();
                            if (reply.state == 'success') {
                                // 注册成功
                                window.location.href = '/message.html';
                            } else {
                                RosDialog.dialog.msg(reply.content, 10000);
                            }
                        },
                        error: function() {
                            FORM_STATE.formState = true;
                            closeWait();
                            RosDialog.dialog.open();
                        }
                    });
                });
            }
        }
        console.log(params);
    }

    const form = document.getElementById("register_form");
    let inputs = form.getElementsByTagName('input');
    for (let i = 0; i < inputs.length; i++) {
        $C.bindEvent.add(inputs[i], 'onfocus', function() {
            $C.key.pressEnter(verifyParams);
        });
    }
})();