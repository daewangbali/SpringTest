package kr.co.dbtest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import lombok.extern.log4j.Log4j2;

@Log4j2
@WebAppConfiguration //서블렉콘텍스트정보저장됨 여기에
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BoardControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mock;
	
	@Before
	public void setup() {
		this.mock = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void listTest() throws Exception {
		log.info("listTest...........");
		
		RequestBuilder url = MockMvcRequestBuilders.get("/board/list");
		
		log.info(mock.perform(url).andReturn().getModelAndView());
	
	}
	
	@Test 
	public void registerTest() throws Exception {
		log.info("registerTest...........");
		
		RequestBuilder url = MockMvcRequestBuilders.post("/board/register")
				.param("title", "control 제목 200")
				.param("content", "control 내용 200")
				.param("writer", "control 작성자 200");
		
		log.info(mock.perform(url).andReturn().getModelAndView());
	}
	
	@Test
	public void getTest() throws Exception {
		log.info("getTest...............");
		
		RequestBuilder url = MockMvcRequestBuilders.get("/board/get")
				.param("bno","1");
		
		log.info(mock.perform(url).andReturn().getModelAndView());
	}
	
	@Test
	public void modifyTest() throws Exception {
		log.info("modifyTest...............");
		
		RequestBuilder url = MockMvcRequestBuilders.post("/board/modify")
				.param("title", "control 수정제목 200")
				.param("content","control 수정내용 200")
				.param("bno", "15");
		
		log.info(mock.perform(url).andReturn().getModelAndView());
		
	}
	
	@Test
	public void removeTest() throws Exception{
		log.info("removeTest...............");
		
		RequestBuilder url = MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "22");
		
		log.info(mock.perform(url).andReturn().getModelAndView());
		
	}
	
	@Test
	public void searchTest() throws Exception {
		log.info("searchTest...............");
		RequestBuilder url = MockMvcRequestBuilders.get("/board/list")
				.param("type", "TCW")
				.param("keyword", "test");
		
		log.info(mock.perform(url).andReturn().getModelAndView());
	}
	
	@Test
	public void comment_get_listTest() throws Exception {
		log.info("comment_get_listTest...........");
		RequestBuilder url = MockMvcRequestBuilders.get("/board/comment/comment_get_list")
				.param("bno", "5000");
		log.info(mock.perform(url).andReturn().getModelAndView());
	}
	
	//0304추가
	
	@Test
	public void comment_registerTest() throws Exception {
		log.info("comment_registerTest.............");
		RequestBuilder url = MockMvcRequestBuilders.post("/board/comment/comment_register")
				.param("writer", "작성자 comment 0304")
				.param("content", "내용 comment 0304")
				.param("bno", "5000");
		log.info(mock.perform(url).andReturn().getModelAndView());
	}
	
	@Test
	public void comment_modifyTest()throws Exception {
		log.info("comment_modifyTest.............");
		RequestBuilder url = MockMvcRequestBuilders.post("/board/comment/comment_modify")
				.param("content", "내용수정 comment 0305")
				.param("id", "43")
				.param("bno", "5000");
		log.info(mock.perform(url).andReturn().getModelAndView());
		
	}
	
	@Test
	public void comment_removeTest() throws Exception {
		log.info("comment_deleteTest.............");
		RequestBuilder url = MockMvcRequestBuilders.post("/board/comment/comment_remove")
				.param("id","43")
				.param("bno", "5000");
		log.info(mock.perform(url).andReturn().getModelAndView());
	}
	
}