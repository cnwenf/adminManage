/**
 * Created by WenFe on 2017/5/11.
 */
var app = angular.module('MDApp', []);
app.controller('MDCtrl', function($scope) {
    $(document).ready(function () {
        init();
    });

    function init() {
        setSideActive("meeting-li", "meeting-overview-li");
        $scope.initArgs = getRequest();
        getMeetingInfo();
    }

    function getMeetingInfo() {
        var callBackFun = function(data) {
            if (data) {
                $scope.meeting = data;
            }
        };
        httpSyncPost(api.meeting.get, {id: $scope.initArgs.id}, callBackFun);
    }

    $scope.update = function () {
        var callBackFun = function (data) {
            if (data) {
                alert("更新成功！");
            } else {
                alert("更新失败！");
            }
        };
        httpSyncPost(api.meeting.update, {id : $scope.meeting.id, log: $scope.meeting.log}, callBackFun);
    }
});