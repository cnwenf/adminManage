/**
 * Created by WenFe on 2017/4/18.
 */
var app = angular.module('userDetailApp', []);
app.controller('userDetailCtl', function($scope) {
    var projects = getLocalJson(projectsInfo);
    var initArgs = getRequest();
    $scope.userInfo = getLocalJson("userInfo");
    $(document).ready(function () {
        httpSyncPost(api.user.detail, {"name": initArgs["id"]}, setInfo);
        setSideActive("user_li", "user_list_li");
    });

    function setInfo(data) {
        $scope.user = data;
        httpSyncPost(api.department.list, {"companyId": $scope.user["companyId"]}, function (data) {
           if ("admin" == $scope.userInfo.role) {
               $scope.departments = data;
               for (var index in $scope.departments) {
                   var p = null;
                   if ($scope.user.departmentId == $scope.departments[index].id) {
                       p = "<option selected='selected' value=" + "'"+ $scope.departments[index].id +"'>" + $scope.departments[index].name + "</option>"
                   } else {
                       p = "<option value=" + "'"+ $scope.departments[index].id +"'>" + $scope.departments[index].name + "</option>"
                   }
                   $("#departmentSelect").append(p);
               }
           }
        });
        //httpSyncPost(api.department.get, {"companyId": userInfo["companyId"], "id": data["departmentId"]}, setDpm);
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
                    a.innerHTML = text + "    ";
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

    $scope.update = function () {
        $scope.user.departmentId = $("#departmentSelect").val();
        $scope.user.role = $("#roleSelect").val();
        var args = {
            name: $scope.user.name,
            displayName: $scope.user.displayName,
            departmentId: $scope.user.departmentId,
            workNum: $scope.user.workNum,
            phone: $scope.user.phone,
            email: $scope.user.email,
            history: $scope.user.history,
            role: $scope.user.role
        };
        httpSyncPost(api.user.update, args, function (data) {
            if (data) {
                alert("更新成功！");
            } else {
                alert("更新失败！");
            }
        });
    };

    $scope.delete = function() {
        httpSyncPost(api.user.delete, {id: $scope.user.name}, function(data) {
            if (data) {
                alert("删除成功！");
            } else {
                alert("删除失败！");
            }
        });
    }
});