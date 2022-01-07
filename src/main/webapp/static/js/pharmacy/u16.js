function isUpdate(form){
    if(confirm('薬情報を変更しますか？')){
        form.submit();
    }else{
        return;
    }
}

function isDeleteImg(drug_num){
	if(confirm('薬の写真を削除しますか？')){
		location.href='PharmacyController?action=u16_s2&drug_num='+drug_num;
	} else{
		return;
	}
}

var file = document.querySelector('#img_file');

file.onchange = function () { 
    var fileList = file.files ;
    
    // 읽기
    var reader = new FileReader();
    reader.readAsDataURL(fileList [0]);

    //로드 한 후
    reader.onload = function  () {
        document.querySelector('#preview').src = reader.result ;
    }; 
}; 