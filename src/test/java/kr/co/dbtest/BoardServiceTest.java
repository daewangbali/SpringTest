package kr.co.dbtest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.domain.BoardVO;
import kr.co.service.BoardService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceTest {
	
	@Autowired
	BoardService bs;
	
	@Test
	public void getListTest() {
		log.info("getListTest.....................");
		List<BoardVO> vo = bs.getList();
		
		for(BoardVO boardVO : vo) {
			log.info(boardVO);
		}
	}
	
	@Test
	public void registerTest() {
		log.info("registerTest.....................");
		BoardVO vo = new BoardVO();
		vo.setTitle("새글제목 insertTest 10");
		vo.setContent("새글제목 insertTest 10");
		vo.setWriter("작성자 insert 10");
		log.info(vo);
		
		bs.register(vo);
		this.getListTest();
	}
	
	@Test
	public void registerSelectKeyTest() {
		log.info("registerSelectKeyTest.....................");
		BoardVO vo = new BoardVO();
		vo.setTitle("새글제목 insertTest 10");
		vo.setContent("새글제목 insertTest 10");
		vo.setWriter("작성자 insert 10");
		log.info(vo);
		
		long bno = bs.registerSelectKey(vo);
		log.info(bno);
		this.getListTest();
	}
	
	@Test
	public void getTest() {
		log.info("getTest.....................");
		log.info(bs.get(15L));
	}
	
	@Test
	public void modifyTest() {
		log.info("modifyTest.....................");
		BoardVO vo = new BoardVO();
		vo.setTitle("수정제목 modifyTest 10");
		vo.setContent("수정제목 modifyTest 10");
		vo.setBno(15L);
		log.info(vo);
		
		log.info(bs.modify(vo));
		
		this.getListTest();
	}
	
	@Test
	public void removeTest() {
		log.info("removeTest.....................");
		log.info(bs.remove(16L));
		
		this.getListTest();
	}
	

}