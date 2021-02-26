package kr.co.dbtest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.domain.BoardVO;
import kr.co.domain.Criteria;
import kr.co.mapper.BoardMapper;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTest {
	
	@Autowired
	BoardMapper bm;
	
	@Test
	public void getListTest() {
		log.info("getListTest.....................");
		List<BoardVO> vo = bm.getList();
		
		for(BoardVO boardVO : vo) {
			log.info(boardVO);
		}
	}
	
	@Test
	public void insertTest() {
		log.info("insertTest......................");
		BoardVO vo = new BoardVO();
		vo.setTitle("새글제목 insertTest 10");
		vo.setContent("새글제목 insertTest 10");
		vo.setWriter("작성자 insert 10");
		log.info(vo);
		
		bm.insert(vo);
		this.getListTest();
 	}
	
	@Test
	public void insertSelectKeyTest() {
		log.info("insertSelectKeyTest......................");
		BoardVO vo = new BoardVO();
		vo.setTitle("새글제목 insertTest 10");
		vo.setContent("새글제목 insertTest 10");
		vo.setWriter("작성자 insert 10");
		log.info(vo);
		
		bm.insertSelectKey(vo);
		log.info(vo.getBno());
		this.getListTest();
	}
	
	@Test
	public void readTest() {
		log.info("readTest......................");
		log.info(bm.read(10L));
	}
	@Test
	public void updateTest() {
		log.info("updateTest......................");
		
		BoardVO vo = new BoardVO();
		vo.setTitle("수정제목 insertTest 10");
		vo.setContent("수정내용 insertTest 10");
		vo.setBno(10L);
		log.info(vo);
		
		log.info(bm.update(vo));
		
		this.getListTest();
	}
	
	@Test
	public void deleteTest() {
		log.info("deleteTest......................");
		log.info(bm.delete(11L));
		
		this.getListTest();

	}
	
	@Test
	public void getListPageTest() {
		log.info("getListPageTest.....................");
		Criteria cri = new Criteria();
		List<BoardVO> vo = bm.getListPage(cri);
		
		for(BoardVO boardVO : vo) {
			log.info(boardVO);
		}
	}
	
	@Test
	public void searchTest() {
		log.info("searchTest..............");
		Criteria cri = new Criteria();
		
		
		cri.setType("T");
		cri.setKeyword("test");
		
		List<BoardVO> list = bm.getListPage(cri);
		
		for(BoardVO boardVO : list) {
			log.info(boardVO);
		}
	}
}
