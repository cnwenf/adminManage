/**
 * Created by WenFe on 2017/5/17.
 */
/**
 * Created by WenFe on 2017/4/12.
 */
var app = angular.module('DepartmentEditApp', []);
app.controller('DepartmentEditCtrl', function($scope) {
    $(document).ready(function () {
        $scope.initArgs = getRequest();
        $scope.userInfo = getLocalJson("userInfo");
        init();
    });
    function init() {
        httpSyncPost(api.department.get, {"companyId": $scope.userInfo["companyId"], "id": $scope.initArgs.id},
        function(data){
            $scope.department = data;
        });
        setSideActive("department_li", "department_overview_li");
        $.ajaxSetup({
            async: false
        });
        $.post(api.department.list, {"companyId": $scope.userInfo["companyId"]},
            function (data) {
                if (data) {
                    var dpmSelect = $("#dpmSelect");
                    var varItem = new Option("[空]", "");
                    dpmSelect.append(varItem);
                    for (var index in data) {
                        var varItem = new Option(data[index]["name"], data[index]["id"]);
                        if (data[index]["id"] == $scope.department.parentId) {
                            varItem.setAttribute("selected", "selected");
                        }
                        dpmSelect.append(varItem);
                    }
                } else {
                }
            });
    }
    $scope.sureBtn = function() {
        var userInfo = getLocalJson("userInfo");
        var name = $('#dpmName').val();
        var parentId = $('#dpmSelect').val();
        //废弃部长选择
        var ownerId = "0";//$('#ownerSelect').val();
        var introduce = $('#introduce').val();
        if ($.isEmptyObject(name)) {
            alert("部门名称不能为空！");
            return false;
        } else {
            $.post("/adminManage/department/update", {
                    "id": $scope.initArgs.id,
                    "name": name,
                    "description": introduce,
                    "parentId": parentId,
                    "ownerId": ownerId,
                    "companyId": userInfo["companyId"]
                },
                function (data) {
                    if (data) {
                        alert("更新部门信息成功！");
                        return false;
                    } else {
                        alert("更新部门信息失败！");
                        return false;
                    }
                });
        }
    }
});
