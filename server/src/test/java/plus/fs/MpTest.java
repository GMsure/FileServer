package plus.fs;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

/**
 * @author    GMsure
 * @since    2021/11/23 0023
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class MpTest {
    @Test
    public void testGen(){
        // 数据库连接的信息
        FastAutoGenerator.create("jdbc:mysql://JDBC.GMsure.plus/FileServer", "root", "54root233")
                .globalConfig(builder -> {
                    builder.author("GMsure") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\10507\\Desktop\\Code\\JAVA\\FileServer\\service\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("plus") // 设置父包名
                            .moduleName("fs") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "C:\\Users\\10507\\Desktop\\Code\\JAVA\\FileServer\\service\\src\\main\\java\\plus\\fs\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("file_info");// 设置需要生成的表名
//                            .addTablePrefix("file_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}