package plus.fs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import plus.fs.entity.FileInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GMsure
 * @since 2021-11-24
 */
@Repository
public interface FileInfoMapper extends BaseMapper<FileInfo> {
    //保存文件信息
    int saveFileInfo(FileInfo fileInfo);

    //根据UUID查询文件元信息
    FileInfo getFileInfo(String fileUid);
}
