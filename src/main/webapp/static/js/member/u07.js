function addSubmit(form){
    let items = document.querySelectorAll('.items_req input');
    
	if(checkBirth()) {
		alert('正しい生年月日を入力してください。\n例 2010 02 09');
		return;
	}
	if(checkI_expiry_date()){
		alert('正しい保険証有効期限を入力してください。\n例 2025 01 25');
		return;
	}
    //space tap enterがあればreturn true
    let regSpace = /\s/g;
    let isError = false;
    let errorMsg = "以下の項目に空白・スペースがあります。\n";
    let regNum = /^([0-9]{0,7})$/;
    
    for(let i of items){
        if(i.value === "" || i.value === null || regSpace.test(i.value)){
            //電話番号のINPUTの場合兄弟のElementがnull
            if(i.previousElementSibling !== null){
                errorMsg += i.previousElementSibling.textContent+ " ";
            }else{
				errorMsg += "生年月日を入力してください。"
			}
            isError = true;
        }
    }
    if(!regNum.test(items[6].value)){
        isError = true;
        alert('子供医療証の番号は 7桁以下です。');
		return;
    }
    if(!isError) form.submit();
    else alert(errorMsg);
}
function isUpdate(form){
    let items = document.querySelectorAll('.items_req input');
    
    //space tap enterがあればreturn true
    let regSpace = /\s/g;
    let isError = false;
    let errorMsg = "以下の項目に空白・スペースがあります。\n";
    let regNum = /^([0-9]{0,7})$/;
    
    for(let i of items){
        if(i.value === "" || i.value === null || regSpace.test(i.value)){
            //電話番号のINPUTの場合兄弟のElementがnull
            if(i.previousElementSibling !== null){
                errorMsg += i.previousElementSibling.textContent+ " ";
            }
            isError = true;
        }
    }
    if(!regNum.test(items[0].value)){
        isError = true;
        errorMsg += "\n 子供医療証の番号は 7桁以下です。"
    }
    if(confirm('子供情報を変更しますか？')){
        if(!isError) form.submit();
        else alert(errorMsg);
    }else{
        return;
    }
}

function isSubmit(form){
    if(confirm('子供情報を登録をしますか？')) form.submit();
    else return;
}

function isDelete(form){
	if(confirm('子供情報を削除しますか？')) form.submit();
    else return;
}

function checkBirth(){
    let brith = document.querySelectorAll(".birth input[type='number']");
    if(brith[0].value.length != 4) return true;
    if(brith[1].value.length != 2) return true;
    if(brith[2].value.length != 2) return true;
    if(brith[1].value < 1 || brith[1].value > 12) return true;
    if(brith[2].value < 1 || brith[2].value > 31) return true;
	return false;
}

function checkI_expiry_date(){
	let expiry_date = document.querySelectorAll(".expiry_date input[type='number']");
    if(expiry_date[0].value.length != 4) return true;
    if(expiry_date[1].value.length != 2) return true;
    if(expiry_date[2].value.length != 2) return true;
    if(expiry_date[1].value < 1 || expiry_date[1].value > 12) return true;
    if(expiry_date[2].value < 1 || expiry_date[2].value > 31) return true;
	return false;
}