export class cdn_lists{
	
	cdn_listdel(){
		//querySelector : 한 개의 오브젝트 값을 가져오는 역할
		//querySelectorAll : 한 개 이상의 같은 이름을 가진 오브젝트를 가져오는 역할
		var count = 0;
		var ob = document.querySelectorAll("#ls .ck");
		console.log(ob);
		var i = 0;
		do{
			if(ob[i].checked == true){
				count++;
			}
			i++;
		}while(i < ob.length);
		//체크 안 된거는 백엔드로 받을 때 무조건 null로 감
		if(count == 0){
			alert("선택 삭제시 하나라도 선택이 되어야만 삭제가 진행됩니다.");
		}else{
			if(confirm("삭제시 데이터는 복구되지 않습니다. \n 삭제 진행 하시겠습니까?")){
				frm.submit();
				
			}
		}
		
	}
	
}