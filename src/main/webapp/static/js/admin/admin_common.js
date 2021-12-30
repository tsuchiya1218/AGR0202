function addSubmit(form){
    let items = document.querySelectorAll('.items input');
    let telNum = document.querySelectorAll('.tel input');
    let brith = document.querySelectorAll('.birth input');
    
    //space tap enterがあればreturn true
    let regSpace = /\s/g;
    let isError = false;
    let telError = false;
    let brithError = false;
    let errorMsg = "以下の項目に空白・スペースがあります。\n";
    let regEmail = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/;
    if(regEmail.test(items[0].value)){
        for(let i of items){
            if(i.value === "" || i.value === null || regSpace.test(i.value)){
                //電話番号のINPUTの場合兄弟のElementがnull
                if(i.previousElementSibling !== null){
                    errorMsg += i.previousElementSibling.textContent+ " ";
                }
                isError = true;
            }
        }
        let count = 0;
        for(let j of telNum){
            if(count > 0){
                if(errorMsg.indexOf('電話番号') == -1){
                    if(!telError && (j.value === null || j.value === "")){
                        errorMsg += "電話番号";
                        isError = true;
                        telError = true;
                    }
                }
            }
            count++;
        }
        count = 0;
        for(let x of brith){
            if(count > 0){
                if(errorMsg.indexOf('生年月日') == -1){
                    if(!brithError && (x.value === null || x.value === "")){
                        errorMsg += "生年月日";
                        isError = true;
                        brithError = true;
                    }
                }
            }
            count++;
        }
        
        if(!isError) form.submit();
        else alert(errorMsg);
    }else{
        alert('正しいメールアドレスを入力してください。');
    }
    
}

function isUpdate(form){
    let items = document.querySelectorAll('.items input');
    let telNum = document.querySelectorAll('.tel input');
    //space tap enterがあればreturn true
    let regSpace = /\s/g;
    let isError = false;
    let telError = false;
    let errorMsg = "以下の項目に空白・スペースがあります。\n";
    let regEmail = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/;
    if(regEmail.test(items[0].value)){
        for(let i of items){
            if(i.value === "" || i.value === null || regSpace.test(i.value)){
                //電話番号のINPUTの場合兄弟のElementがnull
                if(i.previousElementSibling !== null){
                    errorMsg += i.previousElementSibling.textContent+ " ";
                }
                isError = true;
            }
        }
        let count = 0;
        for(let j of telNum){
            if(count > 0){
                if(errorMsg.indexOf('電話番号') == -1){
                    if(!telError && (j.value === null || j.value === "")){
                        errorMsg += "電話番号";
                        isError = true;
                        telError = true;
                    }
                }
            }
            count++;
        }
        
        if(!isError){
            if(confirm('変更しますか？')) form.submit();
            else return;
        }else{
            alert(errorMsg);
        } 
    }else{
        alert('正しいメールアドレスを入力してください。');
    }
    
}

function isSubmit(form){
    if(confirm('登録をしますか？')) form.submit();
    else return;
}

function isDelete(form){
    if(confirm('削除しますか？')) form.submit();
    else return;
}

function maxLengthCheck(object){
    if (object.value.length > object.maxLength){
      object.value = object.value.slice(0, object.maxLength);
    }    
}