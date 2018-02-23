package com.hxy.sys.oss;

import com.hxy.base.utils.DateUtils;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * 类CloudStorageService的功能描述:
 * 云存储(支持七牛、阿里云、腾讯云、又拍云)
 * @auther hxy
 * @date 2017-08-25 16:15:52
 */
public abstract class CloudStorageService {
    /** 云存储配置信息 */
    CloudStorageConfig config;

    /**
     * 文件路径
     * @param prefix 前缀
     * @return 返回上传路径
     */
    public String getPath(String prefix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        String path = DateUtils.format(new Date(), "yyyyMMdd") + "/" + uuid;

        if(StringUtils.isNotBlank(prefix)){
            path = prefix + "/" + path;
        }

        return path;
    }

    /**
     * 保持原生路径
     * @param prefix
     * @param originalName
     * @return
     */
    public String getOriginalPath(String prefix, String originalName) {
        String path = originalName;
        if(StringUtils.isNotBlank(prefix)){
            path = prefix + "/" + originalName;
        }

        return path;
    }

    /**
     * 文件上传
     * @param data    文件字节数组
     * @param path    文件路径，包含文件名
     * @return        返回http地址
     */
    public abstract String upload(byte[] data, String path);

    /**
     * 文件上传
     * @param data    文件字节数组
     * @return        返回http地址
     */
    public abstract String upload(byte[] data);

    /**
     * 文件上传
     * @param inputStream   字节流
     * @param path          文件路径，包含文件名
     * @return              返回http地址
     */
    public abstract String upload(InputStream inputStream, String path);

    /**
     * 文件上传
     * @param inputStream  字节流
     * @return             返回http地址
     */
    public abstract String upload(InputStream inputStream);

    /***
     *
     * @param bytes
     * @param originalName
     * @param subFix 后缀名，主要与上面第一个方法区分开
     * @return
     */
    public abstract String upload(byte[] bytes, String originalName, String subFix);
}