package cn.xuyj.gis.service.impl;

import cn.xuyj.gis.config.GdalSpatialDatabaseConfig;
import cn.xuyj.gis.service.DatasourceService;
import org.gdal.ogr.DataSource;
import org.gdal.ogr.Layer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xuyj
 * @Date: 2025/3/1 12:04
 * @Email: 1768335576@qq.com
 * @Desc：类描述
 */
@Service
public class GdalDatasourceServiceImpl implements DatasourceService {
    @Autowired
    GdalSpatialDatabaseConfig gdalSdConfig;

    @Override
    public List<String> getSdDatasetNames() {
        List<String> datasetNameList = new ArrayList<>();
        DataSource ds = gdalSdConfig.getSdObject();
        int layerCount = ds.GetLayerCount();
        for (int i = 0; i < layerCount; i++) {
            Layer layer = ds.GetLayer(i);
            String name = layer.GetName();
            datasetNameList.add(name);
        }
        return datasetNameList;
    }
}
