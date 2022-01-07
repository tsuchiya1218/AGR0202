var startDate = document.getElementById('startDate');
var endDate = document.getElementById('endDate');
window.onload = function(){
	endDate.max = new Date().toISOString().slice(0, 10);
	startDate.max = new Date().toISOString().slice(0, 10);
}


function limitEndDate(){
	endDate.min = startDate.value;
	if(startDate.value > endDate.value){
		endDate.value = startDate.value;
	}
}