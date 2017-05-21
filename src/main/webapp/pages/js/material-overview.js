/**
 * Created by WenFe on 2017/5/11.
 */
var app = angular.module('MOApp', []);
app.controller('MOCtrl', function($scope) {
    $(document).ready(function () {
        init();
    });
    $scope.transStatus = function(status) {
        if (status == "run") {
            return "待审批";
        } else if (status == "pass") {
            return "审批通过";
        } else if (status == "end") {
            return "审批拒绝";
        }
    };
    function init() {
        $scope.userInfo = getLocalJson("userInfo");
        setSideActive("material-li", "material-overview-li");
        getSpMaterial();
        getExMaterial();
        $('.data-table').dataTable({
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "sDom": '<""l>t<"F"fp>'
        });
    }

    function getSpMaterial() {
        var callBackFun = function (data) {
            if (data) {
                $scope.spMaterial = data;
            }
            $scope.$apply();
        };
        httpSyncPost(api.material.listBySpUser, {spUserId: $scope.userInfo.name}, callBackFun);
    }

    function getExMaterial() {
        var callBackFun = function (data) {
            if (data) {
                $scope.exMaterial = data;
            }
            $scope.$apply();
        };
        httpSyncPost(api.material.listByExUser, {exUserId: $scope.userInfo.name}, callBackFun);
    }

    $scope.delete = function(id) {
        var cb = function(data) {
            if (data) {
                alert("删除成功！");
                getSpMaterial();
                getExMaterial();
            } else {
                alert("删除失败");
            }
        };
        httpSyncPost(api.material.delete, {id: id}, cb);
    };

    $scope.pass = function(id) {
        var cb = function(data) {
            if (data) {
                alert("审批成功！");
                getSpMaterial();
                getExMaterial();
            } else {
                alert("审批失败");
            }
        };
        httpSyncPost(api.material.update, {id: id, status: "pass", exInfo: ""}, cb);
    };

    $scope.stop = function(id) {
        var cb = function(data) {
            if (data) {
                alert("审批成功！");
                getSpMaterial();
                getExMaterial();
            } else {
                alert("审批失败");
            }
        };
        httpSyncPost(api.material.update, {id: id, status: "end", exInfo: ""}, cb);
    }
});