/**
 * Created by james on 2017/4/10.
 */
$(function() {
   $("#add").click(function() {
       var username = $("#username").val();
       var password = $("#password").val();
       var email = $("#email").val();
       var phonenumber = $("#phonenumber").val();
       var address = $("#address").val();
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
       if (email === "" || email === null || isEmail(email) === false) {
           message.style.color = "red";
           message.innerHTML = "email格式错误";
           return;
       }
       if (phonenumber === "" || phonenumber === null || isPhoneNo(phonenumber) === false) {
           message.style.color = "red";
           message.innerHTML = "手机号格式错误";
           return;
       }
       if (address === "" || address === null) {
           message.style.color = "red";
           message.innerHTML = "地址不能为空";
           return;
       }
       $.ajax({
           type: "post",
           url: "/Enterprise/add",
           data: $("#enterpriseAddForm").serialize(),
           dataType: "json",
           success: function(data) {
               var temp = eval(data);
               if (temp.status === "success") {
                   message.style.color = "green";
                   message.innerHTML = "新建成功";
                   window.location.href = "/login/";
               } else if (temp.status === "fail") {
                   message.style.color = "red";
                   message.innerHTML = "用户名重复或权限不足";
               }
           },
           error: function (e) {
               alert(e);
               console.log(e);
           }
       })
   })
});

