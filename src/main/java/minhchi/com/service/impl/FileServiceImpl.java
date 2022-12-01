package minhchi.com.service.impl;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import minhchi.com.service.FileService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

import static minhchi.com.utils.Constant.DOWNLOAD_URL;
import static minhchi.com.utils.Constant.JSON_PATH;

public class FileServiceImpl implements FileService {
    @Override
    public String UploadFile(File file, String fileName) throws IOException{
        ClassPathResource serviceAccount = new ClassPathResource("path.json");
        System.out.println(GetExtendsion((fileName)));
        if (GetExtendsion(fileName).contains("mp3")) {
            BlobId blobId = BlobId.of("music-d3f39.appspot.com", fileName);
            System.out.println("OK");
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
            Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(serviceAccount.getFile()));
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
            storage.create(blobInfo, Files.readAllBytes(file.toPath()));
            return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        }
        return "404";
    }

    @Override
    public File ConvertToFile(MultipartFile multipartFile, String fileName) {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tempFile;
    }

    @Override
    public String GetExtendsion(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    @Override
    public String upload(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.GetExtendsion(fileName));  // to generated random string values for file name.

            File file = this.ConvertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String TEMP_URL = this.UploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return TEMP_URL;                    // Your customized response
        } catch (Exception e) {
            e.printStackTrace();
            return  "Unsuccessfully Uploaded!";
        }
    }

    @Override
    public boolean delete(String url)  throws IOException{
        //project-id = "music-d3f39"
        //ClassPathResource serviceAccount = new ClassPathResource("path.json");
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(JSON_PATH));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get("music-d3f39.appspot.com", url);
        if (blob == null) {
            System.out.println("The object " + url + " wasn't found in music-d3f39.appspot.com");
            return false;
        }

        // Optional: set a generation-match precondition to avoid potential race
        // conditions and data corruptions. The request to upload returns a 412 error if
        // the object's generation number does not match your precondition.
        Storage.BlobSourceOption precondition =
                Storage.BlobSourceOption.generationMatch(blob.getGeneration());

        storage.delete("music-d3f39.appspot.com", url, precondition);

        System.out.println("Object " + url + " was deleted from " + "music-d3f39.appspot.com");
        return true;
    }
}
