package com.iu.home.board.qna;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.home.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private QnaService qnaService;
	@Autowired
	private QnaMapper qnaMapper;
	
	@GetMapping("hack")
	@ResponseBody
	public int hack(QnaVO qnaVO) throws Exception {
		qnaMapper.setAdd(qnaVO);
		return 1;
	}
	
	
	
	@GetMapping("list")
	public ModelAndView getList(Pager pager,ModelAndView mv) throws Exception{
		List<QnaVO> ar = qnaService.getList(pager);
		mv.addObject("list", ar);
		mv.setViewName("board/list");
		
		return mv;
	}
	
	@GetMapping("add")
	public ModelAndView setAdd(ModelAndView mv,@ModelAttribute QnaVO qnaVO) throws Exception{
		mv.setViewName("board/add");
		
		return mv;
	}
	
	@PostMapping("add")
	public String setAdd(@Valid QnaVO qnaVO,BindingResult bindingResult ,RedirectAttributes redirectAttributes) throws Exception{
		
		if(bindingResult.hasErrors()) {
			log.info("=========== QNA add 검증 ===========");
			return "board/add";
		}
		
//		int result = qnaService.setAdd(qnaVO);
//		redirectAttributes.addAttribute("result", result);
		
		return "redirect:./list";
	}
	
	@GetMapping("detail")
	public ModelAndView getDetail(QnaVO qnaVO,ModelAndView mv) throws Exception{
		qnaVO = qnaService.getDetail(qnaVO);
		mv.addObject("vo", qnaVO);
		mv.setViewName("board/detail");
		return mv;
	}
	
	@GetMapping("update")
	public ModelAndView setUpdate(QnaVO qnaVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.getDetail(qnaVO);
		mv.addObject("vo", qnaVO);
		mv.setViewName("board/update");
		return mv;
	}
	
	@PostMapping("fileDelete")
	@ResponseBody
	public int setFileDelete(QnaFileVO qnaFileVO) throws Exception{
		int result = qnaService.setFileDelete(qnaFileVO);
		return result;
	}
	
}
