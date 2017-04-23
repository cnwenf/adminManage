/**
 * Unicorn Admin Template
 * Diablo9983 -> diablo9983@gmail.com
**/
$(document).ready(function(){
    $("body").keyup(function () {
        if (event.which == 13){
            $("#loginBtn").trigger("click");
        }
    });
    $.ajaxSetup({
        async : false
    });
    var login = $('#loginform');
	var recover = $('#registerform');
    var loginbox = $('#loginbox');
	var speed = 400;
    $('#registerBtn').click(function(){
        var IDCard = $('#idNum').val();
        var name = $('#regName').val();
        var password = $('#regPass').val();
        var repPassword = $('#repPass').val();
        if ($.isEmptyObject(name) || $.isEmptyObject(IDCard) || $.isEmptyObject(password)|| $.isEmptyObject(repPassword)) {
            alert("请完整填入信息！");
            return false;
        } else {
            $.post("/adminManage/user/register",{"IDCard":IDCard, "displayName":name, "password":password},
                function (data) {
                    if (data) {
                        alert("注册成功！");
                        return false;
                    } else {
                        alert("注册失败！");
                        return false;
                    }
                });
        }


    });
    $('#loginBtn').click(function(){
        var name = $('#loginName').val();
        var password = $('#loginPass').val();
        if (!$.isEmptyObject(name) && !$.isEmptyObject(password)){
            $.post("/adminManage/user/login",{"name":name, "password":password},
                function (data) {
                    if($.isEmptyObject(data)) {
                        alert("用户名或密码错误！");
                        return false;
                    } else {
                        if ($.isEmptyObject(data["companyId"])){
                            jump_args("create-company.html", data);
                            return false;
                        } else {
                            jump_args("department-overview.html", data);
                            return false;
                        }
                    }

                });
        } else {
            alert("用户名或密码不能为空！");
            return false;
        }
    });

	$('#to-register').click(function(){
		login.fadeTo(speed,0.01).css('z-index','100');
		recover.fadeTo(speed,1).css('z-index','200');
        loginbox.css("height", "310px")
	});

	$('#to-login').click(function(){
		recover.fadeTo(speed,0.01).css('z-index','100');
		login.fadeTo(speed,1).css('z-index','200');
        loginbox.css("height", "210px")
	});
    
    if($.browser.msie == true && $.browser.version.slice(0,3) < 10) {
        $('input[placeholder]').each(function(){ 
       
        var input = $(this);       
       
        $(input).val(input.attr('placeholder'));
               
        $(input).focus(function(){
             if (input.val() == input.attr('placeholder')) {
                 input.val('');
             }
        });
       
        $(input).blur(function(){
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.val(input.attr('placeholder'));
            }
        });
    });
    }
});