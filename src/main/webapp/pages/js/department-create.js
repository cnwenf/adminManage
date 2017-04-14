/**
 * Created by WenFe on 2017/4/12.
 */
$(document).ready(function() {
    $.ajaxSetup({
        async : false
    });
    var userInfo = getLocalJson("userInfo");
    $.post(api.department.list,{"companyId":userInfo["companyId"]},
        function (data) {
            if(data) {
                var dpmSelect = $("#dpmSelect");
                var varItem = new Option("[空]", "");
                dpmSelect.append(varItem);
                for (var index in data) {
                    var varItem = new Option(data[index]["name"], data[index]["id"]);
                    dpmSelect.append(varItem);
                }
            } else {
            }
    });
    $.post(api.user.list,{"companyId":userInfo["companyId"]},
        function (data) {
            if(data) {
                var ownerSelect = $("#ownerSelect");
                var varItem = new Option("[空]", "");
                ownerSelect.append(varItem);
                for (var index in data) {
                    var varItem = new Option(data[index]["displayName"], data[index]["name"]);
                    ownerSelect.append(varItem);
                }
            } else {
            }
        });
});
function sureBtn() {
    var userInfo = getLocalJson("userInfo");
    var name = $('#dpmName').val();
    var parentId = $('#dpmSelect').val();
    var ownerId = $('#ownerSelect').val();
    var introduce = $('#introduce').val();
    if ($.isEmptyObject(name)) {
        alert("部门名称不能为空！");
        return false;
    } else {
        $.post("/adminManage/department/create",{"name":name, "description":introduce, "parentId":parentId, "ownerId":ownerId, "companyId":userInfo["companyId"]},
            function (data) {
                if(data) {
                    alert("创建部门成功！");
                    return false;
                } else {
                    alert("创建部门失败！");
                    return false;
                }
            });
    }
}

