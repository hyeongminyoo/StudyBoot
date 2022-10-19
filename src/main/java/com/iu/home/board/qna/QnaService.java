package com.iu.home.board.qna;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.home.util.FileManager;
import com.iu.home.util.Pager;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	@Value("${app.upload.qna}")
	private String path;
	@Autowired
	private FileManager fileManager;
	
	public List<QnaVO> getList(Pager pager) throws Exception{
		Long totalCount = qnaMapper.getCount(pager);
		pager.getNum(totalCount);
		
		pager.getStartRow();
		
		return qnaMapper.getList(pager);
	}
	
	public int setAdd(QnaVO qnaVO) throws Exception{
		
		int result = qnaMapper.setAdd(qnaVO);
		
		java.io.File file = new java.io.File(path);
		if(!file.exists()) {
			boolean check = file.mkdirs();
		}
		
		
		for(MultipartFile f : qnaVO.getFiles()) {
			if(!f.isEmpty()) {
				log.info("FileName : {}",f.getOriginalFilename());
				String fileName = fileManager.saveFile(f, path);
				QnaFileVO qnaFileVO = new QnaFileVO();
				qnaFileVO.setNum(qnaVO.getNum());
				qnaFileVO.setFileName(fileName);
				qnaFileVO.setOriName(f.getOriginalFilename());
				qnaMapper.setFileAdd(qnaFileVO);
			}
			
		}
		
		return result;
	}
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception{
		return qnaMapper.getDetail(qnaVO);
	}
	
}
