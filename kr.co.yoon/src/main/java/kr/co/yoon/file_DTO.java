package kr.co.yoon;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository("file_DTO")
public class file_DTO {
	int AIDX;
	String ORI_FILE, NEW_FILE, API_FILE, FILE_URL, FILE_DATE;
	byte[] FILE_BIN;	//BLOB 자료형은 binary라서 byte에 저장 (오라클에는 있고 mysql에는 없음)
	//BLOB, CLOB => 다운로드
}