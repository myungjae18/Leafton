var wrapper = document.getElementById("wrapper");
var x = document.getElementById("login");
var y = document.getElementById("register");
var z = document.getElementById("btn");
function goLogin(){
    x.style.left = "50px";
    y.style.left = "450px";
    y.style.width = "280px";
    z.style.left = "0";
    wrapper.style.width= "380px";
    wrapper.style.height= "480px";
}
function goRegister(){
    x.style.left = "-400px";
    y.style.left = "50px";
    y.style.width = "600px";
    z.style.left = "110px";
    wrapper.style.width= "700px";
    wrapper.style.height= "1100px";
}

function emailCheck(email){
    alert(email);
}