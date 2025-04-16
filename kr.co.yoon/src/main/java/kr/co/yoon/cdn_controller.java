package kr.co.yoon;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletResponse;

//CDN 전용 컨트롤러 
@Controller
public class cdn_controller {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="file_DTO")
	file_DTO file_DTO;
	
	@Resource(name="cdn_model")
	cdn_model cdn_model;
	
	@Autowired
	private cdn_service cs;
	
	PrintWriter pw = null;
	
	/*
	 ftp에 파일이 없을경우 DB가 삭제 되지 않는 버그가 발생함
	 해당 버그를 수정하여 FTP에 파일이 없어도 DB에 데이터가 삭제 되도록 수정
	 */
	
	@PostMapping("/cdn/cdn_delete.do")
	   public String cdn_delete(@RequestParam(name = "cbox") String cbox[]) throws Exception {
			//	      this.log.info(String.valueOf(cbox.length));
	      int countck = 0; // 성공한 결과만 +1 증가시키는 변수
	      for (String idx : cbox) {
	         this.file_DTO.setAIDX(Integer.parseInt(idx)); // 필드에있는 dto에 setter로 이관
	         List<file_DTO> one = this.cs.cdn_select(1, this.file_DTO); // 서비스에 dto값 전달
	         boolean result = this.cdn_model.cdn_ftpdel(one.get(0).getNEW_FILE(),idx); // 개발자가 생성한 파일명 출력
	         if(result == true) { // 파일 삭제도 되고, 데이터베이스도 삭제가 된 경우
	            countck++;
	         }
	      }
	      if(cbox.length == countck) {
	         this.log.info("정상적으로 모두 삭제 완료 되었습니다.");
	      }else {
	         this.log.info("삭제되지 않은 데이터가 있습니다.");
	      }
	       return "redirect:/cdn/cdn_filelist.do";
	   }

	
	
	/*
	 Controller에서 모든 list 화면을 출력시
	 1. 한 페이지 당 출력하는 데이터 개수
	 2. 데이터 검색어를 받을 경우 
	 3. 사용자가 페이지 번호를 누를 경우 
	 */
	//@ResponseBody : return 결과값을 즉시 실행하여 해당 메소드에 출력하는 역할
	@GetMapping("/cdn/image/{api_id}")
	//또는 @ResponseBody 위에 선언해도 됨
	public @ResponseBody byte[] image(@PathVariable(name = "api_id")String id) throws Exception {	//ResponseBody : 해당 메소드에서 출력
		byte[] img = null;
		if(!id.equals(null) || !id.equals("")) {
			List<file_DTO> result = this.cs.cdn_images(id);
			try {
				URL url = new URL(result.get(0).getFILE_URL());
				//AWS, GCP => https, http 둘 다 존재 조심 
				HttpURLConnection hc = (HttpURLConnection)url.openConnection();
				InputStream is = hc.getInputStream();
				img = IOUtils.toByteArray(is);
			} catch (Exception e) {
				this.log.info("CDN URL 연결실패!!");
			}
		}
		return img;
	}
	
	@GetMapping("/cdn/cdn_filelist.do")
	public String cdn_filelist(Model m) {
		List<file_DTO> all = this.cs.cdn_select(0,this.file_DTO);
		m.addAttribute("all", all);
		
		return null;
	}
	
	//CDN Server로 파일 전송 및 DB 저장 
	@PostMapping("/cdn/cdn_fileok.do")
	public String cdn_fileok(
			@RequestParam(name="mfile") MultipartFile mfile[],
			@RequestParam(name="url") String url, ServletResponse res) throws Exception{	
		//처음부터 배열로 받아야 나중에 기획에 파일 여러개로 받는거로 바뀌었을때 편함 	
		try {
			//사용자가 업로드한 파일명을 개발자가 원하는 이름으로 변경 후 FTP 전송
			boolean result = this.cdn_model.cdn_ftp(mfile[0], url);
			res.setContentType("text/html;charset=utf-8");
			this.pw = res.getWriter();
			
			if(result==true) {
				this.pw.print("<script>"
						+ "alert('정상적으로 등록 완료 되었습니다.');"
						+ "location.href='./cdn_filelist.do';"
						+ "</script>");
				this.log.info("저장완료");
			}else {
				
				this.pw.print("<script>"
						+ "alert('올바르게 저장되지 않았습니다.');"
						+ "history.go(-1);"
						+ "</script>");
				this.log.info("저장실패");
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			
		}
		
		
		
		return null;
	}
	
	
}