(function() {
    // 获取HTML页面标记
    let BODY = document.getElementsByTagName('body')[0];
    console.log(BODY);
    var HTML_DATA = eval(BODY.getAttribute('data'));


    var form = document.getElementById('CommentEditor');
    if (form) {
        // 文本框
        var editor = document.getElementById('editor_box');
        // 文本框提示
        var editorTips = document.getElementById("editor_box_tips");
        // 新用户数据列表
        var author = document.getElementById('comment_author_info');
        // 用户数据
        var USERDATA = null;
        // 文本框获得焦点时,获取USER数据,没有获取到表示未登录
        $C.bindEvent.add(editor, 'onfocus', function() {
            isLogin();
        });
        // 提交按钮
        var submit = document.getElementById('EditorSubmit');
        $C.bindEvent.add(submit, 'onclick', function() {
            if (!USERDATA) {
                isLogin();
            }
            verifyParam();
        })

        // 判断是否登录
        function isLogin() {
            var USER_CENTER = document.getElementById("USER_CENTER");
            if (!USER_CENTER) {
                // 没有数据时显示文本输入框
                author.style.display = 'block';
            } else {
                USERDATA = eval(USER_CENTER.getAttribute('data'));
            }
        }


        function verifyParam() {
            // 获取表单数据
            var params = $C.fns.getParameter(form);
            if (USERDATA == null) {
                // author.style.display = 'block';
            }
            params['mark'] = HTML_DATA.pageMark.mark;
            params['url'] = window.location.href;
            // console.log(USERDATA);
            // console.log(USERDATA.uid);
            // console.log(params);


            var flag = $C.fns.valueIsNull(params['article']);
            if (!flag) {
                editorTips.style.display = 'block';
                var tips = $C.fns.getSub(editorTips, 'tips');
                $C.fns.setTips(tips, 'tips', '不能发表空评论哦~', false);
                setTimeout(function() {
                    editorTips.style.display = 'none';
                }, 5000);
            } else {
                editorTips.style.display = 'none';
            }
            var nf = false;
            var ef = false;
            var lf = false;
            if (USERDATA == null) {
                var centerNickName = document.getElementById('centerNickName');
                nf = $C.fns.paramIsNull(centerNickName, function(e, f) {
                    var tips = $C.fns.getSub(e, 'tips');
                    if (f) {
                        $C.fns.setTips(tips, 'tips', "", true);
                    } else {
                        $C.fns.setTips(tips, 'tips', "请输入昵称！", false);
                    }
                });

                var centerEmail = document.getElementById('centerEmail');
                ef = $C.fns.paramIsNull(centerEmail, function(e, f) {
                    var tips = $C.fns.getSub(e, 'tips');
                    if (f) {
                        $C.fns.setTips(tips, 'tips', "", true);
                    } else {
                        $C.fns.setTips(tips, 'tips', "请输入邮箱！", false);
                    }
                });

                var centerLink = document.getElementById('centerLink');
                lf = $C.fns.valueIsNull(centerLink.value);
                var linkTips = $C.fns.getSub(centerLink, 'tips');
                if (lf) {
                    if ($C.regex.url.test(centerLink.value)) {
                        $C.fns.setTips(linkTips, 'tips', "", true);
                    } else {
                        lf = false;
                        $C.fns.setTips(linkTips, 'tips', "请输入正确格式的URL！", false);
                    }
                } else {
                    lf = true;
                    $C.fns.setTips(linkTips, 'tips', "", true);
                }
            }

            submitForm(USERDATA, params);
        }


        var formState = true;

        /**
         * 提交表单数据
         * @param userdata 用户数据
         * @param params Form表单参数
         */
        function submitForm(userdata, params) {
            console.log(params);
            if (formState) {
                formState = false;
                $.ajax({
                    type: 'post',
                    url: '/comment/save',
                    data: params,
                    dataType: 'json',
                    contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                    success: function(data) {
                        formState = true;
                        RosDialog.dialog.open(data.content);
                    },
                    error: function() {
                        formState = true;
                        RosDialog.dialog.open();
                    }
                });
            }

        }
    }

})();
