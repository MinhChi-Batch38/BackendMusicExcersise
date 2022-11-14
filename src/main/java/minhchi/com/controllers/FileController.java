package minhchi.com.controllers;

import minhchi.com.service.FileService;
import minhchi.com.service.impl.FileServiceImpl;


import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile) {
        //logger.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());
        return fileService.upload(multipartFile);
    }

}
