package cn.com.qjun.dmsoft;

import cn.com.qjun.dmsoft.utils.InfoParseUtils;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件操作
 *
 * @author RenQiang
 * @date 2024/2/14
 */
@RequiredArgsConstructor
public class FileOperations {
    private final DmSoft dmSoft;

    /**
     * 解密指定的文件.
     *
     * @param file 文件名.
     * @param pwd  密码.
     */
    public void decodeFile(String file, String pwd) {
        dmSoft.callAndCheckResultEq1("DecodeFile", file, pwd);
    }

    /**
     * 删除指定的ini小节.
     * <p>
     * 注 : 此函数是多线程安全的. 多线程同时读写同个文件不会造成文件错乱.
     *
     * @param section 小节名
     * @param key     变量名. 如果这个变量为空串，则删除整个section小节.
     * @param file    ini文件名.
     */
    public void deleteIni(String section, String key, String file) {
        dmSoft.callAndCheckResultEq1("DeleteIni", section, key, file);
    }

    /**
     * 删除指定的ini小节.支持加密文件
     * <p>
     * 注 : 此函数是多线程安全的. 多线程同时读写同个文件不会造成文件错乱. 但是多进程是不安全的,要避免多进程同时使用此接口,否则会造成数据错乱.
     * 如果此文件没加密，调用此函数会自动加密.
     *
     * @param section 小节名
     * @param key     变量名. 如果这个变量为空串，则删除整个section小节.
     * @param file    ini文件名.
     * @param pwd     密码.
     */
    public void deleteIniPwd(String section, String key, String file, String pwd) {
        dmSoft.callAndCheckResultEq1("DeleteIniPwd", section, key, file, pwd);
    }

    /**
     * 从internet上下载一个文件.
     *
     * @param url          下载的url地址.
     * @param saveFile     要保存的文件名.
     * @param timeoutMills 连接超时时间，单位是毫秒.
     * @return 1 : 成功
     * -1 : 网络连接失败
     * -2 : 写入文件失败
     */
    public int downloadFile(String url, String saveFile, long timeoutMills) {
        return (int) dmSoft.callForLong("DownloadFile", url, saveFile, timeoutMills);
    }

    /**
     * 加密指定的文件.
     * <p>
     * 如果此文件已经加密，调用此函数不会有任何效果.
     * 插件所有的字库 图片 ini都是用此接口来加密.
     *
     * @param file 文件名.
     * @param pwd  密码.
     */
    public void encodeFile(String file, String pwd) {
        dmSoft.callAndCheckResultEq1("EncodeFile", file, pwd);
    }

    /**
     * 根据指定的ini文件以及section,枚举此section中所有的key名
     * <p>
     * 注 : 此函数是多线程安全的. 多线程同时读写同个文件不会造成文件错乱.
     * 另外,此函数无法枚举没有section的key.
     *
     * @param section 小节名. (不可为空)
     * @param file    ini文件名.
     * @return 每个key用"|"来连接，如果没有key，则返回空字符串. 比如"aaa|bbb|ccc"
     */
    public List<String> enumIniKey(String section, String file) {
        String result = dmSoft.callForString("EnumIniKey", section, file);
        return InfoParseUtils.splitString(result, "\\|");
    }

    /**
     * 根据指定的ini文件以及section,枚举此section中所有的key名.可支持加密文件
     * <p>
     * 注 : 此函数是多线程安全的. 多线程同时读写同个文件不会造成文件错乱. 但是多进程是不安全的,要避免多进程同时使用此接口,否则会造成数据错乱.
     * 另外,此函数无法枚举没有section的key.
     * 如果文件没加密，也可以正常读取.
     *
     * @param section 小节名. (不可为空)
     * @param file    ini文件名.
     * @param pwd     密码
     * @return 每个key用"|"来连接，如果没有key，则返回空字符串. 比如"aaa|bbb|ccc"
     */
    public List<String> enumIniKeyPwd(String section, String file, String pwd) {
        String result = dmSoft.callForString("EnumIniKeyPwd", section, file, pwd);
        return InfoParseUtils.splitString(result, "\\|");
    }

    /**
     * 根据指定的ini文件,枚举此ini中所有的Section(小节名)
     * <p>
     * 注 : 此函数是多线程安全的. 多线程同时读写同个文件不会造成文件错乱.
     *
     * @param file ini文件名.
     * @return 每个小节名用"|"来连接，如果没有小节，则返回空字符串. 比如"aaa|bbb|ccc"
     */
    public List<String> enumIniSection(String file) {
        String result = dmSoft.callForString("EnumIniSection", file);
        return InfoParseUtils.splitString(result, "\\|");
    }

    /**
     * 根据指定的ini文件,枚举此ini中所有的Section(小节名) 可支持加密文件
     * <p>
     * 注 : 此函数是多线程安全的. 多线程同时读写同个文件不会造成文件错乱. 但是多进程是不安全的,要避免多进程同时使用此接口,否则会造成数据错乱.
     * 如果文件没加密，也可以正常读取.
     *
     * @param file ini文件名.
     * @param pwd  密码
     * @return 每个小节名用"|"来连接，如果没有小节，则返回空字符串. 比如"aaa|bbb|ccc"
     */
    public List<String> enumIniSectionPwd(String file, String pwd) {
        String result = dmSoft.callForString("EnumIniSectionPwd", file, pwd);
        return InfoParseUtils.splitString(result, "\\|");
    }

    /**
     * 获取指定的文件长度.
     *
     * @param file 文件名
     * @return 文件长度(字节数)
     */
    public long getFileLength(String file) {
        return dmSoft.callForLong("GetFileLength", file);
    }

    /**
     * 获取指定文件或目录的真实路径
     * <p>
     * 注: 这个功能可以获取到路径中有符号链接之后的，真实路径.
     *
     * @param file 路径名,可以是文件路径，也可以是目录. 这里必须是全路径
     * @return 真实路径, 如果失败, 返回空字符串
     */
    public String getRealPath(String file) {
        return dmSoft.callForString("GetRealPath", file);
    }

    /**
     * 从Ini中读取指定信息.
     * <p>
     * 注 : 此函数是多线程安全的. 多线程同时读写同个文件不会造成文件错乱. 但是多进程是不安全的,要避免多进程同时使用此接口,否则会造成数据错乱.
     *
     * @param section 小节名
     * @param key     变量名.
     * @param file    ini文件名.
     * @return 字符串形式表达的读取到的内容
     */
    public String readIni(String section, String key, String file) {
        return dmSoft.callForString("ReadIni", section, key, file);
    }

    /**
     * 从Ini中读取指定信息.可支持加密文件
     * <p>
     * 注 : 此函数是多线程安全的. 多线程同时读写同个文件不会造成文件错乱. 但是多进程是不安全的,要避免多进程同时使用此接口,否则会造成数据错乱.
     * 如果文件没加密，也可以正常读取.
     *
     * @param section 小节名
     * @param key     变量名.
     * @param file    ini文件名.
     * @param pwd     密码
     * @return 字符串形式表达的读取到的内容
     */
    public String readIniPwd(String section, String key, String file, String pwd) {
        return dmSoft.callForString("ReadIniPwd", section, key, file, pwd);
    }

    /**
     * 弹出选择文件夹对话框，并返回选择的文件夹.
     *
     * @return 选择的文件夹全路径
     */
    public String selectDirectory() {
        return dmSoft.callForString("SelectDirectory");
    }

    /**
     * 弹出选择文件对话框，并返回选择的文件
     *
     * @return 选择的文件全路径
     */
    public String selectFile() {
        return dmSoft.callForString("SelectFile");
    }

    /**
     * 向指定文件追加字符串.
     *
     * @param file    文件
     * @param content 写入的字符串.
     */
    public void writeFile(String file, String content) {
        dmSoft.callAndCheckResultEq1("WriteFile", file, content);
    }

    /**
     * 向指定的Ini写入信息.
     * <p>
     * 注 : 此函数是多线程安全的. 多线程同时读写同个文件不会造成文件错乱.
     *
     * @param section 小节名
     * @param key     变量名.
     * @param value   变量内容
     * @param file    ini文件名.
     */
    public void writeIni(String section, String key, String value, String file) {
        dmSoft.callAndCheckResultEq1("WriteIni", section, key, value, file);
    }

    /**
     * 向指定的Ini写入信息.支持加密文件
     * <p>
     * 注 : 此函数是多线程安全的. 多线程同时读写同个文件不会造成文件错乱.
     * 如果此文件没加密，调用此函数会自动加密.
     *
     * @param section 小节名
     * @param key     变量名.
     * @param value   变量内容
     * @param file    ini文件名.
     * @param pwd     密码.
     */
    public void writeIniPwd(String section, String key, String value, String file, String pwd) {
        dmSoft.callAndCheckResultEq1("WriteIniPwd", section, key, value, file, pwd);
    }
}
