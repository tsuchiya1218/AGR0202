$(document).ready(function(){
    var formInputs = $('input[type="email"],input[type="password"]');
    formInputs.focus(function() {
         $(this).parent().children('p.formLabel').addClass('formTop');
    });
    formInputs.focusout(function() {
      if ($.trim($(this).val()).length == 0){
      $(this).parent().children('p.formLabel').removeClass('formTop');
      }
    });
    $('p.formLabel').click(function(){
       $(this).parent().children('.form-style').focus();
    });
  });

let isLogin_content = false;
var main_box = document.getElementById('main_box');
document.getElementById('account-btn-check').addEventListener('click', function(){
    if(main_box.style.display === 'none'){
        main_box.style.display = 'block';
    }else{
        main_box.style.display = 'none';
    }
})