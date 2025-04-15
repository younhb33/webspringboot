package kr.co.yoon;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository("file_DTO")
public class file_DTO {
	int AIDX;
	String ORI_FILE, NEW_FILE, API_FILE, FILE_URL, FILE_DATE;
	byte[] FILE_BIN; //BLOB
}
