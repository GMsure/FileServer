package plus.fs.service;

import org.springframework.web.multipart.MultipartFile;
import plus.fs.entity.FileInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import plus.fs.entity.MyFileException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GMsure
 * @since 2021-11-24
 */
public interface IFileInfoService extends IService<FileInfo> {

    FileInfo uploadFile(MultipartFile file) throws MyFileException;

    Integer saveFileInfo(FileInfo fileInfo);

    FileInfo getFileInfo(String fileUid);

    FileInputStream getFileStream(String fileUid) throws FileNotFoundException;
}
