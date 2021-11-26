package plus.fs.entity;

/**
 * @author GMsure
 * @since 2021/11/25 0025 22:59
 */
public class MyFileException extends Exception{

    /**
     * 自定义文件处理异常
     * @param message
     */
    public MyFileException(String message) {
        super( "LocalFile Handling Exceptions : " + message);
    }
}
