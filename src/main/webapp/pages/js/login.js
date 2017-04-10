/**
 * Unicorn Admin Template
 * Diablo9983 -> diablo9983@gmail.com
**/
$(document).ready(function(){

	var login = $('#loginform');
	var recover = $('#recoverform');
    var loginbox = $('#loginbox');
	var speed = 400;
    $('#registerBtn').click(function(){

    });
    $('#loginBtn').click(function(){
        var name = $('#loginName').val();
        var password = $('#loginPass').val();
        if (!isEmptyObject(name) && !isEmptyObject(password)){
            $.post("/adminManage/user/login",{"name":name, "password":password},
                function (data) {
                    alert(data)
                });
        } else {
            alert("用户名或密码不能为空！")
        }
    });

	$('#to-recover').click(function(){
		login.fadeTo(speed,0.01).css('z-index','100');
		recover.fadeTo(speed,1).css('z-index','200');
        loginbox.css("height", "230");
	});

	$('#to-login').click(function(){
		recover.fadeTo(speed,0.01).css('z-index','100');
		login.fadeTo(speed,1).css('z-index','200');
        loginbox.css("height", "210");
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

    function login() {

    }
});