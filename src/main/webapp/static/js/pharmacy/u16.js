function isUpdate(form){
    if(confirm('薬情報を変更しますか？')){
        form.submit();
    }else{
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