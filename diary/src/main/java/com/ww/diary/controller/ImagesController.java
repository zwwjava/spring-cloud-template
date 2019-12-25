package com.ww.diary.controller;


import com.ww.diary.dto.MessageResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.ww.diary.controller.BaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;

/**
 * <p>
 * 图片信息表 前端控制器
 * </p>
 *
 * @author zww
 * @since 2019-12-23
 */
@RestController
@RequestMapping("/image")
@Slf4j
public class ImagesController extends BaseController {

    @Value("${baseUrl}")
    private String baseUrl;

    /**
     * 上传图片
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    @ResponseBody
    public MessageResp upload(@RequestParam(value="file")MultipartFile file) throws IOException {
        log.info("文件名称：" + file.getName());
        String fileName = file.getOriginalFilename();//获取文件名加后缀
        if (StringUtils.isNotEmpty(fileName)) {
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            fileName = System.currentTimeMillis() + fileF;//新的文件名
            File fileSystem = new File(baseUrl + fileName);
            file.transferTo(fileSystem);
        }
        return success(fileName);
    }

    /**
     * 下载图片
     * @param response
     * @param imageUrl
     * @throws IOException
     */
    @RequestMapping(value = "/preview/{imageUrl}", method = RequestMethod.GET)
    @ResponseBody
    public void download(HttpServletResponse response, @PathVariable String imageUrl) throws IOException {
        String fileName = baseUrl + imageUrl;//文件名
        File file = new File(fileName);
        fileOutput(response, file);
    }

    protected void fileOutput(HttpServletResponse resp, File file) {
        if (file != null) {
            OutputStream os = null;
            setDownload(resp, file.getName());
            try {
                os = resp.getOutputStream();
                os.write(Files.readAllBytes(file.toPath()));
                resp.flushBuffer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (os != null) {
                    try {
                        os.close();
                        log.info("下载文件，写文件成功");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private void setDownload(HttpServletResponse resp, String fileName) {
        resp.setContentType("image/jpeg");
        resp.setCharacterEncoding("utf-8");
    }

}

