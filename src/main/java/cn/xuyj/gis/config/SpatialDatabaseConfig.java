package cn.xuyj.gis.config;

/**
 * @Author: xuyj
 * @Date: 2025/3/1 10:53
 * @Email: 1768335576@qq.com
 * @Desc：类描述
 */
public interface SpatialDatabaseConfig {
    /**
     * 连接数据库
     *
     * @return
     */
    Object connect();

    /**
     * 断开数据库连接
     */
    void disConnect();

    /**
     * 获取数据库对象
     *
     * @return
     */
    Object getSdObject();
}
