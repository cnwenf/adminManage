/**
 * Created by WenFe on 2017/5/11.
 */
var app = angular.module('MOApp', []);
app.controller('MOCtrl', function($scope) {
    $(document).ready(function () {
        init();
    });

    function init() {
        setSideActive("meeting-li", "meeting-overview-li");
        $scope.userInfo = getLocalJson("userInfo");
        getMeetings();
    }

    function getMeetings() {
        var callBackFun = function(data) {
            if (data) {
                $scope.meetings = data;
            }
        }
        httpSyncPost(api.meeting.listByUser, {userId: $scope.userInfo.name}, callBackFun);
    }
});