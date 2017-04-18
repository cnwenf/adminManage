/**
 * Created by WenFe on 2017/4/17.
 */
var projects = getLocalJson(projectsInfo);
var userInfo = getLocalJson("userInfo");
var personsSelect = [];
$(document).ready(function() {
    $('#persons').multiSelect({
    afterSelect: function(options){
        personsSelect.push({"text":options.text, "id": options.value});
    },
    afterDeselect: function(options){
        for (var i in personsSelect) {
            var id = personsSelect[i]["id"];
            if (id == options.value) {
                personsSelect.splice(i, 1);
            }
        }
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
                var personsSelect = $("#persons");
                for (var index in data) {
                    var varItem = new Option(data[index]["displayName"], data[index]["name"]);
                    ownerSelect.append(varItem);
                    $("#persons").multiSelect('addOption', { value: data[index]["name"], text: data[index]["displayName"]});
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
        "companyId":userInfo["companyId"],
        "project": {
            "text":name,
            "id":data["data"].length
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
