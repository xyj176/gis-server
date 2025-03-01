package cn.xuyj.gis.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.postgis.PostgisNGDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xuyj
 * @Date: 2025/3/1 0:33
 * @Email: 1768335576@qq.com
 * @Desc：geotools的数据库连接配置
 */
@Configuration
@Data
@Slf4j
public class GeotoolsSpatialDatabaseConfig implements SpatialDatabaseConfig {
    @Value("${spatial.datasource.host:localhost}")
    private String host;
    @Value("${spatial.datasource.port:5432}")
    private Integer port;
    @Value("${spatial.datasource.database:test}")
    private String database;
    @Value("${spatial.datasource.schema:public}")
    private String schema;
    @Value("${spatial.datasource.user}")
    private String user;
    @Value("${spatial.datasource.password}")
    private String password;
    @Value("${spatial.datasource.type:postgis}")
    private String type;

    private DataStore dataStore;

    @Bean(name = "geotools-connect")
    @Override
    public DataStore connect() {
        Map<String, Object> params = new HashMap<>();
        params.put(PostgisNGDataStoreFactory.HOST.key, host);
        params.put(PostgisNGDataStoreFactory.PORT.key, port);
        params.put(PostgisNGDataStoreFactory.DATABASE.key, database);
        params.put(PostgisNGDataStoreFactory.SCHEMA.key, schema);
        params.put(PostgisNGDataStoreFactory.USER.key, user);
        params.put(PostgisNGDataStoreFactory.PASSWD.key, password);
        params.put(PostgisNGDataStoreFactory.DBTYPE.key, type);
        try {
            dataStore = DataStoreFinder.getDataStore(params);
            if (dataStore == null) {
                log.warn("geotools连接数据库，数据源为null");
                return null;
            }
            log.info("geotools连接数据库成功！");
            return dataStore;
        } catch (IOException e) {
            log.error("geotools连接数据库失败：" + e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void disConnect() {
        dataStore.dispose();
    }

    @Override
    public DataStore getSdObject() {
        if (dataStore != null)
            return dataStore;
        return connect();
    }
}
