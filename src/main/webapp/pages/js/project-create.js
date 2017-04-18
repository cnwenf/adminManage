/**
 * Created by WenFe on 2017/4/17.
 */
var projects = getLocalJson(projectsInfo);
var userInfo = getLocalJson("userInfo");
$(document).ready(function() {
    $('#persons').multiSelect({
    afterSelect: function(values){
        alert("Select value: "+values);
    },
    afterDeselect: function(values){
        alert("Deselect value: "+values);
    }
    });
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
    var dpmSelectId = $("#dpmSelect").val();
    var dpmSelectText = $("#dpmSelect").find("option:selected").text();
    var ownerSelectId = $("#ownerSelect").val();
    var ownerSelectText = $("#ownerSelect").find("option:selected").text();
    var introduce = $("#introduce").val();
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();
    var json = {
        "name": name,
        "dpmSelectId": dpmSelectId,
        "dpmSelectText": dpmSelectText,
        "ownerSelectId": ownerSelectId,
        "ownerSelectText": ownerSelectText,
        "introduce": introduce,
        "startDate": startDate,
        "endDate": endDate,
        "status": projectStatus.run
    };
    data["data"].push(json);
    setLocalJson(projectsInfo, data);
}
