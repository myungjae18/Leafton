let formEdit=document.getElementById("form-edit");
let password=document.getElementById("password");
let checkPassword=document.getElementById("check-password");

function edit(type){
    formEdit.action="/member/edit?type="+type;
    formEdit.submit();
}

function passwordCheck(){
    if(password.innerText!=checkPassword.innerText) {
        alert("패스워드와 패스워드 확인이 일치하지 않습니다");
    } else {

    }
}