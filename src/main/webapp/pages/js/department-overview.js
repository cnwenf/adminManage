var app = angular.module('DepartmentOAPP', []);
app.controller('DepartmentOCtrl', function($scope) {
    $(document).ready(function () {
        init();
    });
    function init() {
        var userInfo = getRequest();
        setSideActive("department_li", "department_overview_li");
        if ($.isEmptyObject(userInfo)) {
            userInfo = getLocalJson("userInfo");
        }
        setLocalJson("userInfo", userInfo);
        $.post("/adminManage/department/structure", {"companyId": userInfo["companyId"]},
            function (data) {
                $('#tree').treeview({
                    data: data,
                    expandIcon: "glyphicon glyphicon-plus",
                    collapseIcon: "glyphicon glyphicon-minus",
                    nodeIcon: "glyphicon glyphicon-user",
                    enableLinks: true,
                    onhoverColor: "#428bca"
                });
            }
        );
    }
});
