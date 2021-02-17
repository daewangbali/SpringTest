package kr.co.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {
	private Long bno;
	private String title,content,writer;
	private Date regdate,updatedate;

}
