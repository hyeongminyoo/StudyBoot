package com.iu.home.board.qna;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class QnaVO {
	
	private Long num;
	@NotBlank
	private String title;
	@NotBlank
	@Size(max = 8, min = 4)
	private String writer;
	@NotBlank
	private String contents;
	private Long hit;
	private Date regDate;
	private Long ref;
	private Long step;
	private Long depth;
	
	private MultipartFile [] files;
	
	private List<QnaFileVO> qnaFileVOs;
	
}
