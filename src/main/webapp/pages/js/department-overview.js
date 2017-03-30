$.post("/adminManage/department/structure",{"companyId":"2a14906e-b01a-4d9a-93e4-ac3740bbe33b"},
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