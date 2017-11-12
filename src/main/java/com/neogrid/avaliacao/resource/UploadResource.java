package com.neogrid.avaliacao.resource;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neogrid.avaliacao.config.ConferenciaConfig;

@RestController
@RequestMapping("/upload")
public class UploadResource {

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> uploadFile(MultipartFile myfile) throws IOException {
		File file = new File(System.getProperty("user.dir"), ConferenciaConfig.FILE_NAME);
		if(file.exists()) file.delete();
		FileUtils.writeByteArrayToFile(file, myfile.getBytes());
		return ResponseEntity.status(200).build();
	}

}
