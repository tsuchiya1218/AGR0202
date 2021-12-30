function isAvailable(form){
    if(confirm('電子処方箋を有効可にしますか？')) form.submit();
    else return;
}

// let today = new Date();
// let endDay = today;
// window.onload = function(){
//      //ブラウザで時刻差が9時間あるため足す
//   today.setHours(today.getHours()+9);
//   today = today.toISOString().slice(0, 10);

//   let start = document.getElementById("start_date");
//   let end = document.getElementById("end_date");
//   start.value = today;
//   end.value = today;
//   start.setAttribute("max", start.value);
//   end.setAttribute("max", end.value);
//   start.setAttribute("min", "2020-01-01");
//   end.setAttribute("min", "2020-01-01");
  
// }