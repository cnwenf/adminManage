/**
 * Created by WenFe on 2017/4/17.
 */
var app = angular.module('projectCEApp', []);
app.controller('projectCECtrl', function($scope) {
    var projects = getLocalJson(projectsInfo);
    var userInfo = getLocalJson("userInfo");
    var initArgs = getRequest();
    $scope.project = {
        id: "",
        budget:"",
        companyId: userInfo.companyId,
        persons: []
    };
    if ($.isEmptyObject(initArgs)) {
        $scope.isCreate = true;
    } else {
        $scope.isEdit = true;
        $scope.project = getProjectInfo(initArgs.companyId, initArgs.id);
    }
    var personsSelect = [];
    $(document).ready(function () {
        $("#startDate").datepicker({
            inline: true
        });
        $("#endDate").datepicker({
            inline: true
        });

        initPersons();
        initOwner();
        initDepartment();
    });
    function initDepartment() {
        var callBackFuc = function(data) {
            if (data) {
                $scope.departments = data;
            } else {
            }
        }
        httpSyncPost(api.department.list, {"companyId": userInfo["companyId"]}, callBackFuc);
    }
    function initPersons() {
        var personsSelect = $("#persons");
        $.post(api.user.list,{"companyId":userInfo["companyId"]},
            function(data) {
                if (data) {
                    for (var index in data) {
                        personsSelect.multiSelect('addOption', {
                            value: data[index]["name"],
                            text: data[index]["displayName"]
                        });
                    }
                    //初始化已选择人员
                    if ($scope.isEdit) {
                        if (!$.isEmptyObject($scope.project)) {
                            var personsId = [];
                            for (var index in $scope.project.persons) {
                                personsId.push($scope.project.persons[index].id);
                            }
                            personsSelect.multiSelect('select', personsId);
                        }
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
        httpSyncPost(api.user.list, {"companyId": userInfo["companyId"]}, callBackFuc);

    }

    function getProjectInfo(companyId, id) {
        var projects = getLocalJson(projectsInfo);
        var data = projects["data"];
        for (var i in data){
            var e = data[i];
            if (id == e["project"]["id"]) {
                return data[i];
            }
        }
        return null;
    }
    $scope.projectCreate = function() {
        $scope.project.startDate = $("#startDate").val();
        $scope.project.endDate = $("#endDate").val();
        httpSyncPost(api.project.create, {"projectInfo": JSON.stringify($scope.project)}, function(data){
            if (data) {
                alert("suc");
            }
        });
    };
    function sureBtn() {
        var data = $.isEmptyObject(projects) ? {"data": []} : projects;
        var name = $("#name").val();
        var dpmSelectId = $("#dpmSelect").val();
        var dpmSelectText = $("#dpmSelect").find("option:selected").text();
        var ownerSelectId = $("#ownerSelect").val();
        var ownerSelectText = $("#ownerSelect").find("option:selected").text();
        var introduce = $("#introduce").val();
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        var json = {
            "companyId": userInfo["companyId"],
            "project": {
                "text": name,
                "id": data["data"].length
            },
            "dpm": {
                "text": dpmSelectText,
                "id": dpmSelectId
            },
            "owner": {
                "text": ownerSelectText,
                "id": ownerSelectId
            },
            "introduce": introduce,
            "startDate": startDate,
            "endDate": endDate,
            "persons": personsSelect,
            "status": projectStatus.run
        };
        data["data"].push(json);
        setLocalJson(projectsInfo, data);
    }
});