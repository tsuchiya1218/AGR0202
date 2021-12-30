function isSubmit(form){
    if(confirm('薬剤情報提供書を登録しますか？')) form.submit();
    else return;
}