package kr.co.mapper;

import java.util.List;

import kr.co.domain.BoardVO;
import kr.co.domain.CommentVO;
import kr.co.domain.Criteria;

public interface BoardMapper {
	
	//체크용
	//@Select("select * from tbl_board")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);

	public BoardVO read(Long bno);
	
	public int update(BoardVO board);
	
	public int delete(Long bno); 
	
	public List<BoardVO> getListPage(Criteria cri); //추가
	
	public Long getTotalCount(Criteria cri); //조건따라서 총 페이지수 바꿔야 하니까 criteria 미리 만들어 놓음

	//2020303 추가
	public void comment_insert(CommentVO comment);
	
	public List<CommentVO> comment_list(Long bno);
	
	public void comment_update(CommentVO comment);
	
	public void comment_delete(Long id);
	
	
}
