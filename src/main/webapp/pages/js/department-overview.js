$(document).ready(function(){
    var recive = getRequest();
    var cache = {};
    $.data(cache, "userInfo", recive);
    $.post("/adminManage/department/structure",{"companyId":recive["companyId"]},
        function (data) {
            $('#tree').treeview({data: data,
                expandIcon: "glyphicon glyphicon-plus",
                collapseIcon:"glyphicon glyphicon-minus",
                nodeIcon:"glyphicon glyphicon-user",
                enableLinks: true,
                onhoverColor:"#428bca"
            });
        }
    );
});
