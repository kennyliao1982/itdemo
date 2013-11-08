package itdemo.demo.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FreeMarkerDemo {

  private static Logger log = Logger.getLogger(FreeMarkerDemo.class);

  private static Configuration cfg;

  //@BeforeClass
  public static void setUp() {
    if (cfg == null) {
      cfg = new Configuration();
      try {
        cfg.setDirectoryForTemplateLoading(new File("./src/test/java/itdemo/demo/freemarker"));
        cfg.setObjectWrapper(new DefaultObjectWrapper());
      } catch (Exception e) {
        log.error(e.getMessage());
        cfg = null;
      }
    }
  }
  @Ignore
  @Test
  public void test() throws Exception {
    //始初化freemarker configuration
    Configuration cfg = new Configuration();
    cfg.setDirectoryForTemplateLoading(new File("./src/test/java/itdemo/demo/freemarker"));
    cfg.setObjectWrapper(new DefaultObjectWrapper());
    
    //戴入template
    Template tpl = cfg.getTemplate("test.ftl");

    //準備要輸出的資料
    Map<String,Object> data = new HashMap<String,Object>();
    data.put("name", "kenny");
    data.put("orderDate", new Date());
    data.put("point", 1000);
    data.put("amount", 1000);
    data.put("payDate", new Date());
    data.put("payAccount", "12345668");
    
    List dataList = new ArrayList();
    dataList.add("aa");
    data.put("dataList", dataList);
    //資料 + template，透過Writer輸出  
    //StringWriter, FileWriter, HttpServletResponse的PrintWriter都行
    StringWriter sw = new StringWriter();
    tpl.process(data, sw);
    
    log.info(sw.toString());
  }
  
  @Test
  public void testDoc() throws Exception {
    //始初化freemarker configuration
    Configuration cfg = new Configuration();
    cfg.setDirectoryForTemplateLoading(new File("./src/test/java/itdemo/demo/freemarker"));
    cfg.setObjectWrapper(new DefaultObjectWrapper());
    
    //戴入template
    Template tpl = cfg.getTemplate("creditNote.ftl");

    //準備要輸出的資料
    Map<String,Object> data = new HashMap<String,Object>();
    data.put("currentRocYear", "102");
    data.put("currentMonth", "06");
    data.put("currentDate", "20");
    data.put("orderNo", "123456");
    data.put("style1", "2");
    data.put("year1", "102");
    data.put("month1", "06");
    data.put("date1", "02");
    data.put("invoiceCode1", "BY");
    data.put("invoice1", "6666602");
    data.put("product1", "三明治");
    data.put("rewardPrice1", "99");
    data.put("tax1", "1");
    data.put("taxCheck1", "V");
    data.put("sumRewardPrice", "99");
    data.put("sumTax", "1");
    data.put("clientName", "廖宏明");
    data.put("address", "台北市");
    //資料 + template，透過Writer輸出  
    //StringWriter, FileWriter, HttpServletResponse的PrintWriter都行
    //StringWriter sw = new StringWriter();
    //tpl.process(data, sw);
    
    //log.info(sw.toString());
    BufferedWriter bw = new BufferedWriter(new FileWriter("d:/temp/aaa.doc"));
    tpl.process(data, bw);
    bw.flush();
    bw.close();
  }
}
