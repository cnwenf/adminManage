/**
 * Created by WenFe on 2017/4/17.
 */
var app = angular.module('projectCEApp', []);
app.controller('projectCECtrl', function($scope) {
    $(document).ready(function () {
        init();
    });

    function init() {
        $scope.userInfo = getLocalJson("userInfo");
        $scope.initArgs = getRequest();
        $scope.project = {
            id: "",
            budget:"",
            companyId: $scope.userInfo.companyId,
            persons: []
        };
        initPersons();
        initOwner();
        initDepartment();
        if ($.isEmptyObject($scope.initArgs)) {
            $scope.isCreate = true;
        } else {
            $scope.isEdit = true;
            getProjectInfo($scope.initArgs.companyId, $scope.initArgs.id);
        }
        $("#startDate").datepicker({
            inline: true
        });
        $("#endDate").datepicker({
            inline: true
        });


    }

    function initDepartment() {
        var callBackFuc = function(data) {
            if (data) {
                $scope.departments = data;
            } else {
            }
        }
        httpSyncPost(api.department.list, {"companyId": $scope.userInfo["companyId"]}, callBackFuc);
    }
    function initPersons() {
        var personsSelect = $("#persons");
        $.post(api.user.list,{"companyId":$scope.userInfo["companyId"]},
            function(data) {
                if (data) {
                    for (var index in data) {
                        personsSelect.multiSelect('addOption', {
                            value: data[index]["name"],
                            text: data[index]["displayName"]
                        });
                    }
                } else {
                }
            });
        //添加监听事件
        personsSelect.multiSelect({
            afterSelect: function (options) {
                for (var index in $scope.users) {
                    if ($scope.users[index].name == options.value) {
                        $scope.project.persons.push($scope.users[index]);
                    }
                }
            },
            afterDeselect: function (options) {
                for (var i in $scope.project.persons) {
                    var id = $scope.project.persons[i].name;
                    if (id == options.value) {
                        $scope.project.persons.splice(i, 1);
                    }
                }
            }
        });
    }

    function initOwner() {
        var callBackFuc = function(data) {
            if (data) {
                $scope.users = data;
            }
        };
        httpSyncPost(api.user.list, {"companyId": $scope.userInfo["companyId"]}, callBackFuc);

    }

    function getProjectInfo(companyId, id) {
        var callBackFun = function (data) {
            if (data) {
                $scope.project = data;
                //初始化已选择人员
                var personsSelect = $("#persons");
                if (!$.isEmptyObject($scope.project)) {
                    var personsId = [];
                    for (var index in $scope.project.persons) {
                        personsId.push($scope.project.persons[index].name);
                    }
                    personsSelect.multiSelect('select', personsId);
                }
            }
        };
        httpSyncPost(api.project.get, {companyId: companyId, id: id}, callBackFun);
    }
    $scope.projectCreate = function() {
        //ng-model无法取到该值
        $scope.project.startDate = $("#startDate").val();
        $scope.project.endDate = $("#endDate").val();
        $scope.project.status = "run";
        var projectInfo  = JSON.stringify($scope.project);
        httpSyncPost(api.project.create, {"projectInfo": projectInfo}, function(data){
            if (data) {
                alert("suc");
            }
        });
    };

    $scope.projectUpdate = function() {
        //ng-model无法取到该值
        $scope.project.startDate = $("#startDate").val();
        $scope.project.endDate = $("#endDate").val();
        var projectInfo  = JSON.stringify($scope.project);
        httpSyncPost(api.project.update, {"projectInfo": projectInfo}, function(data){
            if (data) {
                alert("suc");
            }
        });
    };
});