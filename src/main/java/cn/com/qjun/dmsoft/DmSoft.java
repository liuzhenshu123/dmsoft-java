package cn.com.qjun.dmsoft;

import cn.com.qjun.dmsoft.utils.RuntimeUtils;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.LibraryLoader;
import com.jacob.com.Variant;
import com.sun.jna.Library;
import com.sun.jna.Native;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 大漠插件封装
 *
 * @author RenQiang
 * @date 2024/2/11
 */
@Slf4j
public class DmSoft {
    private final ThreadLocal<ActiveXComponent> componentThreadLocal;
    private final BasicOperations basicOperations;
    private final WindowOperations windowOperations;
    private final ColourOperations colourOperations;
    private final BackgroundOperations backgroundOperations;

    static {
        try {
            Path jacobDll = Files.createTempFile("jacob-1.20-x86", ".dll");
            RuntimeUtils.copyResourceFile("/win32-x86/jacob-1.20-x86.dll", jacobDll);
            System.setProperty(LibraryLoader.JACOB_DLL_PATH, jacobDll.toAbsolutePath().toString());
            LibraryLoader.loadJacobLibrary();

            Path dmDll = Files.createTempFile("dm", ".dll");
            RuntimeUtils.copyResourceFile("/win32-x86/dm.dll", dmDll);
            long dmReg = DmReg.INSTANCE.SetDllPathA(dmDll.toAbsolutePath().toString(), 1);
            if (dmReg != 1L) {
                throw new RuntimeException("Load dm.dll failed.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Library release fail.", e);
        }
    }

    public DmSoft(String regCode, String addCode, String workDir) {
        basicOperations = new BasicOperations(this);
        windowOperations = new WindowOperations(this);
        colourOperations = new ColourOperations(this);
        backgroundOperations = new BackgroundOperations(this);
        componentThreadLocal = ThreadLocal.withInitial(() -> {
            ComThread.InitMTA();
            ActiveXComponent dmSoft = new ActiveXComponent("dm.dmsoft");
//            JacobUtils.callAndCheckResultEq1(dmSoft, "SetShowErrorMsg", 0);
            log.info("Init DmSoft success.");
            return dmSoft;
        });
        String version = opsForBasic().ver();
        log.info("大漠插件版本: {}", version);
        opsForBasic().reg(regCode, addCode);
        opsForBasic().setPath(workDir);

//        // 加载AI
//        JacobUtils.callAndCheckResultEq1(component(), "LoadAi", "modules\\ai.module");
//        log.info("LoadAi");
//        JacobUtils.callAndCheckResultEq1(component(), "AiYoloSetVersion", "v5-7.0");
//        log.info("AiYoloSetVersion");
//        JacobUtils.callAndCheckResultEq1(component(), "AiYoloSetModel", 0, "modules\\MirM.dmx", "123");
//        log.info("AiYoloSetModel");
//        JacobUtils.callAndCheckResultEq1(component(), "AiYoloUseModel", 0);
//        log.info("AiYoloUseModel");

        // 加载字库
//        JacobUtils.callAndCheckResultEq1(component(), "SetDict", DICT_INDEX_DIGITS, "resources\\dict\\digits.txt");
//        JacobUtils.callAndCheckResultEq1(component(), "SetDict", DICT_INDEX_HANZI, "resources\\dict\\hanzi.txt");
    }

    public BasicOperations opsForBasic() {
        return this.basicOperations;
    }

    public WindowOperations opsForWindow() {
        return this.windowOperations;
    }

    public BackgroundOperations opsForBackground() {
        return this.backgroundOperations;
    }

    public ColourOperations opsForColour() {
        return this.colourOperations;
    }

    /**
     * 调用大漠接口，返回多个整型数
     *
     * @param method       方法名
     * @param outputCount  输出参数数量
     * @param outputInArgs 输出参数是否已经包含在参数中
     * @param args         方法参数
     * @return 返回的整型数数组
     */
    int[] callForMultiInt(String method, int outputCount, boolean outputInArgs, Object... args) {
        int argsCount = args == null ? 0 : args.length;
        int inputCount = outputInArgs ? argsCount : argsCount + outputCount;
        Variant[] variants = new Variant[inputCount];
        for (int i = 0; i < argsCount; i++) {
            variants[i] = new Variant(args[i]);
        }
        if (inputCount > argsCount) {
            for (int i = argsCount; i < inputCount; i++) {
                variants[i] = new Variant(0, true);
            }
        }
        callAndCheckResultEq1(method, (Object[]) variants);
        return Stream.of(variants)
                .skip(inputCount - outputCount)
                .mapToInt(Variant::getInt)
                .toArray();
    }

    /**
     * 调用大漠接口，返回字符串
     *
     * @param method 方法名
     * @param args   参数
     * @return 返回的字符串
     */
    String callForString(String method, Object... args) {
        return Dispatch.call(component(), method, args).getString();
    }

    /**
     * 调用大漠接口，并将返回的字符串（逗号分隔）转换成Long型List
     *
     * @param method 方法名
     * @param args   参数
     * @return 将大漠返回的字符串用逗号分割转成Long型的List
     */
    List<Long> callForStringAndConvertToLongList(String method, Object... args) {
        String result = callForString(method, args);
        if (result == null || result.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(result.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    /**
     * 调用大漠接口，返回整型数
     *
     * @param method 方法名
     * @param args   参数
     * @return 返回的整型数
     */
    long callForLong(String method, Object... args) {
        return Dispatch.call(component(), method, args).getInt();
    }

    /**
     * 调用大漠接口，并检查返回值是否等于1，不等于1则抛出异常
     *
     * @param method 方法名
     * @param args   参数
     */
    void callAndCheckResultEq1(String method, Object... args) {
        long result = callForLong(method, args);
        if (result != 1L) {
            throw new RuntimeException(String.format("JACOB调用失败: method=%s, args=%s, code=%d", method, Arrays.toString(args), result));
        }
    }

    /**
     * 获取当前线程的大漠插件ActiveX对象
     *
     * @return
     */
    private ActiveXComponent component() {
        return componentThreadLocal.get();
    }

    private interface DmReg extends Library {
        DmReg INSTANCE = Native.load("DmReg", DmReg.class);

        /**
         * 加载大漠插件
         *
         * @param dllPath dm.dll路径
         * @param mode    0表示STA，1表示MTA
         * @return 0表示成功
         */
        int SetDllPathA(String dllPath, int mode);
    }
}
