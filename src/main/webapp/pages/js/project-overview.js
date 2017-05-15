/**
 * Created by WenFe on 2017/4/18.
 */
var app = angular.module('projectOverviewApp', []);
app.controller('projectOverviewCtrl', function($scope, $http) {
    $scope.userInfo = getLocalJson("userInfo");
    $(document).ready(function () {
        setSideActive("project_li", "project_overview_li");
        init();
        $scope.$apply();

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

    function getProjectList() {
        // $http({
        //     method: 'POST',
        //     url: api.project.list,
        //     data: $.param({companyId: $scope.userInfo.companyId}),
        //     headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8' }
        // }).success(function(data) {
        //     if (data) {
        //         $scope.projects = data;
        //     }
        //     });
        var callBackFuc = function(data) {
            if (data) {
                $scope.projects = data;
            }
        };
        httpSyncPost(api.project.list, {companyId: $scope.userInfo.companyId}, callBackFuc);
    }

    function init() {
        getProjectList();
    }
});