package com.iu.home.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.iu.home.board.qna.QnaFileVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileManageController {
	
	@GetMapping("/fileDown/{path}") //RestFul, RestAPI
	public ModelAndView fileDown(@PathVariable String path,QnaFileVO qnaFileVO) throws Exception{
		log.info("Path : {}",path);
		ModelAndView mv = new ModelAndView();
		
		//DB에서 파일 정보조회
		qnaFileVO.setFileName("eaabffaa-6669-4e86-845c-bd03c3d0d9f8_그레이하운드.jpg");
		qnaFileVO.setOriName("그레이하운드.jpg");
		
		mv.addObject("fileVO", qnaFileVO);
		mv.addObject("path",path);
		mv.setViewName("fileManager");
		
		return mv;
		
	}
	

	
}
