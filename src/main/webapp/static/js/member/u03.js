let regPw = /^[0-9a-zA-Z]{8,}$/;

function isSubmit(form){
    let pw = document.getElementById('pw').value;
    let pw_check = document.getElementById('pw_check').value;
    let isError = false;
    if(pw !== pw_check) isError = true;
    if(!isError){
        if(regPw.test(pw) && regPw.test(pw_check)){
            if(confirm('パスワードを再設定しますか？')) form.submit();
            else return;
        }else{
            alert('パスワードは英数字のみ、８文字以上です。');
        }
    }else{
        alert('パスワードが一致しません。');
    }
}