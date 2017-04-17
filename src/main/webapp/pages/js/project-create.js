/**
 * Created by WenFe on 2017/4/17.
 */
var projects = getLocalJson(projectsInfo);
var userInfo = getLocalJson("userInfo");
$(document).ready(function() {
    $("#startDate").datepicker({
        inline: true
    });
    $("#endDate").datepicker({
        inline: true
    });
    $.post(api.department.list,{"companyId":userInfo["companyId"]},
        function (data) {
            if(data) {
                var dpmSelect = $("#dpmSelect");
                for (var index in data) {
                    var varItem = new Option(data[index]["name"], data[index]["id"]);
                    dpmSelect.append(varItem);
                }
            } else {
            }
        });
    $.post(api.user.list,{"companyId":userInfo["companyId"]},
        function (data) {
            if(data) {
                var ownerSelect = $("#ownerSelect");
                for (var index in data) {
                    var varItem = new Option(data[index]["displayName"], data[index]["name"]);
                    ownerSelect.append(varItem);
                }
            } else {
            }
        });
});
function sureBtn() {
    var data = $.isEmptyObject(projects)? {"data":[]} : projects;
    var name = $("#name").val();
    var dpmSelect = $("#dpmSelect").val();
    var ownerSelect = $("#ownerSelect").val();
    var introduce = $("#introduce").val();
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();
    var json = {
        "name": name,
        "dpmSelect": dpmSelect,
        "ownerSelect": ownerSelect,
        "introduce": introduce,
        "startDate": startDate,
        "endDate": endDate
    };
    data["data"].append(json);
    setLocalJson(projects, data);
}
