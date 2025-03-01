package cn.xuyj.gis.service.impl;

import cn.xuyj.gis.config.GeotoolsSpatialDatabaseConfig;
import cn.xuyj.gis.service.DatasourceService;
import lombok.SneakyThrows;
import org.geotools.data.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xuyj
 * @Date: 2025/3/1 12:05
 * @Email: 1768335576@qq.com
 * @Desc：类描述
 */
@Service
public class GeotoolsDatasourceServiceImpl implements DatasourceService {
    @Autowired
    GeotoolsSpatialDatabaseConfig geotoolsSdConfig;

    @SneakyThrows
    @Override
    public List<String> getSdDatasetNames() {
        DataStore ds = geotoolsSdConfig.getSdObject();
        List<String> datasetNameList = new ArrayList<>(Arrays.asList(ds.getTypeNames()));
        return datasetNameList;
    }
}
