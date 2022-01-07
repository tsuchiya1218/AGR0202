function isAvailable(form){
    if(confirm('電子処方箋を承認しますか？')) form.submit();
    else return;
}