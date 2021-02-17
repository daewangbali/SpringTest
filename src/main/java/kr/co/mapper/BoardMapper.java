package kr.co.mapper;

import java.util.List;

import kr.co.domain.BoardVO;

public interface BoardMapper {
	
	//체크용
	//@Select("select * from tbl_board")
	public List<BoardVO> getList();

}
