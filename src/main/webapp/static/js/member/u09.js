$(document).ready(function(){
    $('#fileDown').click(function(){
 
        var filename = $('#fileName').val();
        window.location.assign('${ctx}/infoCenter/download.do?fileName='+encodeURI(fileName));
    });
});
