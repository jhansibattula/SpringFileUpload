package com.spring.employee.FileUpload;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
public class FileUploadCuntroller {

    private static String UPLOAD_DIR ="C:\\Users\\Admin\\Downloads";

    @PostMapping("/upload")
    public String uploadfile(@RequestParam("file")MultipartFile file, HttpServletRequest request ) throws IOException {
        try {
            String filename = file.getOriginalFilename();
            String path = request.getServletContext().getRealPath("") + UPLOAD_DIR + File.separator + filename;
            savefile(file.getInputStream(), path);
            return filename;
        } catch (Exception e) {
            e.printStackTrace();

            return e.getMessage();
        }
    }


    private   void savefile(InputStream inputStream,String path){
        try{
            OutputStream outputStream=new FileOutputStream(new File(path));
            int read=0;
            byte[] bytes=new byte[1024];
            while ((read=inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,read);
            }
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


