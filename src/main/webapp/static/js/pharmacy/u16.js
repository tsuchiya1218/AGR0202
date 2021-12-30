function isUpdate(form){
    if(confirm('薬情報を変更しますか？')){
        form.submit();
    }else{
        return;
    }
}