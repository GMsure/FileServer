package plus.fs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author GMsure
 * @since 2021-11-24
 */
@TableName("file_info")
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * UUID
     */
    @TableId
    private String fileUid;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件创建时间
     */
    private Date fileCreatTime;

    /**
     * 文件保存目录地址
     */
    private String filePath;

    public FileInfo() {}

    public FileInfo(String fileName, String fileUid, String fileType, String filePath) {
        this.fileName = fileName;
        this.fileUid = fileUid;
        this.fileType = fileType;
        this.filePath = filePath;
    }

    public FileInfo(String fileName, String fileUid, String fileSize, String fileType, Date fileCreatTime, String filePath) {
        this.fileName = fileName;
        this.fileUid = fileUid;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.fileCreatTime = fileCreatTime;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileUid() {
        return fileUid;
    }

    public void setFileUid(String fileUid) {
        this.fileUid = fileUid;
    }
    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public Date getFileCreatTime() {
        return fileCreatTime;
    }

    public void setFileCreatTime(Date fileCreatTime) {
        this.fileCreatTime = fileCreatTime;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
            "fileName=" + fileName +
            ", fileUdi=" + fileUid +
            ", fileSize=" + fileSize +
            ", fileType=" + fileType +
            ", fileCreatTime=" + fileCreatTime +
            ", filePath=" + filePath +
        "}";
    }
}
