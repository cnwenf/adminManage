/**
 * Created by WenFe on 2017/3/16.
 */
$(document).ready(function() {
    var postArgs = getRequest();
    $.post(api.department.get,{"companyId":postArgs["companyId"], "id":postArgs["id"]},
        function (data){
            console.log(data);
        }
    );
});