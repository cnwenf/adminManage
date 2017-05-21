/**
 * Created by WenFe on 2017/5/11.
 */
var app = angular.module('MCApp', []);
app.controller('MCCtrl', function($scope) {
    $(document).ready(function() {
        init();
    });
    function init() {
        $("#time").datetimepicker();
        setSideActive("meeting-li", "meeting-create-li");
        $scope.userInfo = getLocalJson("userInfo");
        $scope.getProjects();
    }

    $scope.getProjects = function () {
        var callBackFun = function(data) {
            if (data) {
                $scope.projects = data;
            }
        }
        httpSyncPost(api.project.listByUserId, {companyId: $scope.userInfo.companyId, userId: $scope.userInfo.name}, callBackFun);
    }

    $scope.create = function () {
        var callBackFun = function(data) {
            if (data) {
                alert("发起会议成功！");
            } else {
                alert("发起会议失败！");
            }
        };
        $scope.meeting.time = $("#time").val();
        var args = {
            companyId: $scope.userInfo.companyId,
            projectId: $scope.meeting.project.id,
            name: $scope.meeting.name,
            time: $scope.meeting.time,
            location: $scope.meeting.location,
            ownerId: $scope.userInfo.name
        };
        httpSyncPost(api.meeting.create, args, callBackFun);
    }
});