package cn.com.qjun.dmsoft.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author RenQiang
 * @date 2023/1/10
 */
//@Slf4j
public class RuntimeUtils {

    /**
     * 拷贝资源文件到指定文件
     *
     * @param sourceFilePath 资源文件路径
     * @param targetFilePath 目标文件路径
     */
    public static void copyResourceFile(String sourceFilePath, Path targetFilePath) throws IOException {
        try (InputStream input = RuntimeUtils.class.getResourceAsStream(sourceFilePath)) {
            if (input != null) {
                Files.copy(input, targetFilePath, StandardCopyOption.REPLACE_EXISTING);
            } else {
                throw new RuntimeException("Cloud not find resource file : " + sourceFilePath);
            }
        }
    }
}
