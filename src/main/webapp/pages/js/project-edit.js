/**
 * Created by WenFe on 2017/5/15.
 */
/**
 * Created by WenFe on 2017/4/17.
 */
var app = angular.module('projectEApp', []);
app.controller('projectECtrl', function($scope) {
    $(document).ready(function () {
        init();
    });

    function init() {
        setSideActive("project_li", "project_create_li");
        $scope.userInfo = getLocalJson("userInfo");
        $scope.initArgs = getRequest();
        $scope.project = {
            id: "",
            budget:"",
            companyId: $scope.userInfo.companyId,
            owner: {},
            persons: []
        };
        if ($.isEmptyObject($scope.initArgs)) {
            $scope.isCreate = true;
        } else {
            $scope.isEdit = true;
            getProjectInfo($scope.initArgs.companyId, $scope.initArgs.id);
        }
        $("#endDate").datepicker({
            inline: true
        });

        $scope.$apply();
    }

    function getProjectInfo(companyId, id) {
        var callBackFun = function (data) {
            if (data) {
                $scope.project = data;
            }
            $scope.$apply();
        };
        httpSyncPost(api.project.get, {companyId: companyId, id: id}, callBackFun);
    }

    $scope.projectUpdate = function() {
        //ng-model无法取到该值
        $scope.project.endDate = $("#endDate").val();
        var projectInfo  = JSON.stringify($scope.project);
        httpSyncPost(api.project.update, {"projectInfo": projectInfo}, function(data){
            if (data) {
                alert("suc");
            }
        });
        return false;
    };
});