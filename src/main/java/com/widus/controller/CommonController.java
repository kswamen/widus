package com.widus.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/common/*")
public class CommonController {
	private String uploadPath = "C:";
	
	@RequestMapping(value = "ckUpload", method = RequestMethod.POST)
	@ResponseBody
	public void ckUpload(HttpServletRequest req, HttpServletResponse res, @RequestParam MultipartFile upload, 
			@RequestParam(value = "responseType", required = false) String responseType, 
			@RequestParam(value = "type", required = false) String type) throws Exception {
		UUID uid = UUID.randomUUID();
		OutputStream out = null;
		PrintWriter printWriter = null;
		
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		try {
			String fileName = upload.getOriginalFilename();
			System.out.println(fileName);
			byte[] bytes = upload.getBytes();
			
			String ckUploadPath = uploadPath + File.separator + "ckUpload" + File.separator + uid + "_" + fileName;
			out = new FileOutputStream(new File(ckUploadPath));
			out.write(bytes);
			out.flush();
			
			String callback = req.getParameter("CKEditorFuncNum");
			printWriter = res.getWriter();
			String fileUrl = "/common/ckDownload/" + uid + "_" + fileName;

			printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");

			// Toolbar image button
//			else {
//				printWriter.println("<script type='text/javascript'>"
//						+ "window.parent.CKEDITOR.tools.callFunction("
//						+ callback + ",'" + fileUrl + "', '이미지를 업로드했습니다.')"
//						+ "</script>");
//			}
			
			printWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null) {
					out.close();
				}
				if(printWriter != null) {
					printWriter.close();
				} 
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}
	
	@GetMapping(value = "/ckDownload/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> ckDownload(@PathVariable("fileName") String fileName) {
		try {
			final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get("C:\\ckUpload\\" + fileName)));
			return ResponseEntity
					.status(HttpStatus.OK)
					.contentLength(inputStream.contentLength())
					.body(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
