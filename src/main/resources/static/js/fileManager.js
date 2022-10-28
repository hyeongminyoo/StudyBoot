let count = 0;
try {
    
    console.log(len);
    count = count + len;
} catch (error) {
    
}

$("#fileAdd").click(function(){
    if(count>4){
        alert("파일첨부는 최대 5개입니다.");
        return;
    }
    let code = '<div class="mb-3">'+
                    '<label for="file" class="form-label">File</label>'+
                    '<input type="file" name="files">'+
                    '<button type="button" onclick="fdel(this)" class="del">삭제</button>'+               
                '</div>';
    $("#files").append(code);
    count++;
})

//onclick="fdel(this)"

function fdel(e){
    $(e).parent().remove();
    count--;
}

// $("#files").on("click",".del",function(){
//     $(this).parent().remove();
//     count--;
// })




//// 글 수정 시 첨부파일 삭제 (하드디스크에서도)
let flag = true;
$("#files").on("click",".deleteFile",function(){
    //DB, HDD 삭제
    let file = this;
    let check = window.confirm("삭제하시겠습니까?");
    if(flag){
        let fileNum = $(this).attr("data-fileNum");
        console.log(fileNum);
        flag=false;
    }
    let fileSize = $("#files").attr("data-file-size");
    console.log(fileSize);
    if(check){
        //post방식 
        // /qna/fileDelete
        //파라미터 : fileNum
        $.ajax({
            type:"POST",
            url:"./fileDelete",
            data:{
                fileNum:fileNum
            },
            success:function(data){
                console.log("data:",data);
                if(data == '1'){
                    $(file).parent().remove();
                    alert("삭제완료");
                    count--;
                }
            },
            error:function(){
                console.log("에러발생");
            }
        })
    }
})