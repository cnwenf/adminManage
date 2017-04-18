/**
 * Created by WenFe on 2017/4/18.
 */
var projects = getLocalJson(projectsInfo);
var initArgs = getRequest();
var userInfo = getLocalJson("userInfo");
$(document).ready(function() {
    httpSyncPost(api.user.detail, {"name": initArgs["id"]}, setInfo);
    setProjects();
});

function setInfo(data) {
    $("#displayName").text(data["displayName"]);
    $("#phone").text(data["phone"]);
    $("#email").text(data["email"]);
    $("#introduce").text(data["history"]);
    httpSyncPost(api.department.get, {"companyId": userInfo["companyId"], "id": data["departmentId"]}, setDpm);
}
function setProjects() {
    var span = document.getElementById("projects");
    var projects_data = projects["data"];
    for (var p in projects_data) {
        var userId = initArgs["id"];
        var projectsPersons = projects_data[p]["persons"];
        projectsPersons.push(projects_data[p]["owner"]);
        for (var i in projectsPersons) {
            if (userId == projectsPersons[i]["id"]) {
                var text = projects_data[p]["project"]["text"];
                var id = projects_data[p]["project"]["id"];
                var companyId = projects_data[p]["companyId"];
                var jump_str = "project-detail.html?" + "companyId=" + companyId + "&id=" + id;
                var a = document.createElement("a");
                a.setAttribute("href", jump_str);
                a.innerHTML = text+"    ";
                span.appendChild(a);
                break;
            }
        }

    }
}
function setDpm(data) {
    $("#dpm").text(data["name"]);
    var jump_str = "department-detail.html?" + "companyId=" + data["companyId"] + "&id=" + data["id"];
    document.getElementById("dpm").setAttribute("href", jump_str);
}