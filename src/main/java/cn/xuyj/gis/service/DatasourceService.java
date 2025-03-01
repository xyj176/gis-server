package cn.xuyj.gis.service;

import java.util.List;

/**
 * @Author: xuyj
 * @Date: 2025/3/1 11:59
 * @Email: 1768335576@qq.com
 * @Desc：类描述
 */
public interface DatasourceService {
    /**
     * 获取空间库里面所有数据的名称
     *
     * @return
     */
    List<String> getSdDatasetNames();
}
