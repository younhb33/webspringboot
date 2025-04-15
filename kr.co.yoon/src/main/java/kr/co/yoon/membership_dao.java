package kr.co.yoon;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//dao클래스를 만들 때 implements 에서 검색해서 적용
//서비스 어노테이션 적용하기 

//@Component : @Controller, Service, Repository 모두 포함된 어노테이션
//@Service : 클래스를 해당 코드 로직에 정보를 담고 있는 어노테이션이며, 
//			interface를 호출시 작동되는 클래스를 말함 (@Autowired로 호출)

@Service
public class membership_dao implements membership_service {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	//config가 읎음 DB연결은? 프로퍼티 파일이 해줌 
	
	//Spring에서는 리소스로 템플릿 불러왔을때 셀렉트올같은게 나와서 사용했었는데
	//boot에서는 mapper를 이용하기 때문에 바로 사용함 
	
	@Autowired
	private mapper mp;
	
	@Override
	public int join_member(membership_DTO dto) {
		int result = this.mp.save_member(dto);
		return result;
	}
	
	// selectList, sleectOne, insert, delete, update(X)
	@Override
	public List<membership_DTO> alldata() {
		List<membership_DTO> all = this.mp.alldata();
		this.log.info(all.toString());
		
		return all;
	}

}
