let formEdit=document.getElementById("form-edit");

function edit(type){
    formEdit.action="/member/edit?type="+type;
    formEdit.submit();
}