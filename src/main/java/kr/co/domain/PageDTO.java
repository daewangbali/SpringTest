package kr.co.domain;
import lombok.Data;

@Data
public class PageDTO {
	
	private int startPage,endPage;
	private Long total; //페이지버튼번호,끝번호,전체
	private boolean prev,next; //앞페이지 뒤페이지 가는 
	private Criteria cri; // 
	
	public PageDTO(Criteria cri,Long total) {
		this.cri = cri;
		this.total = total;
		
		
		this.endPage = (int)Math.ceil(cri.getPageNum() / 10.0) * 10;//end페이지 구하기 -> criteria에서 현재페이지 가져와서 10으로 나눠줌 (start페이지를 구하기 위한 end페이지)
		this.startPage = endPage - 9;
		
		int realEnd = (int)Math.ceil((double)total/cri.getAmount());//진짜 마지막 페이지 구하기
		
		this.endPage = realEnd < endPage ? realEnd : endPage;

		this.prev = startPage > 1;
		this.next = realEnd > endPage;
				
				
	}

}
