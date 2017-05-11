/**
 * Created by WenFe on 2017/4/18.
 */
var app = angular.module('projectDetailApp', []);
app.controller('projectDetailCtrl', function($scope) {
    $scope.initArgs = getRequest();
    $scope.userInfo = getLocalJson("userInfo");
    $(document).ready(function() {
        setSideActive("project_li", "project_detail_li");
        init();
    });

    function getProjectInfo() {
        var callBackFuc = function (data) {
            if (data) {
                $scope.project = data;
            }
        };
        httpSyncPost(api.project.get, {"companyId": $scope.initArgs.companyId, "id": $scope.initArgs.id}, callBackFuc);
    }

    function getMeetingInfo() {
        var callBackFun = function(data) {
            if (data) {
                $scope.meetings = data;
            }
            $scope.$apply();
        };
        httpSyncPost(api.meeting.listByProject, {projectId: $scope.project.id}, callBackFun);
    }

    function getMaterialInfo() {
        var callBackFun = function(data) {
            if (data) {
                $scope.materials = data;
            }
            $scope.$apply();
        };
        httpSyncPost(api.material.listByProject, {projectId: $scope.project.id}, callBackFun);
    }

    function init() {
        getProjectInfo();
        getMeetingInfo();
        getMaterialInfo();
    }

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

    $scope.transMaterialStatus = function(status) {
        if (status == "run") {
            return "待审批";
        } else if (status == "pass") {
            return "审批通过";
        } else if (status == "end") {
            return "审批拒绝";
        }
    };
});