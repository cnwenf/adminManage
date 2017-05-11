/**
 * Created by WenFe on 2017/5/11.
 */
var app = angular.module('MaterialDApp', []);
app.controller('MaterialDCtrl', function($scope) {
    $(document).ready(function () {
        init();
    });

    function init() {
        setSideActive("material-li", "material-overview-li");
        $scope.userInfo = getLocalJson("userInfo");
        $scope.initArgs = getRequest();
        getMaterialInfo();
    }

    function getMaterialInfo() {
        var cb = function(data) {
            if (data) {
                $scope.material = data;
            }
        };
        httpSyncPost(api.material.get, {id: $scope.initArgs.id}, cb);
    }

    $scope.update = function() {
        var cb = function(data) {
            if (data) {
                alert("更新成功!");
            } else {
                alert("更新失败!");
            }
        };
        $scope.material.status = $("#status").val();
        httpSyncPost(api.material.update, {id: $scope.initArgs.id, status: $scope.material.status, exInfo: $scope.material.exInfo}, cb);
    }
});