function isSubmit(form){
    let items_medicine = document.querySelectorAll('#items_medicine input');
    let regExceptionStr = "以下の項目に空白・数字以外が記載されています。\n";
    let isError = false;
    var regExp = /^[0-9]+$/;

    for(let i of items_medicine){
        if(i.getAttribute('type') === 'number' && !regExp.test(i.value)){
            regExceptionStr += i.previousElementSibling.textContent + " ";
            isError = true;
        }
    }
    if(!isError){
        form.submit();
    }else{
        if(regExceptionStr === "以下の項目に数字以外が記載されています。") regExceptionStr = null;
        else alert(regExceptionStr);
    }
}

/* TAP MENU */
const medicine_tap_btn = document.getElementById('medicine_tap_btn');
const medicine_text_btn = document.getElementById('medicine_text_btn');
const medicine_tap_menu = document.getElementById('medicine_tap_menu');
const medicine_con = document.getElementById('medicine_con');
const medicine = document.getElementById('medicine');
let tap_item = document.querySelectorAll('.tap_item input');
var tap_item_ary = new Array();
let medicine_name = document.getElementsByName('medicine_name[]');
let medicine_cbx = document.getElementsByName('medicine_cbx[]');
let isOpen = false;

function openTab(){
    if(isOpen){
        medicine_tap_menu.style.display = "none";
        document.getElementById('search_result').style.display = "none";
        isOpen = false;
    }else{
        medicine_tap_menu.style.display = "block";
        isOpen = true;
        for(let i of tap_item){
            if(i.checked){
                i.parentElement.style.display = "none";
            }else{
                i.parentElement.style.display = "inline-block";
            }
        }
    }
}

function closeTap(){
    medicine_tap_menu.style.display = "none";
    document.getElementById('search_result').style.display = "none";
    isOpen = false;
}
var count = 0;
var isWhile = true;

for(let i of tap_item){
    i.addEventListener('click', function(){
        if(i.checked){
            count++;
            const medicine_copy = medicine_con.cloneNode(true);
            i.disabled = true;
            if(medicine_copy.getAttribute("id").indexOf(count) == -1){
                medicine_copy.setAttribute("id","medicine_con"+count);
                medicine_copy.querySelector('button').style.display = "block";
                medicine_copy.firstChild.nextSibling.id = "items_medicine";
                medicine_copy.style.display = "unset";
                medicine_copy.querySelector('button').value = count;
                var whatIndex = 0;
                for(let index of medicine_cbx){
                    if(index.value === i.value){
                        medicine_copy.querySelector('.medicine_name_index').value = medicine_cbx[whatIndex].value;
                        medicine_copy.querySelector('.medicine_name_index').disabled = true;
                    }
                    whatIndex++;
                }
            }
            i.parentElement.style.display = "none";
            if(tap_item_ary.indexOf(i) == -1){
                tap_item_ary.push(i);
            }
            medicine.appendChild(medicine_copy);
        }
        isWhile = true;
    });
}

function deleteItem(e){
    for(let i of tap_item_ary){
        //nextSiblingは下にいるElementを探す
        if(i.value === e.nextSibling.nextSibling.childNodes[3].value){
            i.parentElement.style.display = "inline-block";
            i.checked = false;
            i.disabled = false;
        }
    }
    count--;
    e.parentElement.parentElement.remove();
}

let search_result = document.getElementById('search_result');
function searchMedicine(){
let isSearched = false;
    let keyword = document.getElementById('search_medicine_keyword').value;
    medicine_tap_menu.style.display = "block";
    for(let i of medicine_cbx){
        if(i.value.indexOf(keyword) != -1){
            i.parentElement.style.display = "inline-block";
            isSearched = true;
        }else{
            i.parentElement.style.display = "none";
        }
    }
    if(!isSearched) search_result.innerHTML = "検索結果がありません";
    else search_result.innerHTML = "検索結果";
    search_result.style.display = "inline-block";
    isOpen = true;

}
