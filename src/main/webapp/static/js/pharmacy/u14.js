function isSubmit(form){
    if(confirm('薬情報を登録しますか？')){
        form.submit();
    }else{
        return;
    }
}

function checkForm(form){
    let items = document.querySelectorAll('.items_title2');
    let isError = false;
    for(let i of items){
        if(i.nextElementSibling.value === null || i.nextElementSibling.value === ''){
            isError = true;
        }
    }
    if(isError) alert('必須項目を入力してください。');
    else form.submit();
}

var file = document.querySelector('#img_file');

file.onchange = function () { 
    var fileList = file.files ;
    
    var reader = new FileReader();
    reader.readAsDataURL(fileList [0]);

    reader.onload = function  () {
        document.querySelector('#preview').src = reader.result ;
    }; 
}; 