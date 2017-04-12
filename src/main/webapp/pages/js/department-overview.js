$(document).ready(function(){
    var userInfo = getRequest();
    if ($.isEmptyObject(userInfo)) {
        userInfo = getLocalJson("userInfo");
    }
    setLocalJson("userInfo", userInfo);
    $.post("/adminManage/department/structure",{"companyId":userInfo["companyId"]},
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
