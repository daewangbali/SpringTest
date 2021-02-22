package kr.co.dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DbTest {
	@Autowired
	DataSource ds;
	
	@Test
	public void jdbcTest() throws Exception {
		Connection con = null; //연결
		PreparedStatement psm = null; //sql구문을 사전에 처리
		ResultSet rs = null; //결과값을 받아오는 애 
		
		//오라클연동
		String url = "jdbc:oracle:thin:@localhost:1521:system"; //자바랑 오라클을 연동하는 매개체,뭐랑 연동?,
		String id = "gjtest";
		String pw = "gjtest";
		
		Class cla = Class.forName("oracle.jdbc.OracleDriver"); //드라이버네임 체크
		log.info(cla);
		
		con = DriverManager.getConnection(url, id, pw);
		log.info(con);
		
		String sql = "select sysdate from dual"; //오늘날짜 끌어오는 것, 조건있으면 where 해서 =? 해주면 됨
		psm = con.prepareStatement(sql);
		rs = psm.executeQuery();
		
		while(rs.next()) {
			log.info(rs.getDate("sysdate")); // 필드명은 select from 가운데에 오는 것
		}
	}
	@Test
	public void hikariTest() throws Exception {
		PreparedStatement psm = null; //sql구문을 사전에 처리
		ResultSet rs = null; //
		
		Connection con = ds.getConnection();
		log.info(con);
		
		
		String sql = "select sysdate from dual"; //오늘날짜 끌어오는 것, 조건있으면 where 해서 =? 해주면 됨
		psm = con.prepareStatement(sql);
		rs = psm.executeQuery();
		
		while(rs.next()) {
			log.info(rs.getDate("sysdate")); // 필드명은 select from 가운데에 오는 것
		}
		
		/*
		String url = "jdbc:oracle:thin:@localhost:1521:system"; //자바랑 오라클을 연동하는 매개체,뭐랑 연동?,
		String id = "gjgreen";
		String pw = "gjgreen";
		
		long start = System.currentTimeMillis();
		for(int i=0;i<10;i++) {
			con = ds.getConnection();
			//con = DriverManager.getConnection(url, id, pw);
			log.info(con);
		}
		long end = System.currentTimeMillis();
		
		log.info(end - start);
		*/
		
	}
	@Autowired
	SqlSessionFactory ssf;
	
	@Test
	public void mybatisTest() throws Exception{
		SqlSession ss = ssf.openSession();
		Connection con = ss.getConnection();
		
		log.info(con);
	}
	
	
}
