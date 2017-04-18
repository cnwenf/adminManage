/**
 * Created by WenFe on 2017/4/18.
 */
var projects = getLocalJson(projectsInfo);
$(document).ready(function() {
    yanshi();
    projects = getLocalJson(projectsInfo);
    init();
});
function init() {
    if ($.isEmptyObject(projects["data"])) {
        return;
    }
    var tableBody = document.getElementById("tableBody");
    var data = projects["data"];
    var column = ["project", "dpm", "owner", "status"];
    var link = ["project-detail.html", "department-detail.html", "user-detail.html"];
    for (var i in data) {
        var row = document.createElement("tr");
        row.id = i;
        for (var j in column) {
            var cell = document.createElement("td");
            cell.id = i + "/" + column[j];
            if ("status" == column[j]){
                cell.setAttribute("class", "taskStatus");
                var span = document.createElement("span");
                span.setAttribute("class", projectStatusClass(data[i][column[j]]));
                span.innerHTML = data[i][column[j]];
                cell.appendChild(span);//
            } else {
                var a = document.createElement("a");
                a.setAttribute("href",link[j]+"?companyId="+data[i]["companyId"]+"&" +"id="+data[i][column[j]]["id"]);
                a.innerHTML = data[i][column[j]]["text"];
                cell.appendChild(a);
            }
            row.appendChild(cell);
        }
        tableBody.appendChild(row);
    }
}

function yanshi() {
    var data = $.isEmptyObject(projects)? {"data":[]} : projects;
    if (data["data"].length >= 3) {
        return;
    }
    var json = {
        "companyId":"abb4237d-1359-49f0-8223-2f4c0317b0ec",
        "project": {
            "id":data["data"].length,
            "text": "GPU虚拟化"
        },
        "dpm": {
            "text": "云计算",
            "id": "48ae9f7e-cdbb-4600-93c3-5d56f2483751"
        },
        "owner": {
            "text": "文峰",
            "id": "46000619940813231X"
        },
        "introduce": "支持kvm虚拟化技术下的GPU共享",
        "startDate": "04/18/2017",
        "persons":[{"text":"文峰", "id":"46000619940813231X"}, {"text":"纵柯", "id":"46000119940813231X"}],
        "endDate": "04/21/2017",
        "status": projectStatus.run
    };
    data["data"].push(json);
    var json2 = {
        "companyId":"abb4237d-1359-49f0-8223-2f4c0317b0ec",
        "project": {
            "text":"磁盘QoS",
            "id":data["data"].length
        },
        "dpm": {
            "text": "ECS",
            "id": "84c04736-6f28-4cf3-bf3d-aa8988563f27"
        },
        "owner": {
            "text": "文峰",
            "id": "46000619940813231X"
        },
        "introduce": "支持数据盘QoS",
        "persons":[{"text":"文峰", "id":"46000619940813231X"}, {"text":"纵柯", "id":"46000119940813231X"}],
        "startDate": "04/18/2017",
        "endDate": "04/18/2017",
        "status": projectStatus.done
    };
    data["data"].push(json2);
    var json3 = {
        "companyId":"abb4237d-1359-49f0-8223-2f4c0317b0ec",
        "project": {
            "text":"自动快照策略",
            "id":data["data"].length
        },
        "dpm": {
            "text": "ECS",
            "id": "84c04736-6f28-4cf3-bf3d-aa8988563f27"
        },
        "owner": {
            "text": "文峰",
            "id": "46000619940813231X"
        },
        "introduce": "支持制定自动快照策略",
        "persons":[{"text":"文峰", "id":"46000619940813231X"}, {"text":"纵柯", "id":"46000119940813231X"}],
        "startDate": "04/18/2017",
        "endDate": "04/18/2017",
        "status": projectStatus.delay
    };
    data["data"].push(json3);
    setLocalJson(projectsInfo, data);
}