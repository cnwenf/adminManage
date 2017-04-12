// /**
//  * Created by WenFe on 2017/4/11.
//  */
$(document).ready(function(){
    var loginbox = $('#loginbox');
    loginbox.css("height", "320px");
    $.ajaxSetup({
        async : false
    });
    var recive = getRequest();
    var nameInput = $("#name");
    var addressInput = $("#address");
    var emailInput = $("#email");
    var phoneInput = $("#phone");
    var introduceInput = $("#introduce");
    var sureBtn = $("#sureBtn");
    sureBtn.click(function () {
        var name = nameInput.val();
        var address = addressInput.val();
        var email = emailInput.val();
        var phone = phoneInput.val();
        var introduce = introduceInput.val();
        if ($.isEmptyObject(name) || $.isEmptyObject(address) || $.isEmptyObject(email) || $.isEmptyObject(phone) || $.isEmptyObject(introduce)) {
            alert("请输入完整信息！");
            return false;
        } else {
            var postData = {
                "name":name,
                "ownerId":recive["name"],
                "address":address,
                "email":email,
                "phone":phone,
                "introduce":introduce
            }
            $.post("/adminManage/company/create",postData,
                function (data) {
                    console.log(data);
                    if($.isEmptyObject(data)) {
                        alert("创建公司失败！");
                        return false;
                    } else {
                        recive["companyId"] = data["id"];
                        jump_args("department-overview.html", recive);
                        return false;
                    }
                });
        }
    });
});

