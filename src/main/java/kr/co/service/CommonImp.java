package kr.co.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class CommonImp implements Common{
	
	// upload라는 폴더를 만들고 board(or notice..) 만들고 년도 월별
	//upload/board/2021/03/08/file1.jpg 이런식
	
	private String makeFolder(String category, String upload) {
		StringBuilder sb = new StringBuilder(upload);
		sb.append(File.separator + category);
		
		Date now = new Date();
		
		sb.append(File.separator + new SimpleDateFormat("yyyy").format(now));
		sb.append(File.separator + new SimpleDateFormat("MM").format(now));
		sb.append(File.separator + new SimpleDateFormat("dd").format(now));
		
		String folder = sb.toString(); //폴더위치
		
		File f = new File(folder); // 폴더 실제 대상의 경로(위치)
		
		if(f.exists()) {
			f.mkdirs();
		}
		
		return folder;
		
	}

	@Override
	public String upload(String category, MultipartFile file, HttpSession session) {
		String resources = session.getServletContext().getRealPath("resources"); //resources에 실경로
		String upload = resources + File.separator + "upload";
		
		String folder =  this.makeFolder(category,upload);
		
		//"aslkjfe_filename.txt" -> aslkjfe 이부분 랜덤으로 아이디부여
		String uuid = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
		
		try {
			file.transferTo(new File(folder , uuid));
		} catch (Exception e) {
			e.getMessage();
		}
		
		return folder.substring(resources.length())+ File.separator + uuid;
	}

}
