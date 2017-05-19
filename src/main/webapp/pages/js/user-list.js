/**
 * Created by WenFe on 2017/4/18.
 */
var app = angular.module('userListApp', []);
app.controller('userListCtrl', function($scope, $http, $q) {
    $scope.userInfo = getLocalJson("userInfo");
    $(document).ready(function () {
        setSideActive("user_li", "user_list_li");
        init();
        $scope.$apply();
        $('.data-table').dataTable({
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "sDom": '<""l>t<"F"fp>'
        });

    });
    $scope.selectStatusColor = function (status) {
        return projectStatusColor($scope.transStatus(status));
    };
    $scope.transStatus = function (status) {
        switch (status) {
            case "RUN":
                return "执行中";
            case "DELAY":
                return "延期";
            case "END":
                return "完结";
            case "FORCESTOP":
                return "异常终止";
        }
    };

    function getUserList() {

        var callBackFuc = function(data) {
            if (data) {
                $scope.users = data;
            }
        };
        httpSyncPost(api.user.list, {companyId: $scope.userInfo.companyId}, callBackFuc);
    }

    function init() {
        getUserList();
    }
});