package com.spring.employee.fileUpload2;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class FileuploadController  {
    @PostMapping(value = "/uploads",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object>uploadFile(@RequestParam(required = true,value = "file")MultipartFile file,@RequestParam(required=true,value
    ="jsondata")String jsondata)   {
        File convertfile =new File("C:\\Users\\Admin\\Downloads"+file.getOriginalFilename()) ;
        try {
            convertfile.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(convertfile);
            outputStream.write(file.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

return new ResponseEntity<>("file has uploaded succcsessfully", HttpStatus.OK);
    }





}
