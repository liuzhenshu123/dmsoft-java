package cn.com.qjun.dmsoft.utils;

import cn.com.qjun.dmsoft.domain.Point;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 信息解析工具
 *
 * @author RenQiang
 * @date 2024/2/14
 */
public class InfoParseUtils {

    /**
     * 字符串解析成坐标
     *
     * @param str
     * @param separator
     * @return
     */
    public static Point parsePoint(String str, String separator) {
        String[] parts = str.split(separator);
        return Point.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    /**
     * 分割字符串
     *
     * @param str       要分割的字符串
     * @param regex 分隔符
     * @return 分割结果
     */
    public static List<String> splitString(String str, String regex) {
        if (str == null || str.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(str.split(regex))
                .collect(Collectors.toList());
    }
}
