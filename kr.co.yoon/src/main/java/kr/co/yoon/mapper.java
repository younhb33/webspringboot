package kr.co.yoon;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jdbc.repository.query.Query;

//Mapper => 무조건 interface임
@Mapper
public interface mapper {
	List<membership_DTO> alldata();
	List<membership_DTO> onedata(String MID);
	int save_member(membership_DTO dto);
	
}
