(function() {

    /**
     * 初始化 Close 条,关闭对话框模块,用于绑定主动关闭对话框
     * @param box 对话框DOM元素
     * @param title 标题
     * @returns {HTMLDivElement} Close - DOM
     */
    function buildCloseBox(box, title, closeMethod) {
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
            RosDialog.dialog.close(box, closeMethod);
        });
        return closeBox;
    }

    /**
     * 初始化对话框
     * @param data 对话框初始化信息
     * @returns {HTMLDivElement} 对话框DOM元素
     */
    function initBox(data) {
        RosDialog.dialog.close();
        var wraps = document.getElementsByClassName('dialog-wrap');
        var dialog = document.createElement('div');
        if (wraps != undefined && wraps != null && wraps.length > 0) {
            dialog.id = 'dialog_' + (wraps.length + 1);
        } else {
            dialog.id = 'dialog_1';
        }
        RosDialog.item.add(dialog.id);
        dialog.className = 'dialog-wrap';
        var box = document.createElement('div');
        if (data.boxName != undefined && data.boxName != null && data.boxName != '') {
            box.className = 'dialog-box ' + data.boxName;
        } else {
            box.className = 'dialog-box';
        }
        // 添加close条
        box.appendChild(buildCloseBox(dialog, data.title, data.closeMethod));
        var contentBox = document.createElement('div');
        contentBox.className = "dialog-content";
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
            buttonBox.className = 'dialog-button';
            if ($C.fns.isFunction(data.button)) {
                buttonBox.appendChild(data.button());
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

    /**
     * 创建对话框的工具包
     * @type {{dialog: {slider: slider, wait: (function(*=): function(...[*]=)), login: login, close: close, open: open}, item: {add: add, remove: remove}}}
     */
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

        // 内置对话框
        dialog: {
            /**
             * 关闭,并移除对话框
             * @param box 要关闭的DOM节点
             * @param fn 移除对话框前执行的函数
             */
            close: function(box, fn) {
                if ($C.fns.isFunction(fn)) {
                    fn();
                }
                if (box != undefined && box != null && box.parentNode != null) {
                    box.parentNode.removeChild(box);
                }
            },

            /**
             * 弹出消息提示框
             */
            open: function(text, time, confirmMethod, cancelMethod) {
                var confirm; // '确定'按钮
                var cancel; // '取消'按钮
                // 初始化对话框
                var dialogBox = initBox({
                    boxName: 'message-box',
                    content: function() {
                        // '网络故障！'
                        var content = document.createElement("div");
                        if (text == undefined) {
                            text = "网络故障！";
                            content.innerHTML = text;
                        } else if (text == null) {
                            text = '服务器拒绝了你的请求！';
                            content.innerHTML = text;
                        } else if ($C.fns.isFunction(text)) {
                            content.appendChild(text);
                        } else {
                            content.innerHTML = text;
                        }
                        return content;
                    },
                    button: function() {
                        var buttonBox = document.createElement('div');
                        buttonBox.className = 'button-box';
                        var confirmButton = document.createElement('button');
                        confirmButton.type = 'button';
                        confirmButton.className = 'button confirm';
                        confirmButton.innerHTML = '确认';
                        confirm = confirmButton;
                        var cancelButton = document.createElement('button');
                        cancelButton.type = 'button';
                        cancelButton.className = 'button cancel';
                        cancelButton.innerHTML = '取消';
                        cancel = cancelButton;
                        buttonBox.appendChild(confirmButton);
                        buttonBox.appendChild(cancelButton);
                        return buttonBox;
                    }
                });

                // 为对话框组件绑定函数
                // 为'确定'按钮绑定函数，点击'确定'按钮时触发调用
                $C.bindEvent.add(confirm, 'onclick', function() {
                    if ($C.fns.isFunction(confirmMethod)) {
                        confirmMethod();
                    }
                    //console.log("点击'确定'按钮");
                    RosDialog.dialog.close(dialogBox);
                });
                // 为'取消'按钮绑定函数，点击'取消'按钮时触发调用
                $C.bindEvent.add(cancel, 'onclick', function() {
                    if ($C.fns.isFunction(cancelMethod)) {
                        cancelMethod();
                    }
                    //console.log("点击'取消'按钮");
                    RosDialog.dialog.close(dialogBox);
                });

                // 设置自动关闭对话框的函数，如果没有设置，对话框则一直存在
                if (time != undefined && time != null && time != '' && time > 0) {
                    setTimeout(function() {
                        RosScript.dialog.close(dialogBox);
                    }, time);
                }
            },

            /**
             * 弹出登录框
             */
            login: function() {
                var usernameInput;
                var passwrodinput;
                var submitButton;
                var formList;
                var formState = true;

                // 初始化登录框
                initBox({
                    title: '登录',
                    boxName: 'login-box',
                    content: function() {
                        var loginForm = document.createElement('form');
                        loginForm.className = 'login-form';
                        formList = loginForm;
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

                // 为form表单组件绑定函数
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
                        RosDialog.dialog.slider(function() {
                            //console.log("验证通过！");
                            // 获取表单数据
                            var userdata = $C.fns.getParameter(formList);
                            userdata['url'] = window.location.href;
                            // 弹出等待层
                            var waitClose = RosDialog.dialog.wait();
                            if (formState) {
                                formState = false;
                                //console.log(userdata);
                                $.ajax({
                                    type: 'post',
                                    url: '/login',
                                    data: userdata,
                                    dataType: 'json',
                                    contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                                    success: function(reply) {
                                        formState = true;
                                        waitClose();
                                        if (reply.state == 'success') {
                                            // 登录成功
                                            window.location.href = '/message.html';
                                        } else {
                                            RosDialog.dialog.open(reply.content, 5000);
                                        }
                                    },
                                    error: function() {
                                        formState = true;
                                        waitClose();
                                        RosDialog.dialog.open();
                                    }
                                });
                            }
                        });
                    }
                });


            },

            /**
             * 滑动验证条
             * @param callback 回调函数
             * @param time 延迟时间,默认1000毫秒
             */
            slider: function(callback, time) {
                if (!$C.fns.isFunction(callback)) {
                    throw "callback 不是函数！";
                }
                if (!$C.fns.valueIsNull(time) || time <= 0) {
                    time = 1000;
                }
                // 初始化滑块验证框
                var dialogBox = initBox({
                    title: '滑块验证',
                    boxName: 'slide-box',
                    content: function() {
                        var slideBox = document.createElement("div");
                        slideBox.className = "slide-bar";
                        slideBox.innerHTML = "<div class='text'>将滑块滑动到右边验证</div>";
                        var tips = document.createElement("span");
                        tips.className = 'tips';
                        tips.style.display = 'none';
                        slideBox.appendChild(tips);
                        var slider = document.createElement("i");
                        slider.className = 'slider';
                        slideBox.appendChild(slider);
                        $C.bindEvent.add(slider, 'onmousedown', function(e) {
                            //鼠标点击物体那一刻相对于物体左侧边框的距离=点击时的位置相对于浏览器最左边的距离-物体左边框相对于浏览器最左边的距离  
                            var diffX = e.clientX - slider.offsetLeft;
                            document.onmousemove = function(e) {
                                var left = e.clientX - diffX;
                                // 限制滑块只能在指定范围内滑动
                                if (left < 2) {
                                    left = 2;
                                } else if (left > 225) {
                                    left = 225;
                                }
                                //移动时重新得到物体的距离，解决拖动时出现晃动的现象  
                                slider.style.left = left + 'px';
                            };
                            //当鼠标弹起来的时候不再移动  
                            document.onmouseup = function(e) {
                                this.onmousemove = null
                                //预防鼠标弹起来后还会循环（即预防鼠标放上去的时候还会移动） 
                                this.onmouseup = null;
                                // 鼠标弹起后，滑块没有滑动到最右边，则复位到最左边
                                if (slider.style.left != '225px') {
                                    slider.style.left = '2px';
                                } else {
                                    // 滑块移动到最右边，执行回调函数
                                    tips.style.display = 'block';
                                    setTimeout(function() {
                                        callback();
                                        RosDialog.dialog.close(dialogBox);
                                    }, time);
                                }
                            };
                        });
                        return slideBox;
                    },
                });
            },


            /**
             * 弹出等待层，防止用户在客户端请求服务器时进行某些操作。例如上传文件时弹出此模块，防止用户干扰上传过程。
             * @returns {function(...[*]=)} // 返回了一个无参数的回调函数，用于关闭此模块，可以调用此函数关闭等待层
             */
            wait: function(content) {
                // var text = '';
                // if (content != undefined && content != null) {
                //     text = content;
                // }
                var wrap = document.createElement("div");
                wrap.className = 'dialog-wrap';
                wrap.innerHTML = "<div class='dialog-wait'><i class='wait-circle'></i><div class='wait-text'>Logding···</div><div class='wait-text'></div></div>";
                document.body.appendChild(wrap);
                return function() {
                    wrap.parentNode.removeChild(wrap);
                }
            }

        }
    }

    window.RosDialog = RosDialog;
})();
