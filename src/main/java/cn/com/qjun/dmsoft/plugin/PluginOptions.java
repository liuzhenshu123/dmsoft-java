package cn.com.qjun.dmsoft.plugin;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * 插件配置
 *
 * @author RenQiang
 * @date 2024/2/11
 */
public class PluginOptions {
    /**
     * 工作目录
     */
    private String workDir;
    /**
     * 注册码
     */
    private String regCode;
    /**
     * 附加码
     */
    private String addCode;

    /**
     * 字库信息
     * 因为字库只需要全局初始化调用一次，所以在创建DmSoft实例时进行调用
     */
    @Getter
    @AllArgsConstructor
    public static class DmDicts {
        /**
         * 字库密码
         */
        private String password;
        /**
         * 字库序号和对应文件
         */
        private Map<Integer, String> dicts;
    }
}
