$.get("/adminManage/department/structure", function (data) {
    $('#tree').treeview({data: data,
        expandIcon: "glyphicon glyphicon-plus",
        collapseIcon:"glyphicon glyphicon-minus",
        nodeIcon:"glyphicon glyphicon-user",
        enableLinks: true,
        onhoverColor:"#428bca"
    });
});