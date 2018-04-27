layui.use(['form', 'jquery'], function () {

    var form = layui.form,$ = layui.jquery;
    //提交
    form.on('submit(LAY-user-login-submit)', function (data) {
        // console.log(data);
        // layer.load(1);
        // $.post("login", obj.field, function (data) {
        //     if (data === 'success') {
        //         layer.msg('登录成功');
        //         location.href = "/"
        //     } else {
        //         layer.msg(data, {icon: 5, anim: 6});
        //     }
        // });
        return true;
    });

    form.verify({
        vercode: [/^[0-9a-zA-Z]{5}$/, '只能输入字母和数字'],
        length_6: function (value) {
            if (value.length !== 5) {
                return "验证码为5位";
            }
        }
    });

});