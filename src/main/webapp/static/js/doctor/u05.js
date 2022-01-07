function isSubmit(form){
    let pw = document.getElementById('pw').value;
    if(confirm('退会しますか？')) form.submit();
    else return;
}