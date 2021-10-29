package com.huaiwei.jdbctempalte.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
@Controller
public class FileHandler {

    @ResponseBody
    @PostMapping(value = "/upload",consumes = {"multipart/form-data"})
    public String handleFileUpload(@RequestParam("file") MultipartFile[] file) throws Exception {
        OutputStream out = null;
        InputStream in = null;
        byte[] buf = new byte[1024];
        for (int i = 0; i < file.length; i++) {
            File f = new File("E:\\上传文件测试\\" + file[i].getOriginalFilename());
            log.info(f.getName());
            in = file[i].getInputStream();
            out = new FileOutputStream(f);
            while (in.read(buf) != -1) {
                out.write(buf, 0, buf.length);
            }
        }
        assert in != null;
        in.close();
        out.close();
        return "上传成功";
    }
}
