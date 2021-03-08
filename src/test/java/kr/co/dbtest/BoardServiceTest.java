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
import kr.co.domain.PageDTO;
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
		vo.setTitle("새글제목 registerTest 10");
		vo.setContent("새글제목 registerTest 10");
		vo.setWriter("작성자 registerTest 10");
		vo.setFilename("새글파일 registerTest 10");
		vo.setFilepath("새글경로 registerTest 10");
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
		vo.setFilename("새글파일 registerTest 10");
		vo.setFilepath("새글경로 registerTest 10");
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
		vo.setFilename("수정파일 modifyTest 10");
		vo.setFilepath("수정경로 modifyTest 10");
		vo.setBno(15445L);
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
	
	@Test
	public void getListPageTest() {
		log.info("getListPageTest.....................");
		Criteria cri = new Criteria(2,15);
		List<BoardVO> vo = bs.getList(cri);
		
		for(BoardVO boardVO : vo) {
			log.info(boardVO);
		}
	}
	
	@Test
	public void pageDTOTest() {
		log.info("pageDTOTest.....................");
		
		Criteria cri = new Criteria(35,10);
		PageDTO page = new PageDTO(cri, 2500L);
		
		log.info(page);
	}
	
	@Test
	public void searchTest() {
		log.info("searchTest..............");
		Criteria cri = new Criteria();
		
		cri.setType("TCW");
//		cri.setType("C");
		cri.setKeyword("test");
//		cri.setKeyword("내용");
		
		List<BoardVO> list = bs.getList(cri);
		
		log.info("totalcount : "+bs.getTotalCount(cri));
		
		for(BoardVO boardVO : list) {
			log.info(boardVO);
		}
	}
	
	//20210303 추가
		@Test
		public void comment_registerTest() {
			log.info("comment_registerTest.................");
			
			CommentVO comment = new CommentVO();
			
			comment.setWriter("commnet 작성자 10");
			comment.setContent("comment 내용 10");
			comment.setBno(5000L);
			bs.comment_register(comment);
			
			this.comment_get_listTest();
		}
		
		@Test
		public void comment_get_listTest() {
			log.info("comment_getlistTest.................");
			List<CommentVO> list = bs.comment_get_list(5000L);
			for(CommentVO commentVO : list) {
				log.info(commentVO);
			}
			
		}
		
		@Test
		public void comment_modifyTest() {
			log.info("comment_modifyTest.................");
			
			CommentVO comment = new CommentVO();
			
			comment.setContent("comment 수정내용 100000");
			comment.setId(42L);
			bs.comment_modify(comment);
			
			this.comment_get_listTest();
			
		}
		
		@Test
		public void comment_removeTest() {
			log.info("comment_removeTest.................");
			bs.comment_remove(42L);
			
			this.comment_get_listTest();
			
		}
	
	
	

}
