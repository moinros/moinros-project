/**
 *
 */
(function() {

    const RosScript = {
        regex: {
            // 匹配任意非空白字符,并且不能是纯数字,不能由任意单个重复字符组成;
            nickname: /^(?!(.)\1+$)(?!\d+$)[^\s]+$/,
            // 匹配邮箱的正则表达式
            email: /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/,
            // 匹配密码的正则表达式
            password: /^(?!(.)\1+$)(?!\d+$)[^\s]+$/,
            // 匹配空格的正则表达式
            blank: /[, ]/g,
            // 匹配任意连续的重复字符
            repeated: /^(.)\1+$/,
            // 匹配纯数字
            number: /^\d+$/,
        },
        key: {
            /**
             * 按下'回车键'后执行指定方法
             */
            pressEnter: function(fn) {
                document.onkeydown = function(ev) {
                    if (ev.key == 'Enter') {
                        fn();
                    }
                }
            },
        },

        /**
         * 定义一些常用的公用方法
         */
        fns: {

            /**
             * 添加方法到RosScript.fns中
             * @param k 方法名
             * @param v 方法体
             */
            add: function(k, v) {
                if (RosScript.fns[k] == undefined || RosScript.fns[k] == null) {
                    RosScript.fns[k] = v;
                } else {
                    throw "[ " + k + " ] 方法已经存在！不能重复添加";
                }
            },

            /**
             * 判断是否为function
             * @param fn
             * @returns {boolean}
             */
            isFunction: function(fn) {
                return typeof fn === 'function';
                // return Object.prototype.toString.call(fn) === '[object Function]';
            },

            /**
             * 定时器
             * @param fn 需要执行的函数
             * @param index 执行的次数
             * @param time 每次执行的间隔时间,默认1000
             */
            timer: function(fn, index) {
                fn(index);
                index--;
                if (index >= 0) {
                    setTimeout(function() {
                        RosScript.fns.timer(fn, index);
                    }, 1000);
                } else {
                    fn(index);
                }
            },

            /**
             * 循环计时器
             * @param fn 需要执行的函数
             * @param time 每次执行的间隔时间
             */
            loopTimer: function(fn, time) {
                fn();
                setTimeout(function() {
                    RosScript.fns.loopTimer(fn, time);
                }, time);
            },

            /**
             * 清除空格
             */
            clearBlank: function(e) {
                if (e == undefined || e == null || e.type == 'input') {
                    e = this;
                }
                var v = e.value;
                if (v == undefined || v == null) {
                    return false;
                }
                if (v.indexOf(' ') >= 0) {
                    e.value = v.replace(/\s/g, '');
                    return false;
                } else {
                    return true;
                }
            },

            /**
             * 设置提示信息
             *
             * @param e 需要设置的对象
             * @param a 提示选择器ID
             * @param c 提示内容
             * @param f 提示颜色
             */
            setTips: function(e, a, c, f) {
                if (f) {
                    e.className = 'tips tips-success';
                    e.setAttribute(a, c);
                } else {
                    e.className = 'tips tips-error';
                    e.setAttribute(a, c);
                }

            },
            /**
             * 验证空值
             *
             * @Param e [Element]HTML DOM对象
             * @Param c [Callback]回调函数
             */
            paramIsNull: function(e, c) {
                var f = false;
                if (e == undefined || e == null) {
                    return f;
                }
                var v = e.value;
                if (v != undefined && v != null && v != '') {
                    f = true;
                }
                if (c != undefined && c != null && RosScript.fns.isFunction(c)) {
                    c(e, f);
                }
                return f;
            },
            valueIsNull: function(v) {
                if (v == undefined || v == null || v == '') {
                    return false;
                }
                v = v.replace(/\s/g, '');
                if (v == '') {
                    return false;
                }
                return true;
            },

            /**
             * 获取验证码图片
             */
            getCheckCodeImg: function(e) {
                var src = e.src;
                var index = src.indexOf('?');
                e.src = (index > -1 ? src.slice(0, index) : src) + '?' + (Date.now() / 10);
            },

            /**
             * 获取表单提交的数据
             *
             * @Param form 表单DOM对象
             * @Return obj{}
             */
            getParameter: function(form) {
                var item = form.getElementsByTagName('input');
                var obj = {};
                for (var i = 0; i < item.length; i++) {
                    if (item[i].type == "text" || item[i].type == "password"
                        || item[i].type == "select-one"
                        || item[i].type == "tel" || item[i].type == "search"
                        || item[i].type == "range" || item[i].type == "number"
                        || item[i].type == "month" || item[i].type == "email"
                        || item[i].type == "datetime-local"
                        || item[i].type == "datetime" || item[i].type == "date"
                        || item[i].type == "color") {
                        obj[item[i].name] = item[i].value;
                    } else if (item[i].type == "checkbox") {
                        var stamp = true;
                        if (item[i].checked) {
                            for (var n in obj) {
                                if (n == item[i].name) {
                                    obj[n].push(item[i].value);
                                    stamp = false;
                                    break;
                                }
                            }
                            if (stamp) {
                                obj[item[i].name] = [item[i].value];
                            }
                        }
                    } else if (item[i].type == "radio") {
                        if (item[i].checked) {
                            obj[item[i].name] = item[i].value;
                        }
                    }
                }
                var textItem = form.getElementsByTagName('textarea');
                if (textItem != undefined && textItem != null) {
                    for (var i = 0; i < textItem.length; i++) {
                        obj[textItem[i].name] = textItem[i].value;
                    }
                }
                var selectItem = form.getElementsByTagName('select');
                if (selectItem != undefined && selectItem != null) {
                    for (var i = 0; i < selectItem.length; i++) {
                        obj[selectItem[i].name] = selectItem[i].value;
                    }
                }
                return obj;
            },


        },


        /**
         * 为元素添加或者移除event事件
         *
         * @param e     [Element]需要添加事件的DOM元素
         * @param type  需要添加的事件类型,例如: onclick
         * @param fn    触发事件时执行的方法
         * @param isBubble规定事件流
         */
        bindEvent: {
            add: function(e, type, fn, isBubble) {
                if (!isBubble)
                    isBubble = false;
                if (e.addEventListenner) {
                    e.addEventListenner(type, fn, isBubble);
                } else if (e.attachEvent) {
                    Transit = function() {
                        fn.call(e);
                    }
                    e.attachEvent(type, Transit);
                } else {
                    e[type] = fn;
                }
            },
            remove: function(e, type, fn, isBubble) {
                if (!isBubble)
                    isBubble = false;
                if (e.removeEventListenner) {
                    e.removeEventListenner(type, fn, isBubble)
                } else if (e.detachEvent) {
                    e.detachEvent(type, Transit)
                } else {
                    e[type] = null;
                }
            }
        },


        /**
         * 弹出对话框
         */
        dialog: {

            /**
             * 初始化，并弹出对话框
             *
             * @param data 初始化参数
             * data = {
             *          content: null, // 对话框内容
             *          time: 0,    // 对话框自动关闭时间
             *          callback: null, // 回调函数，可以绑定一些需要执行的函数。参数为当前对话框的DOM对象
             *          confirmMethod: null, // 点击确定时执行的函数。参数为当前对话框的DOM对象
             *          closeMethod: null, // 点击取消时执行的函数。参数为当前对话框的DOM对象
             *        }
             *
             */
            init: function(data) {
                var allbox = document.getElementsByClassName('dialog-wrap');
                var boxid = '';
                if (allbox != undefined && allbox != null && allbox.length > 0) {
                    boxid = 'dialog' + allbox.length + 1;
                } else {
                    boxid = 'dialog';
                }

                // 初始化对话框DOM对象
                var dialog = document.createElement('div');
                dialog.id = boxid;
                dialog.className = 'dialog-wrap';

                var dialogBox = document.createElement('div');
                dialogBox.className = 'dialog-box';

                var closeBox = document.createElement('div');
                closeBox.className = 'dialog-close';
                var close = document.createElement('i');
                close.className = 'close';
                close.innerHTML = "<i></i>";
                closeBox.appendChild(close);
                dialogBox.appendChild(closeBox);

                var contentBox = document.createElement('div');
                contentBox.className = 'dialog-content';
                if (data.content == undefined) {
                    contentBox.innerHTML = '网络故障！';
                } else if (data.content == null) {
                    contentBox.innerHTML = '出现了未知错误！请稍后再试！！';
                } else {
                    contentBox.innerHTML = data.content;
                }
                dialogBox.appendChild(contentBox);

                var buttonBox = document.createElement('div');
                buttonBox.className = 'dialog-button';
                var confirmButton = document.createElement('button');
                confirmButton.type = 'button';
                confirmButton.className = 'box-button yes';
                confirmButton.innerHTML = '确认';
                var closeButton = document.createElement('button');
                closeButton.type = 'button';
                closeButton.className = 'box-button no';
                closeButton.innerHTML = '取消';
                buttonBox.appendChild(confirmButton);
                buttonBox.appendChild(closeButton);
                dialogBox.appendChild(buttonBox);

                dialog.appendChild(dialogBox);
                document.body.appendChild(dialog);

                // 为对话框绑定event事件

                // 点击右上角的X图标关闭对话框
                RosScript.bindEvent.add(close, 'onclick', function() {
                    RosScript.dialog.close(boxid);
                });

                // 点击确定关闭对话框,并执行绑定的函数
                RosScript.bindEvent.add(confirmButton, 'onclick', function() {
                    // 如果传入了需要执行的函数则继续执行绑定的函数
                    if (data.confirmMethod != undefined && data.confirmMethod != null && RosScript.fns.isFunction(data.confirmMethod)) {
                        data.confirmMethod(dialog);
                    }
                    // 关闭对话框
                    RosScript.dialog.close(boxid);
                });

                // 点击取消关闭对话框,并执行绑定的函数
                RosScript.bindEvent.add(closeButton, 'onclick', function() {
                    // 如果传入了需要执行的函数则继续执行绑定的函数
                    if (data.closeMethod != undefined && data.closeMethod != null && RosScript.fns.isFunction(data.closeMethod)) {
                        data.closeMethod(dialog);
                    }
                    // 关闭对话框
                    RosScript.dialog.close(boxid);
                });

                // 绑定回调函数
                if (data.callback != undefined && data.callback != null && RosScript.fns.isFunction(data.callback)) {
                    data.callback(dialog);
                }

                // 设置自动关闭对话框的函数，如果没有设置，对话框则一直存在
                if (data.time != undefined && data.time != null && data.time > 0) {
                    setTimeout(function() {
                        RosScript.dialog.close(boxid);
                    }, data.time);
                }

            },

            /**
             * 弹出对话框
             * @param content 对话框内容
             * @param callback 回调函数
             * @param time 自动关闭时间
             */
            open: function(content, time) {
                // 初始化，并弹出对话框
                RosScript.dialog.init({
                    content: content,
                    time: time,
                    callback: null,
                    confirmMethod: null,
                    closeMethod: null,
                });
            },

            /**
             * 关闭对话框
             *
             * @param method
             *            关闭对话框时运行的函数
             */
            close: function(id) {
                var div = document.getElementById(id);
                if (div != undefined && div != null) {
                    div.parentNode.removeChild(div);
                }
            },

            /**
             * 弹出等待层，防止用户在客户端请求服务器时进行某些操作。例如上传文件时弹出此模块，防止用户干扰上传过程。
             * @returns {function(...[*]=)} // 返回了一个无参数的回调函数，用于关闭此模块，可以将此回调函数放入上传文件的回调函数中，文件上传结束时调用，关闭此模块。
             */
            wait: function(content) {
                var text = '';
                if (content != undefined && content != null) {
                    text = content;
                }
                var wrap = document.createElement("div");
                wrap.className = 'dialog-wrap';
                wrap.innerHTML = "<div class='dialog-wait'><i class='wait-circle'></i><div class='wait-text'>Logding···</div><div class='wait-text'>" + text + "</div></div>";
                document.body.appendChild(wrap);
                return function() {
                    wrap.parentNode.removeChild(wrap);
                }
            }
        },

        /**
         * 弹出对话框,选择文件上传到文件服务器
         */
        upload: {
            data: null,
            formState: true,
            close: function() {
                var box = document.getElementById('uploadDialog');
                if (box != undefined && box != null) {
                    box.parentNode.removeChild(box);
                }
            },
            dialog: function(callback) {
                RosScript.upload.close();
                var dialog = document.createElement('div');
                dialog.id = 'uploadDialog';
                dialog.className = 'upload-dialog';
                var background = document.createElement('div');
                background.className = 'u-d-background';
                dialog.appendChild(background);
                var wrap = document.createElement('div');
                wrap.className = 'u-d-wrap';
                background.appendChild(wrap);
                var form = document.createElement('form');
                form.innerHTML = "<img id='_u_d_image' src=''>";
                var closeBox = document.createElement('i');
                closeBox.className = 'close';
                var close = document.createElement('i');
                RosScript.bindEvent.add(closeBox, 'onclick', function() {
                    dialog.parentNode.removeChild(dialog);
                });
                var closeBar = document.createElement('div');
                closeBar.className = 'close-bar';
                closeBar.innerHTML = "图片上传";
                closeBox.appendChild(close);
                closeBar.appendChild(closeBox);
                wrap.appendChild(closeBar);
                wrap.appendChild(form);
                var input = document.createElement('input');
                input.type = 'file';
                input.name = 'file';
                var button = document.createElement('button');
                button.type = 'button';
                button.innerHTML = '上传';
                form.appendChild(input);
                form.appendChild(button);
                document.body.appendChild(dialog);
                RosScript.bindEvent.add(input, 'onchange', function() {
                    var file = this.files[0];
                    if ((file.type).indexOf("image/") <= -1) {
                        RosScript.dialog.init({
                            content: '你选择的文件不是图片哦~',
                            time: 5000,
                        });
                        return;
                    }
                    var base64 = new FileReader();
                    base64.readAsDataURL(file);
                    base64.onload = function() {
                        document.getElementById('_u_d_image').src = base64.result;
                    };
                })

                /**
                 * 上传图片
                 */
                RosScript.bindEvent.add(button, 'onclick', function() {
                    var file = input.files[0];
                    if (file == undefined || file == null || file == '') {
                        RosScript.dialog.init({
                            content: '请先选择图片再上传！',
                            time: 5000,
                        });
                        return;
                    }
                    if ((file.type).indexOf("image/") <= -1) {
                        RosScript.dialog.init({
                            content: '你选择的文件不是图片哦~',
                            time: 5000,
                        });
                        return;
                    } else {
                        if (input.value != undefined && input.value != null && input.value != '') {
                            var formdata = new FormData(form);
                            if (RosScript.upload.formState) {
                                RosScript.upload.formState = false;
                                var waitClose = RosScript.dialog.wait('图片上传中···');
                                $.ajax({
                                    type: "post",
                                    url: "https://www.server-file.com/file/server/upload",
                                    data: formdata,
                                    dataType: "json",
                                    contentType: false,
                                    processData: false,
                                    success: function(reply) {
                                        RosScript.upload.formState = true;
                                        waitClose();
                                        if (reply.state == 'success') {
                                            RosScript.upload.data = reply;
                                            if (callback != undefined && callback != null && RosScript.fns.isFunction(callback)) {
                                                callback(reply);
                                            }
                                            RosScript.upload.close();
                                        }
                                        RosScript.dialog.open(reply.message, 5000);
                                    },
                                    error: function() {
                                        RosScript.upload.formState = true;
                                        waitClose();
                                        $C.dialog.open();
                                    }
                                });
                            }
                        } else {
                            RosScript.dialog.open("请先选择图片！", 5000);
                        }
                    }
                });
            },
        },
    };

    /**
     * 在兄弟元素或者兄弟元素的子元素中查找指定className的元素
     * @param e DOM元素对象
     * @param className 元素类名
     * @returns {null|Element}
     */
    RosScript.fns.getSub = function(e, className) {
        // 在子元素中查找
        var son = getSon(e, className);
        if (son != null) {
            return son;
        }
        // 在兄弟元素中查找
        return getSibling(e, className);
    };
    function getSibling(e, n) {
        var brother = e.nextElementSibling; // 获取下一个兄弟元素
        if (brother == undefined || brother == null) {
            return null;
        }
        if (getIsNull(brother, n)) {
            return brother;
        }
        var son = getSon(brother, n);
        if (son != null) {
            return son;
        }
        return getSibling(brother, n);
    }
    function getIsNull(e, n) {
        if (e.className != undefined && e.className != null) {
            if (e.className.indexOf(n) >= 0) {
                return true;
            }
        }
        return false;
    }
    function getSon(e, n) {
        var son = e.firstElementChild;
        if (son == undefined || son == null) {
            return null;
        } else {
            if (getIsNull(son, n)) {
                return son;
            }
        }
        var brother = getSibling(son, n);
        if (brother != null) {
            return brother;
        }
        return getSon(son, n)
    }

    window.$C = RosScript;
})();