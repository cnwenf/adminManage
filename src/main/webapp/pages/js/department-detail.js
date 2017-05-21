/**
 * Created by WenFe on 2017/3/16.
 */
var app = angular.module('departmentDApp', []);
app.controller('departmentDCtrl', function($scope) {
    var initArgs = getRequest();
    $scope.userInfo = getLocalJson("userInfo");
    $scope.existChild = false;
    $(document).ready(function () {
        setSideActive("department_li", "department_overview_li");
        httpSyncPost(api.department.get, {"companyId": initArgs["companyId"], "id": initArgs["id"]}, readyCallback);
    });
    function readyCallback(data) {
        $scope.department = data;
        httpSyncPost(api.department.get, {"companyId": initArgs["companyId"], "id": data["parentId"]}, setParentName);
        httpSyncPost("/adminManage/user/listByDepartment", {"companyId": initArgs["companyId"], "departmentId": initArgs["id"]}, getPersons);
        httpAsyncPost(api.department.existChild, {"companyId": initArgs["companyId"], "id": data["id"]}, function(data) {
            $scope.existChild = data;
        });
        $("#dpmName").text(data["name"]);
        $("#introduce").text(string2ChangeLine(data["description"]));
    }

    function setParentName(data) {
        $("#parentId").text($.isEmptyObject(data["name"]) ? "--" : data["name"]);
    }

    function getPersons(data) {
        if (data) {
            $scope.persons = data;
            $scope.$apply();
        }
    }
    $scope.transRole = function(role) {
        switch (role){
            case "admin":
                return "企业管理员";
            case "departmentadmin":
                return "部长";
            case  "materialadmin":
                return "物资管理员";
            case  "normal":
                return "部员";
        }
    }
    $scope.delete = function() {
        var cb = function(data) {
            if (data) {
                alert("删除部门成功！");
            } else {
                alert("删除部门失败！");
            }
        };
        httpSyncPost("/adminManage/department/delete", {companyId: $scope.userInfo.companyId, id: initArgs.id}, cb);
    }
});