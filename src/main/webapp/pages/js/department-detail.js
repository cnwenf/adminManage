/**
 * Created by WenFe on 2017/3/16.
 */
var initArgs = getRequest();
$(document).ready(function() {

    httpPost(api.department.get, {"companyId":initArgs["companyId"], "id":initArgs["id"]},readyCallback);
});
function readyCallback(data) {
    httpPost(api.department.get, {"companyId":initArgs["companyId"], "id":data["parentId"]}, setParentName);
    httpPost(api.user.get, {"name":data["ownerId"]}, setOwnerName);
    $("#dpmName").text(data["name"]);
    $("#introduce").text(data["description"]);
}
function setParentName(data) {
    $("#parentId").text(data["name"]);
}
function setOwnerName(data) {
    $("#ownerId").text(data["displayName"]);
}