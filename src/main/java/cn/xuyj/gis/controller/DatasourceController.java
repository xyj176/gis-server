package cn.xuyj.gis.controller;

import cn.xuyj.gis.service.impl.GdalDatasourceServiceImpl;
import cn.xuyj.gis.service.impl.GeotoolsDatasourceServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: xuyj
 * @Date: 2025/3/1 12:19
 * @Email: 1768335576@qq.com
 * @Desc：类描述
 */
@RestController
@Api(tags = "数据源Controller")
public class DatasourceController {
    @Autowired
    GdalDatasourceServiceImpl gdalDsService;

    @Autowired
    GeotoolsDatasourceServiceImpl geotoolsDsService;

    @GetMapping("/sd/dataset/names")
    @ApiOperation("获取空间库所有数据集名称")
    public void getAllSdDatasetNames() {
        List<String> datasetNamesGdal = gdalDsService.getSdDatasetNames();
        System.out.println("gdal获取空间库所有数据集的名称：" + String.join(",", datasetNamesGdal));
        List<String> datasetNamesGeotools = geotoolsDsService.getSdDatasetNames();
        System.out.println("geotools获取空间库所有数据集的名称：" + String.join(",", datasetNamesGeotools));
    }
}
