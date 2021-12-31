function isUpdate(form){

	if(checkBrith()){
		alert('正しい生年月日を入力してください。例)1990 09 05');
		return;
	}
	if(checkI_expiry_date()){
		alert('正しい有効期限を入力してください。例)2022 05 09');
		return;
	}
	
    if(confirm('変更しますか？')) form.submit();
    else return;
}

function checkBrith(){
    let brith = document.querySelectorAll(".birthday input[type='number']");
    if(brith[0].value.length != 4) return true;
    if(brith[1].value.length != 2) return true;
    if(brith[2].value.length != 2) return true;
    if(brith[1].value < 1 || brith[1].value > 12) return true;
    if(brith[2].value < 1 || brith[2].value > 31) return true;
	return false;
}

function checkI_expiry_date(){
	let expiry_date = document.querySelectorAll(".hoken input[type='number']");
    if(expiry_date[0].value.length != 4) return true;
    if(expiry_date[1].value.length != 2) return true;
    if(expiry_date[2].value.length != 2) return true;
    if(expiry_date[1].value < 1 || expiry_date[1].value > 12) return true;
    if(expiry_date[2].value < 1 || expiry_date[2].value > 31) return true;
	return false;
}

function isSubmit(form){
	if(confirm('メールアドレスを変更しますか？')) form.submit();
	else return;
}