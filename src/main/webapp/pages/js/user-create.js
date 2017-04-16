/**
 * Created by WenFe on 2017/4/17.
 */
var userInfo = getLocalJson("userInfo");
$(document).ready(function() {
    getDepartmentList();

});
function getDepartmentList() {
    var callBackFun = function (data) {
        if (data) {
            var dpmSelect = $("#dpmSelect");
            for (var index in data) {
                var varItem = new Option(data[index]["name"], data[index]["id"]);
                dpmSelect.append(varItem);
            }
        } else {
        }
    };
    httpSyncPost(api.department.list, {"companyId": userInfo["companyId"]}, callBackFun);
}

function sureBtn() {
    var displayName = $("#displayName").val();
    var cardId = $("#cardId").val();
    var dpm = $("#dpmSelect").val();
    var workNum = $("#workNum").val();
    var phone = $("#phone").val();
    var email = $("#email").val();
    var history = $("#history").val();
    if ($.isEmptyObject(displayName) ||
    $.isEmptyObject(cardId) ||
    $.isEmptyObject(dpm) ||
    $.isEmptyObject(phone) ||
    $.isEmptyObject(email) ||
    $.isEmptyObject(workNum) ||
    $.isEmptyObject(history)) {
        alert("请填入完整信息！");
        return false;
    } else {
        var callBackFun = function (data) {
            if (data){
                alert("添加人员成功！");
            } else {
                alert("添加人员失败！");
            }
        };
        var args = {
            "displayName": displayName,
            "name": cardId,
            "departmentId": dpm,
            "workNum": workNum,
            "companyId": userInfo["companyId"],
            "phone": phone,
            "email": email,
            "history": history
        };
        httpSyncPost(api.user.create, args, callBackFun);
    }
}