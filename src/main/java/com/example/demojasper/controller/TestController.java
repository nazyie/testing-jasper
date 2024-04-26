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


    import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRDocxExporter;
import net.sf.jasperreports.engine.export.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

// ...

private void generateReport() {
    try {
        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);

        // Parameters for report
        Map<String, Object> parameters = new HashMap<String, Object>();

        // DataSource
        // This is simple example, no database.
        // then using empty datasource.
        Collection<Map<String, ?>> dataSource = Collections.singletonList(Collections.singletonMap("data", "test data"));

        JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);

        // Make sure the output directory exists.
        File outDir = new File(targetPath);
        outDir.mkdirs();

        // Export to PDF.
        JasperExportManager.exportReportToPdfFile(print, targetPath + "simple_report.pdf");

        // Export to HTML.
        JasperExportManager.exportReportToHtmlFile(print, targetPath + "simple_report.html");

        // Export to XML.
        JasperExportManager.exportReportToXmlFile(print, targetPath + "simple_report.xml", false);

        // Export to XLS.
        JRXlsxExporter xlsExporter = new JRXlsxExporter();
        xlsExporter.setExporterInput(new SimpleExporterInput(print));
        xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(targetPath + "simple_report.xlsx"));
        xlsExporter.exportReport();

        // Export to CSV.
        JRCsvExporter csvExporter = new JRCsvExporter();
        csvExporter.setExporterInput(new SimpleExporterInput(print));
        csvExporter.setExporterOutput(new SimpleWriterExporterOutput(targetPath + "simple_report.csv"));
        csvExporter.exportReport();

        // Export to DOCX.
        JRDocxExporter docxExporter = new JRDocxExporter();
        docxExporter.setExporterInput(new SimpleExporterInput(print));
        docxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(targetPath + "simple_report.docx"));
        docxExporter.exportReport();

        System.out.println("Done!");

    } catch (JRException e) {
        e.printStackTrace();
    }
}

}
