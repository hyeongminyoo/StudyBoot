function nullcheck(data, dest, kind){
    if(data == null || data == ''){
        $(dest).html(kind+"은(는) 필수입니다.");
        return false;
    }else{
        $(dest).html("정상");
        return true;
    }
}

function equals(data, checkdata){
    if(data == checkdata){
        return true;
    }else{
        return false;
    }
}