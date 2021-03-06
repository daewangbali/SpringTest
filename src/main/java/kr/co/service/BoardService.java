package kr.co.service;

import java.util.List;

import kr.co.domain.BoardVO;
import kr.co.domain.CommentVO;
import kr.co.domain.Criteria;

public interface BoardService {
	
	public List<BoardVO> getList();
	
	public List<BoardVO> getList(Criteria cri);
	
	public void register(BoardVO board);
	
	public Long registerSelectKey(BoardVO board);
	
	public BoardVO get(Long bno);
	
	public int modify(BoardVO board);
	
	public int remove(Long bno); 
	
	public Long getTotalCount(Criteria cri);
	
	//20210303 아래 추가
	public void comment_register(CommentVO comment);
			
	public List<CommentVO> comment_get_list(Long bno);
			
	public void comment_modify(CommentVO comment);
			
	public void comment_remove(Long id);
		
}
