package com.example.demojasper.controller;

import com.example.demojasper.controller.jasper.Data;
import com.example.demojasper.controller.jasper.HeaderAndFooter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    String targetPath = "src/main/resources/reports/";
    String reportPath = "src/main/resources/reports/report.jrxml";

    @GetMapping("/test")
    public void testEndpoint() {

    }

    private void generateReport() {

        // setting the header and footer default value
        HeaderAndFooter headerAndFooter = new HeaderAndFooter();
        headerAndFooter.setHeader("THIS IS THE REPORT TITLE");
        headerAndFooter.setFooter("THIS IS THE REPORT FOOTER");

        // setting the data
        List<Data> dataList = new ArrayList<>();

        Data data = new Data();
        data.setId("1");
        data.setName("John Doe");
        data.setAddress("123 Main St");

        Data data1 = new Data();
        data.setId("2");
        data.setName("Alice Jerrack");
        data.setAddress("456 Side St");

        dataList.add(data);
        dataList.add(data1);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("headerAndFooter", headerAndFooter);
        parameters.put("dataSource", dataSource);

        // now need to generate report only

    }


}
