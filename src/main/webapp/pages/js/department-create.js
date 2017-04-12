/**
 * Created by WenFe on 2017/4/12.
 */
$(document).ready(function() {
});
function sureBtn() {
    var userInfo = getLocalJson("userInfo");
    var name = $('#dpmName').val();
    var parentId = $('#parentDpm').val();
    var ownerId = $('#ownerId').val();
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

