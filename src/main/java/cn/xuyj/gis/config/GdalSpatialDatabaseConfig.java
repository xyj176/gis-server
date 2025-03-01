package cn.xuyj.gis.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.gdal.gdal.gdal;
import org.gdal.ogr.DataSource;
import org.gdal.ogr.ogr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Author: xuyj
 * @Date: 2025/3/1 10:57
 * @Email: 1768335576@qq.com
 * @Desc：gdal的数据库连接配置
 */
@Data
@Slf4j
@Configuration
public class GdalSpatialDatabaseConfig implements SpatialDatabaseConfig {
    @Value("${spatial.datasource.host:localhost}")
    private String host;
    @Value("${spatial.datasource.port:5432}")
    private Integer port;
    @Value("${spatial.datasource.database:test}")
    private String database;
    @Value("${spatial.datasource.user}")
    private String user;
    @Value("${spatial.datasource.password}")
    private String password;

    private DataSource dataSource;

    @PostConstruct
    public void init() {
        ogr.RegisterAll();
        gdal.AllRegister();
        //支持中文路径
        gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES");
        //属性表支持中文
        gdal.SetConfigOption("SHAPE_ENCODING", "CP936");
    }

    @Override
    @Bean(name = "gdal-connect")
    public DataSource connect() {
        String connStr = String.format("PG:dbname = '%s' host ='%s' port ='%s' user='%s' password = '%s' ", database, host, port, user, password);
        dataSource = ogr.Open(connStr, 0);
        if (dataSource == null) {
            log.error("gdal连接数据库失败：" + gdal.GetLastErrorMsg());
            return null;
        }
        log.info("gdal连接数据库成功！");
        return dataSource;
    }

    @Override
    public void disConnect() {
        dataSource.Close();
        dataSource.delete();
    }

    @Override
    public DataSource getSdObject() {
        if (dataSource != null)
            return dataSource;
        return connect();
    }
}
