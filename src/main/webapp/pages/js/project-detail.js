/**
 * Created by WenFe on 2017/4/18.
 */
var app = angular.module('projectDetailApp', []);
app.controller('projectDetailCtrl', function($scope) {
    var projects = getLocalJson(projectsInfo);
    var initArgs = getRequest();
    var userInfo = getLocalJson("userInfo");
    $(document).ready(function() {
        setSideActive("project_li", "project_detail_li");
        init();
    });
    function init() {
        var project = null;
        var projectId = initArgs["id"];
        var data = projects["data"];
        for (var i in data){
            var e = data[i];
            if (projectId == e["project"]["id"]) {
                project = e;
                $scope.project = e;
                $scope.editInfo = JSON.parse(JSON.stringify(e));
                break;
            }
        }
        if (!$.isEmptyObject(project)) {
            $("#status").css("color", projectStatusColor(project["status"]));
            initEditPersons();
            initUsers();
        }
    }

    function initUsers() {
        //初始化部门主管下拉框
        httpSyncPost(api.user.list, {"companyId":userInfo["companyId"]},function (data) {
            if(data) {
                for (var index in data) {
                    data[index]["text"] = data[index].displayName;
                    data[index]["id"] = data[index].name;
                    if ($scope.editInfo.owner.id == data[index]["id"]) {
                        $scope.editInfo.owner = data[index];
                    }
                }
                $scope.users = data;
            } else {
            }
        });
    }

    function initEditPersons() {
        $.post(api.user.list,{"companyId":userInfo["companyId"]},
            function (data) {
                if(data) {
                    var personsSelect = $("#editPersons");
                    for (var index in data) {
                        data[index]["id"] = data[index].name;
                        data[index]["text"] = data[index].displayName;
                        personsSelect.multiSelect('addOption', { value: data[index]["name"], text: data[index]["displayName"]});
                    }
                    //设置已选择的人员
                    var personsId = [];
                    for (var index in $scope.editInfo.persons) {
                        personsId.push($scope.editInfo.persons[index].id);
                    }
                    personsSelect.multiSelect('select',personsId);
                } else {
                }
            });
        $('#editPersons').multiSelect({
            afterSelect: function(options){
                $scope.editInfo.persons.push({"text":options.text, "id": options.value});
            },
            afterDeselect: function(options){
                for (var i in $scope.editInfo.persons) {
                    var id = $scope.editInfo.persons[i]["id"];
                    if (id == options.value) {
                        $scope.editInfo.persons.splice(i, 1);
                    }
                }
            }
        });
    }
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
});