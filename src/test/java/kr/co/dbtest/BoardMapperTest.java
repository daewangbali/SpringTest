package kr.co.dbtest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.domain.BoardVO;
import kr.co.domain.CommentVO;
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
		vo.setFilename("새글파일 insertTest 10");
		vo.setFilepath("새글경로 insertTest 10");
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
		vo.setFilename("새글파일 insertTest 10");
		vo.setFilepath("새글경로 insertTest 10");
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
		vo.setBno(15443L);
		vo.setFilename("수정파일 updateTest 10");
		vo.setFilepath("수정경로 updateTest 10");
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
		
		
		cri.setType("TCW");
		cri.setKeyword("test");
		
		List<BoardVO> list = bm.getListPage(cri);
		
		log.info("totalcount : "+bm.getTotalCount(cri));
		
		for(BoardVO boardVO : list) {
			log.info(boardVO);
		}
	}
	
	@Test
	public void comment_listTest() {
		log.info("comment_listTest..............");
		
		List<CommentVO> list = bm.comment_list(5000L);
		
		for(CommentVO commentVO : list) {
			log.info(commentVO);
		}
		
	}
	
	@Test
	public void comment_insertTest() {
		log.info("comment_insertTest............");
		
		CommentVO comment = new CommentVO();
		
		comment.setWriter("comment 작성자 10");
		comment.setContent("comment 내용 10");
		comment.setBno(5000L);
		bm.comment_insert(comment);
		
		this.comment_listTest();
		
	}
	@Test
	public void comment_updateTest() {
		log.info("comment_updateTest.............");
		
		CommentVO comment = new CommentVO();
		
		comment.setContent("comment 수정내용 10");
		comment.setId(41L);
		bm.comment_update(comment);
		
		this.comment_listTest();
	}
	
	@Test
	public void comment_deleteTest() {
		log.info("comment_deleteTest.........");
		bm.comment_delete(41L);
		
		this.comment_listTest();
	}
	
	
}
