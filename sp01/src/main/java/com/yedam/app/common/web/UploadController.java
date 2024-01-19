package com.yedam.app.common.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	// 환경변수로 등록되어있는 대상을 불러옴 > uploadPath 여기에 넣는대
	@Value("${file.upload.path}")
	private String uploadPath;

	// 페이지를 불러옴
	@GetMapping("upload")
	public void getUploadPath() {
	}

	// 아작스를 부름
	@PostMapping("/uploadsAjax")
	@ResponseBody // @RequestPart 얘는 multipart/form-data를 요구함
	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles) {

		List<String> imageList = new ArrayList<>();

		for (MultipartFile uploadFile : uploadFiles) {
			if (uploadFile.getContentType().startsWith("image") == false) { // startsWith :이미지에 대해서만 처리하겠다
				System.err.println("this file is not image type");
				return null;
			}

			String originalName = uploadFile.getOriginalFilename();
			String fileName = originalName.substring(originalName.lastIndexOf("//") + 1); // 파일이름 잘라내는 코드

			System.out.println("fileName : " + fileName); // 파일명만 끄집어냄

			// 날짜 폴더 생성
			String folderPath = makeFolder();
			// UUID
			String uuid = UUID.randomUUID().toString(); // 유니크한 값
			// 저장할 파일 이름 중간에 "_"를 이용하여 구분

			String uploadFileName = folderPath + File.separator + uuid + "_" + fileName;

			String saveName = uploadPath + File.separator + uploadFileName;

			Path savePath = Paths.get(saveName);
			// Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
			System.out.println("path : " + saveName);
			try {
				uploadFile.transferTo(savePath);
				// uploadFile에 파일을 업로드 하는 메서드 transferTo(file) //실제 파일이 저장되는 구간 MultipartFile이
				// 가지고있는 transferTo //물리적 위치에 임시 저장된 파일을 읽어서...........파일을 복사..?
			} catch (IOException e) {
				e.printStackTrace();
			}
			// DB에 해당 경로 저장
			// 1) 사용자가 업로드할 때 사용한 파일명
			// 2) 실제 서버에 업로드할 때 사용한 경로
			imageList.add(setImagePath(uploadFileName));
		}

		return imageList;
	}

	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator); // 다시 문자열로 돌림
		File uploadPathFolder = new File(uploadPath, folderPath); // File 클래스 .
		// File newFile= new File(dir,"파일명");
		if (uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}

	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}
}