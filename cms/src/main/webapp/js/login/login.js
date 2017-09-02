/**
 * Created by james on 2017/4/8.
 */
$(function() {
    $("#kaptchaImage").click(function() {
        $(this).attr('src', '/login/getCaptcha?' + new Date());
    });
    $("#enterpriseAdd").click(function() {
        window.location.href = "/Enterprise/addIndex";
    });
    $("#login").click(function() {
        var username = $("#username").val();
        var password = $("#password").val();
        var message = document.getElementById("message");
        if (username === "" || username === null || isUsername(username) === false) {
            message.style.color = "red";
            message.innerHTML = "用户名格式错误";
            return;
        }
        if (password === "" || password === null || isPassword(password) === false) {
            message.style.color = "red";
            message.innerHTML = "密码格式错误";
            return;
        }
        var verifyCodeValue = $("#verifyCode").val();
        if (verifyCodeValue.replace(/\s/g, "") == "") {
            message.style.color = "red";
            message.innerHTML = "验证码不能为空";
            return;
        }
        var loginType = $("[name='loginType']").filter(":checked");
        if (loginType.attr("value")==="管理员")
            var url = "/login/Manager";
        if (loginType.attr("value")==="企业")
            var url = "/login/Enterprise";
        // if (loginType.attr("value")==="主播")
        //     var url = "#";

        $.ajax({
            type: "post",
            url: "/login/verifyCaptcha",
            data: {
                "verifyCode":verifyCodeValue
            },
            dataType: "json",
            success: function(data) {
                temp = eval(data);
                if(temp.status === "success") {
                    $.ajax({
                        type: "post",
                        url: url,
                        data: $("#loginForm").serialize(),
                        dataType: "json",
                        success: function(data) {
                            //temp = JSON.parse(data);
                            var temp = eval(data);
                            if(temp.status === "success") {
                                message.style.color = "green";
                                message.innerHTML = "登录成功";
                                if (loginType.attr("value")==="管理员")
                                    window.location.href = "/Manager/";
                                if (loginType.attr("value")==="企业")
                                    window.location.href = "/Enterprise/";
                                if (loginType.attr("value")==="主播")
                                    window.location.href = "#";
                                // <jsp:forward page="/index/"/>
                            } else if (temp.status === "fail") {
                                message.style.color = "red";
                                message.innerHTML = "用户名或者密码错误";
                                $("#kaptchaImage").attr('src', '/login/getCaptcha?' + new Date());
                            }
                        },
                        error: function(e) {
                            alert(e);
                            console.log(e);
                        }
                    });
                } else if(temp.status === "fail") {
                    message.style.color = "red";
                    message.innerHTML = "验证码错误";
                    $("#kaptchaImage").attr('src', '/login/getCaptcha?' + new Date());
                }
            },
            error: function(e){
                alert(e);
                console.log(e);
            }
        });
    });
});