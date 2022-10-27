console.log("memberAdd");

$("#all").click(function(){
    let ch = $(this).prop("checked");
    console.log("ch:",ch);

    $(".check").prop("checked",ch);

})

$(".check").click(function(){
    // console.log("check click");

    $("#all").prop("checked",true);

    // let flag = true;
    $(".check").each(function(index, item){
        let c = $(item).prop("checked");
        console.log(index+1,"번째 동의 :",c);
        if(!c){
            // flag=false;
            $("#all").prop("checked",false);
            return false;
        }

    })

    // $("#all").prop("checked",flag);


    // $.each(ar,function(index,item){
    //     console.log(ar);
    //     console.log(item);
    //     if(!item){
    //         return;
    //     }else{
    //         $("#all").prop("checkd",true);
    //     }
    // })


})
// id , pw ,pwcheck , name , email
let results = [false,false,false,false,false];
$("#inputId").blur(function(){
    let id = $("#inputId").val();
    let result = nullcheck(id,"#inputUserNameResult","ID");
    results[0] = result;

    //단 id가 비어있지 않을 때 실행
    if(result){
        $.get("./idCheck?id="+id,function(data){
            console.log("data:",data);
            if(data=='0'){
                $("#inputUserNameResult").html("사용가능한 ID");
                results[0] = true;
            }else{
                $("#inputUserNameResult").html("이미 사용중인 ID");
                results[0] = false;
            }
        })
    }

})


// $("#inputId").change(function(){
    
//     const xhttp = new XMLHttpRequest();
//     xhttp.open("GET","./idCheck?id="+$("#inputId"));
//     xhttp.send();
//     xhttp.onreadystatechange = function(){
//         if(this.readyState == 4 && this.status == 200){
//             let result = xhttp.responseText.trim();
//             console.log("결과:",result);
//         }
//     }

// })



// $("#inputPw").blur(function(){
//     $("#inputPwCh").blur();
//     let result = nullcheck($("#inputPw").val(),"#inputPasswordResult","PW");
//     results[1] = result;
// })

$("#inputPw").on({
    blur : function(){
         let result = nullcheck($("#inputPw").val(),"#inputPasswordResult","PW");
        results[1] = result;
         },
    change : function(){
        $("#inputPwCh").val("");
        results[2]=false;
        $("#inputPasswordCheckResult").html("");
        }

})


$("#inputPwCh").blur(function(){
    let result = equals($("#inputPw").val() , $("#inputPwCh").val());
    if(result){
        $("#inputPasswordCheckResult").html("정상");
    }else{
        $("#inputPasswordCheckResult").html("동일한 PW입력필수");
    }
    results[2] = result;
})


$("#inputName").blur(function(){
    let result = nullcheck($("#inputName").val(),"#inputNameResult","이름");
    results[3] = result;
})

$("#inputEmail").blur(function(){
    let result = nullcheck($("#inputEmail").val(),"#inputEmailResult","Email");
    results[4] = result;
})


$("#joinButton").click(function(){
    //event 강제 실행
    
    if(results.includes(false)){
        alert("입력란에 입력해주세요.");
    }else{
        alert("전송");
        // $("#joinForm").submit();
    }


    
    // let c = true;

    // $.each(results,function(index,item){
    //     if(!item){
    //         alert("입력란에 입력해주세요.");
    //         c=false;
    //         return false;
    //     }
    // })

    // if(c){
    //     alert("성공");
    //     // $("#joinForm").submit();
    // }

})


//=================================
function abc(){
let idresult = false;
let pwresult = false;
let pwchresult = false;
let nameresult = false;
let emailresult = false;


//id 검증
$("#inputId").blur(function(){
    if(this.value == ""){
        this.focus();
        $("#inputUserNameResult").html("<h5>ID를 입력하세요</h5>");
        idresult = false;
    }else{
        $("#inputUserNameResult").empty();
        idresult = true;
    }


})



//pw 검증
$("#inputPw").blur(function(){
    if(this.value == ""){
        this.focus();
        $("#inputPasswordResult").html("<h5>Pw를 입력하세요</h5>");
        pwresult = false;
    }else{
        $("#inputPasswordResult").empty();
        pwresult = true;
    }
})

//pw 체크
$("#inputPwCh").keyup(function(){
    if(this.value != $("#inputPw").val() || this.value == ""){
        this.focus();
        $("#inputPasswordCheckResult").html("<h5>동일한 PW를 입력하세요.</h5>");
        pwchresult = false;
    }else{
        $("#inputPasswordCheckResult").empty();
        pwchresult = true;
    }
})

//이름 검증
$("#inputName").blur(function(){
    if(this.value == ""){
        this.focus();
        $("#inputName").next().html("<h5>이름을 입력하세요.</h5>");
        nameresult = false;
    }else{
        $("#inputName").next().html("");
        nameresult = true;
    }
})

//이메일 검증
$("#inputEmail").blur(function(){
    if(this.value == ""){
        this.focus();
        $("#inputEmail").next().html("<h5>Email 주소를 입력하세요.</h5>");
        emailresult = false;
    }else{
        $("#inputEmail").next().html("");
        emailresult = true;
    }
})



$("#joinButton").click(function(){
    //event 강제 실행
    // $("#joinForm").submit();

    let inputId = $("#inputId");
    let inputPw = $("#inputPw");
    let inputPwCh = $("#inputPwCh");
    let inputName = $("#inputName");
    let inputEmail = $("#inputEmail");


    if(idresult && pwresult && pwchresult && nameresult && emailresult && $("#all").prop("checked")){
        // $("#joinForm").submit();
        alert("회원가입");
    }else if(idresult == true && pwresult == true && pwchresult == true && nameresult == true && emailresult == true && $("#all").prop("checked") == false){
        alert("약관동의가 필요합니다");
    }else{
        inputEmail.blur();
        inputName.blur();
        inputPwCh.keyup();
        inputPw.blur();
        inputId.blur();
        inputId.focus();
        alert("입력란에 올바른 값을 입력해주세요.");
    }

})
}



// -----------------------------

