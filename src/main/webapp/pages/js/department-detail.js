/**
 * Created by WenFe on 2017/3/16.
 */
var initArgs = getRequest();
$(document).ready(function() {

    httpSyncPost(api.department.get, {"companyId":initArgs["companyId"], "id":initArgs["id"]},readyCallback);
});
function readyCallback(data) {
    httpSyncPost(api.department.get, {"companyId":initArgs["companyId"], "id":data["parentId"]}, setParentName);
    //httpSyncPost(api.user.detail, {"name":data["ownerId"]}, setOwnerName);
    $("#dpmName").text(data["name"]);
    $("#introduce").text(string2ChangeLine(data["description"]));
}
function setParentName(data) {
    $("#parentId").text($.isEmptyObject(data["name"])?"--":data["name"]);
}
// function setOwnerName(data) {
//     $("#ownerId").text($.isEmptyObject(data["displayName"])?"--":data["displayName"]);
//     var jump_str = "user-detail.html?" + "companyId=" + initArgs["companyId"] +"&id=" + data["name"];
//     document.getElementById("ownerId").setAttribute("href", jump_str);
// }