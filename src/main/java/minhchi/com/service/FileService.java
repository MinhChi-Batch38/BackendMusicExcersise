package minhchi.com.service;

import minhchi.com.config.MulterFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileService {
       String UploadFile(File file, String fileName) throws IOException;
       File ConvertToFile(MultipartFile multipartFile, String fileName);
       String GetExtendsion(String fileName);
       String upload(MultipartFile multipartFile);
}
