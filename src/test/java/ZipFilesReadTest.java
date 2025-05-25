import com.codeborne.pdftest.PDF;

import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFilesReadTest {
    private final ClassLoader cl = ZipFilesReadTest.class.getClassLoader();


    @Test
    void zipFilePDFReadTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                Objects.requireNonNull(cl.getResourceAsStream("resources.zip"))
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("sample.pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(
                            pdf.text.contains("Lorem ipsum dolor sit amet")
                    );
                }
            }
        }
    }

    @Test
    void zipCSVFileReadTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("resources.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("addresses.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csvReader.readAll();
                    Assertions.assertEquals(2, data.size());
                    Assertions.assertArrayEquals(new String[]{"John", "Doe", "120 jefferson st.", "Riverside", "NJ", "08075"}, data.get(0));
                    Assertions.assertArrayEquals(new String[]{"Jack", "McGinnis", "220 hobo Av.", "Phila", "PA", "09119"}, data.get(1));
                }
            }
        }
    }

    @Test
    void zipFileXLSXReadTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("resources.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("file_example_XLS_10.xls")) {
                    XLS xls = new XLS(zis);
                    String actualValue = xls.excel.getSheetAt(0).getRow(1).getCell(2).getStringCellValue();
                    Assertions.assertTrue(actualValue.contains("Abril"));
                }
            }
        }
    }
}