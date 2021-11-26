package plus.fs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import plus.fs.entity.FileInfo;
import plus.fs.entity.MyFileException;
import plus.fs.mapper.FileInfoMapper;
import plus.fs.service.IFileInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GMsure
 * @since 2021-11-24
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements IFileInfoService {
    @Value("${file.upload.path}")
    private String localFilePath;

    @Autowired
    private FileInfoMapper fileInfoMapper;

    public FileInfoServiceImpl(FileInfoMapper fileInfoMapper) {
        this.fileInfoMapper = fileInfoMapper;
    }

    /**
     *
     * @param file
     * @return
     */
    @Override
    public FileInfo uploadFile(MultipartFile file) throws MyFileException {
        Boolean failFlag = false;
        //首先判断目标文件夹是否存在
        File parentDir = new File(localFilePath);
        //不存在则创建对应目标文件夹
        if(!parentDir.exists()) {
            failFlag |= parentDir.mkdir();
            if(failFlag){
                throw new MyFileException("文件目录创建失败");
            }
        }
        //判断对应日期的文件夹是否存在
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        File dateDir = new File(localFilePath + "\\" + date);
        if (!dateDir.exists()) {
            failFlag |= dateDir.mkdir();
            if(failFlag){
                throw new MyFileException("文件目录创建失败");
            }
        }

        //文件目录创建失败处理


        //得到新的文件名（带路径）
        String fileUid = UUID.randomUUID().toString();
        System.out.println(file.getOriginalFilename());
        String[] nameSplit = file.getOriginalFilename().split("\\.");
        String orgFileName = nameSplit[0];
        String fileType = nameSplit[nameSplit.length - 1];//得到后缀名
        String fileName = dateDir + "\\" + fileUid + "." + fileType;

        //如果存在进行覆盖
        failFlag = false;
        File newFile = new File(fileName);
        if(newFile.exists()) {
            failFlag |= newFile.delete();
            if(failFlag){//文件覆写失败处理
                throw new MyFileException("同名文件覆写失败");
            }

        }


        //创建输入输出流
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(file.getInputStream());
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName));
            byte[] buff = new byte[1024];
            int len = 0;
            while((len = bufferedInputStream.read(buff)) != -1) {
                bufferedOutputStream.write(buff, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭输入输出流
            try {
                assert bufferedInputStream != null;
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert bufferedOutputStream != null;
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new FileInfo(orgFileName, fileUid, String.valueOf(file.getSize()), fileType, new Date(), localFilePath + "\\" + date);
    }

    /**
     * 保存文件元信息
     * @param fileInfo
     * @return
     */
    @Override
    public Integer saveFileInfo(FileInfo fileInfo) {
        return fileInfoMapper.saveFileInfo(fileInfo);
    }

    /**
     * 获取指定Uid的文件信息
     * @param fileUid
     * @return
     */
    @Override
    public FileInfo getFileInfo(String fileUid) {
        return fileInfoMapper.getFileInfo(fileUid);
    }

    /**
     * 根据Uid获取指定文件流
     * @param fileUid
     * @return
     */
    @Override
    public FileInputStream getFileStream(String fileUid) throws FileNotFoundException {
        //根据uuid查询对应的元信息
        FileInfo fileInfo = fileInfoMapper.getFileInfo(fileUid);
        //文件不存在 主动抛出异常
        if (fileInfo == null) {
            throw new FileNotFoundException();
        }
        String infoFilePath = fileInfo.getFilePath();
        String infoFileType = fileInfo.getFileType();
        //指定的文件位置
        String localFilePath = infoFilePath + "\\" + fileUid + "." + infoFileType;
        //获取输入流
        return new FileInputStream(localFilePath);
    }
}
