package com.iu.home.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iu.home.board.qna.QnaFileVO;

@Controller
public class FileManageController {
	
	@GetMapping("/fileDown/qna")
	public ModelAndView fileDown(QnaFileVO qnaFileVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		//DB에서 파일 정보조회
		qnaFileVO.setFileName("eaabffaa-6669-4e86-845c-bd03c3d0d9f8_그레이하운드.jpg");
		qnaFileVO.setOriName("그레이하운드.jpg");
		
		mv.addObject("fileVO", qnaFileVO);
		
		mv.setViewName("fileManager");
		
		return mv;
		
	}
	
}
