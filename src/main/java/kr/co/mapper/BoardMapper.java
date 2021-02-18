package kr.co.mapper;

import java.util.List;

import kr.co.domain.BoardVO;

public interface BoardMapper {
	
	//체크용
	//@Select("select * from tbl_board")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int update(BoardVO board);
	
	public int delete(Long bno);

}
