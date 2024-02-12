package cn.com.qjun.dmsoft.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 尺寸大小
 *
 * @author RenQiang
 * @date 2024/2/11
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Size {
    /**
     * 宽度
     */
    private int width;
    /**
     * 高度
     */
    private int height;

    public static Size of(int width, int height) {
        return new Size(width, height);
    }
}
