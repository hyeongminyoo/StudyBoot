package com.iu.home.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.iu.home.board.qna.QnaFileVO;
import com.iu.home.board.qna.QnaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileManageController {
	
	@Autowired
	private QnaService qnaService;
	
	
	@GetMapping("/fileDown/{path}") //RestFul, RestAPI
	public ModelAndView fileDown(@PathVariable String path,QnaFileVO qnaFileVO) throws Exception{
		log.info("Path : {}",path);
		ModelAndView mv = new ModelAndView();
		
		if(path.equals("qna")) {
			qnaFileVO = qnaService.getFileDetail(qnaFileVO);
			mv.addObject("fileVO",qnaFileVO);
			mv.addObject("path", path);
			mv.setViewName("fileManager");
			
		}else if(path.equals("notice")){
		
			//DB에서 파일 정보조회
			qnaFileVO.setFileName("eaabffaa-6669-4e86-845c-bd03c3d0d9f8_그레이하운드.jpg");
			qnaFileVO.setOriName("그레이하운드.jpg");
			
			mv.addObject("fileVO", qnaFileVO);
			mv.addObject("path",path);
			
			//1순위 실행 : BeanNameResolver
			// view의 이름과 일치하는 bean의 이름이 있으면 해당 bean 실행
			//2 순위 실행 : InternalResourceView Resolver
			///WEB-INF/views/fileManger.jsp
			mv.setViewName("fileManager");
		
		}
		
		return mv;
		
	}
	

	
}
