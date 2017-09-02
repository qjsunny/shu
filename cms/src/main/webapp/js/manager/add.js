/**
 * Created by james on 2017/4/9.
 */
$(function() {
    $("#add").click(function() {
        var username = $("#username").val();
        var password = $("#password").val();
        var level = $("#level").val();
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
        if (level === "" || level == null || isLevel(level) === false) {
            message.style.color = "red";
            message.innerHTML = "等级格式错误";
            return;
        }
        $.ajax({
            type: "post",
            url: "/Manager/add",
            data: $("#managerAddForm").serialize(),
            dataType: "json",
            success: function(data) {
                var temp = eval(data);
                if (temp.status === "success") {
                    message.style.color = "green";
                    message.innerHTML = "新建成功";
                    window.location.href = "/Manager/";
                } else if (temp.status === "fail") {
                    message.style.color = "red";
                    message.innerHTML = "用户名有误或权限不足";
                }
            },
            error: function (e) {
              alert(e);
              console.log(e);
            }
        })
    })

    $("#return").click(function() {
        window.location.href = "/Manager/";
    })
});