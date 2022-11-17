package minhchi.com.controllers;

import minhchi.com.service.FileService;
import minhchi.com.service.impl.FileServiceImpl;


import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "http://localhost:3000")
@EnableJpaRepositories
@RestController
public class FileController {
    private FileService fileService = new FileServiceImpl();
    //private Logger logger;

    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestParam("file") MultipartFile file) {
        //logger.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());
        return fileService.upload(file);
    }

}
