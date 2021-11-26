package plus.fs.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import plus.fs.entity.FileInfo;
import plus.fs.entity.MyFileException;
import plus.fs.service.IFileInfoService;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GMsure
 * @since 2021-11-24
 */
@RestController
@RequestMapping("/fs/file")
//@CrossOrigin(allowedHeaders = "*",value = "*",origins = "*",methods = {RequestMethod.GET, RequestMethod.POST},maxAge = 3600)
@CrossOrigin(origins = "*",maxAge = 3600)
public class FileInfoController {
    private Logger logger = LoggerFactory.getLogger(FileInfoController.class);//引入日志

    private final IFileInfoService fileInfoService;

    public FileInfoController(IFileInfoService fileInfoService) {
        this.fileInfoService = fileInfoService;
    }

    /**
     * 上传文件接口：将文件保存到服务器，并将数据的元信息保存到数据库
     * @param file
     * @return
     */
    @RequestMapping("/upload")
//    @Transactional //事务启用需要抛出异常，已经自行捕获，暂时不采用事务处理
    @CrossOrigin(allowedHeaders = "*",value = "*",origins = "*",methods = {RequestMethod.GET, RequestMethod.POST},maxAge = 3600)
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        //将数据保存到服务器
        FileInfo fileInfo = null;
        try {
            fileInfo = fileInfoService.uploadFile(file);
        } catch (MyFileException e) {
            logger.warn(e.getMessage() + "\t 文件上传出现异常");
            e.printStackTrace();

            return "410";
        }

        //将对应信息存进数据库
        int result = fileInfoService.saveFileInfo(fileInfo);
        if (result > 0) {
            logger.info("成功上传文件 : " + fileInfo);
            return fileInfo.getFileUid();
        } else {
            logger.warn("上传文件失败");
            return "410";
        }
    }


    /**
     * 下载文件接口：根据传入的uuid下载对应的文件
     * @param uuid fileUid
     * @param response
     * @return
     */
    @RequestMapping("/download")
    @CrossOrigin(allowedHeaders = "*",value = "*",origins = "*",methods = {RequestMethod.GET, RequestMethod.POST},maxAge = 3600)
    public String downloadFile(@RequestParam("uuid") String uuid, HttpServletResponse response) {
        //设置字符集
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        //根据uuid获取到对应文件信息
        FileInfo fileInfo = fileInfoService.getFileInfo(uuid);
        if (fileInfo == null) {
            return "410";//响应异常状态码
        }

        try {
            //文件存在 进行下载
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; fileName="
                    + fileInfo.getFileName() + "." + fileInfo.getFileType());
            System.out.println(fileInfo.getFileName());
            FileInputStream fileInputStream = fileInfoService.getFileStream(uuid);

            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            int len = -1;
            byte[] buff = new byte[1024];
            while((len = bufferedInputStream.read(buff))!= -1) {
                bufferedOutputStream.write(buff, 0, len);
            }
            logger.info("文件下载成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(410);
            logger.error("不存在对应文件，文件下载失败");
        } finally {
            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "ok";
    }

    /**
     * 获取文件元数据
     * @param uuid fileUId
     * @return
     */
    @RequestMapping("/getInfo")
    public FileInfo getFileInfo(@RequestParam("uuid") String uuid) {
        FileInfo fileInfo = fileInfoService.getFileInfo(uuid);
        logger.info("查询文件元数据 : " + fileInfo);
        return fileInfo;
    }

}
