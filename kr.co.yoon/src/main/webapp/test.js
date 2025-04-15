document.querySelector("#btn").addEventListener("click",function(){
	new memberjoin().input_check();
});

export class memberjoin{
	
	input_check(){
		if(frm.MID.value == ""){
			alert("아이디를 입력하세요.");
		}else if(frm.MPASS.value == ""){
			alert("패스워드를 입력하세요.");
		} else if (frm.MNAME.value == "") {
			alert("고객명을 입력하세요.");
		} else if (frm.MNICK.value == "") {
			alert("닉네임을 입력하세요.");
		} else if (frm.MEMAIL.value == "") {
			alert("이메일을 입력하세요.");
		} else if (frm.MHP.value == "") {
			alert("연락쳐를 입력하세요.");
		} else {
			frm.submit();
		}
	}

}