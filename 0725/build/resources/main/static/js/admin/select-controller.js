let form = document.getElementById("main-form");
let selectChanged=document.getElementById("selectChanged");

function changeMC(){
    selectChanged.value="true";
    form.submit();
    selectChanged.value="false";
}