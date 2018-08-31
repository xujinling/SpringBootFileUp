package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUpController {

	Logger log=LoggerFactory.getLogger(FileUpController.class);
	
	@RequestMapping(value="/file")
	public String toFile(){
		return "file";
	}
	
	@RequestMapping(value="/fileUp",method=RequestMethod.POST)
	@ResponseBody
	public String showFileUp(@RequestParam("fileName") MultipartFile file) throws IllegalStateException, IOException{
		if(file==null){
			return null;
		}
		String fileName=file.getOriginalFilename();
		log.info("-------fileName:--------"+file.getOriginalFilename());
		
		String savePath="C:\\Users\\Administrator\\Desktop\\南昌恒邦\\"+fileName;
		
		File file_a=new File(savePath);
		
		if(!file_a.getParentFile().exists()){
			file_a.getParentFile().mkdir();
		}
	
		file.transferTo(file_a);
		
		return "success";
	}
	
	
	@RequestMapping(value="/downFile")
	public String toDownFile(){
		return "downFile";
	}
	
	@RequestMapping(value="/fileDown")
	@ResponseBody
	public String toFileDown(HttpServletResponse response) throws IOException{
		String fileName="work-1.xlsx";//要下载的文件名
		String path="C:\\Users\\Administrator\\Desktop\\";//要下载文件所在路径
		
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		//根据路径读取文件
		File file=new File(path+fileName);
		
		InputStream is=new FileInputStream(file);
		
		/*//下载的文件要保存的路径
		file=new File("C:\\Users\\Administrator\\Desktop\\南昌恒邦\\"+fileName);
		//判断路径是否存在
		if(!file.getParentFile().exists()){
			file.mkdirs();
		}*/
		
		
		//OutputStream os=new FileOutputStream(file);
		OutputStream os=response.getOutputStream();
		
		int temp;
		while((temp=is.read())!=-1){
			os.write(temp);
		}
		
		os.close();
		is.close();
		
		return "success";
	}
	
	
	
	
	
	
	
	
	
}
