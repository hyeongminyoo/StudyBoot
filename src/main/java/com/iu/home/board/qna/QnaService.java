package com.iu.home.board.qna;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.home.util.FileManager;
import com.iu.home.util.Pager;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	@Value("${app.upload.qna}")
	private String path;
	@Autowired
	private FileManager fileManager;

	
	public boolean setSummerFileDelete(String fileName) throws Exception{
		fileName = fileName.substring(fileName.lastIndexOf("/"));
		QnaFileVO qnaFileVO = new QnaFileVO();
		qnaFileVO.setFileName(fileName);
		boolean result = fileManager.deleteFile(qnaFileVO, path);
		
		return result;
	}
	
	
	public String setSummerFile(MultipartFile file) throws Exception{
		
		String fileName = fileManager.saveFile(file, path);
		
		fileName = "/file/qna/"+fileName;
		
		
		return fileName;
	}
	
	public List<QnaVO> getList(Pager pager) throws Exception{
		Long totalCount = qnaMapper.getCount(pager);
		pager.getNum(totalCount);
		
		pager.getStartRow();
		
		return qnaMapper.getList(pager);
	}
	
	public int setAdd(QnaVO qnaVO) throws Exception{
		
		int result = qnaMapper.setAdd(qnaVO);
		
		File file = new File(path);
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
	
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO) throws Exception{
		return qnaMapper.getFileDetail(qnaFileVO);
	}
	
	public int setFileDelete(QnaFileVO qnaFileVO) throws Exception{
		
		//1. fileNum 조회
		qnaFileVO = qnaMapper.getFileDetail(qnaFileVO);
		
		//2. DB 삭제
		int result = qnaMapper.setFileDelete(qnaFileVO);
		
		//3. DB에서 잘 지워졌으면 로컬에서 삭제
		if(result == 1) {
			boolean fileResult = fileManager.deleteFile(qnaFileVO, path);
			log.info("fileResult {}", fileResult);
		}
		
		
		return result;
	}
}
