package kr.co.service;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class CommonImp implements Common {

	// upload라는 폴더를 만들고 board(or notice..) 만들고 년도 월별
	// upload/board/2021/03/08/file1.jpg 이런식

	private String makeFolder(String category, String upload) {
		StringBuilder sb = new StringBuilder(upload);
		sb.append(File.separator + category);

		Date now = new Date();

		sb.append(File.separator + new SimpleDateFormat("yyyy").format(now));
		sb.append(File.separator + new SimpleDateFormat("MM").format(now));
		sb.append(File.separator + new SimpleDateFormat("dd").format(now));

		String folder = sb.toString(); // 폴더위치

		File f = new File(folder); // 폴더 실제 대상의 경로(위치)
		//System.out.println(f.exists());
		
		if (!f.exists()) {
			System.out.println(f.toString());
			f.mkdirs();// 상위폴더 하위폴더 다 만듬
		}

		return folder;

	}

	@Override
	public String upload(String category, MultipartFile file, HttpSession session) {
		// String resources = session.getServletContext().getRealPath("resources");
		// //resources에 실경로
		String resources = "D:\\jjjung\\JavaEE\\Testgj\\src\\main\\webapp\\resources"; // 작업resources
		String upload = resources + File.separator + "upload";

		String folder = this.makeFolder(category, upload);

		// "aslkjfe_filename.txt" -> aslkjfe 이부분 랜덤으로 아이디부여
		String uuid = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

		try {
			file.transferTo(new File(folder, uuid));
		} catch (Exception e) {
			e.getMessage(); // 간단한 오류메세지
		}
		System.out.println(folder.toString());
		return folder.substring(resources.length()) + File.separator + uuid;
	}
	
//0309 아래추가
	@Override
	public File download(String filepath, String filename, HttpSession session, HttpServletResponse response) {
//		File file = new File(session.getServletContext().getRealPath("resources")+filepath);
		File file = new File("D:\\jjjung\\JavaEE\\Testgj\\src\\main\\webapp\\resources" + filepath); //절대경로 쓸 때
		
		String mime = session.getServletContext().getMimeType(filepath);
		
		if(mime == null) {
			mime = "application/octet-stream"; //다운로드 받는 파일의 형식을 골라줄 수 있다
		}
		
		response.setContentType(mime);
		
		try {
			filename = URLEncoder.encode(filename,"utf-8").replaceAll("\\+", "%20");
			
			response.setHeader("content-disposition", "attachment; filename" + filename);
			
			ServletOutputStream out = response.getOutputStream();
			FileCopyUtils.copy(new FileInputStream(file), out);
			out.flush(); // 안에 남아있는 잔량 데이터 다 보내줌
			
		} catch (Exception e) {
			e.printStackTrace(); // 자세한 오류메세지
		}
		
		return file;
	}

}
