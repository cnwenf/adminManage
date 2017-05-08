function jump_args(url, data) {
    var args = url + "?";
    for (var key in data) {
        args = args + key + "=" + data[key] + "&";
    }
    window.location.replace(args);
}

function getRequest() {
    var url = window.location.search;
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

function setLocalJson(key, jsonData) {
    var data = JSON.stringify(jsonData);
    window.localStorage.setItem(key, data);
}

function getLocalJson(key) {
    var data = window.localStorage.getItem(key);
    return JSON.parse(data);
}

var api = {
    department : {
        structure: "/adminManage/department/structure",
        get: "/adminManage/department/get",
        list: "/adminManage/department/list"
    },
    user: {
        list: "/adminManage/user/list",
        detail:"/adminManage/user/detail",
        create:"/adminManage/user/create"
    },
    project: {
        create: "/adminManage/project/create",
        get: "/adminManage/project/get"
    }
}

function httpSyncPost(url, args, callback) {
    $.ajaxSetup({
        async : false
    });
    $.post(url, args, callback);
}

function httpAsyncPost(url, args, callback) {
    $.post(url, args, callback);
}
function string2ChangeLine(s) {
    //textare换行失败
    s =s.replace("↵", "/n");
    return s;
}
var projectStatus = {
    run: "执行中",
    delay: "延期",
    done: "已完成"
};
function projectStatusClass(state) {
    switch (state) {
        case projectStatus.run:
            return "in-progress";
        case projectStatus.delay:
            return "pending";
        case projectStatus.done:
            return "done";
    }
}
function projectStatusColor(state) {
    switch (state) {
        case projectStatus.run:
            return "#64909E";
        case projectStatus.delay:
            return "#AC6363";
        case projectStatus.done:
            return "#75B468";
    }

}

function setSideActive(id1, id2) {
    document.getElementById(id1).setAttribute("class", "submenu active open");
    document.getElementById(id2).setAttribute("class", "active");
}

function setInputReadOnly(id) {
    $("#" + id).focus(function () {
        $("#" + id).attr("style", "border:1;");
        $("#" + id).removeAttr("readonly");
    });
    $("#" + id).blur(function () {
        $("#" + id).attr("style", "border:0;");
        $("#" + id).attr("readonly", "true");
    });
}
var projectsInfo = "projects";
