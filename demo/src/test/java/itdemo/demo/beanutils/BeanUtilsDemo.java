package itdemo.demo.beanutils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author kenny
 * 
 */
public class BeanUtilsDemo {

  private Logger log = Logger.getLogger(BeanUtilsDemo.class);
  private Employee employee;
  private EmployeeFormBean employeeFormBean;
  private HttpServletRequest req;

  /**
   * 一些進行測試的前置作業..
   * 
   * @throws Exception
   */
  @Before
  public void setupEmployee() throws Exception {
    this.employee = new Employee();
    this.employee.setName("kenny");
    Address address = new Address();
    address.setDetail("台北市重慶南路一段57號3樓");
    this.employee.setAddress(address);

    List<MobilePhone> mobilePhones = new ArrayList<MobilePhone>();
    MobilePhone phone1 = new MobilePhone();
    phone1.setName("iPhone");
    mobilePhones.add(phone1);
    MobilePhone phone2 = new MobilePhone();
    phone2.setName("HTC Butterfly");
    mobilePhones.add(phone2);
    this.employee.setMobilePhones(mobilePhones);

    Enumeration<Object> enumeration = new StringTokenizer("name", ",");
    req = mock(HttpServletRequest.class);
    when(req.getParameterNames()).thenReturn(enumeration);
    when(req.getParameter("name")).thenReturn("mock test");

    this.employeeFormBean = new EmployeeFormBean();
    BeanUtils.copyProperties(employeeFormBean, this.employee);
  }

  /**
   * Java Reflection api
   * 
   * @throws Exception
   */
  @Ignore
  @Test
  public void testReflection() throws Exception {
    String name = (String) Employee.class.getMethod("getName", null).invoke(this.employee, null);
    log.info(name);

    Address address = (Address) Employee.class.getMethod("getAddress", null).invoke(this.employee, null);
    String detail = (String) Address.class.getMethod("getDetail", null).invoke(address, null);
    log.info(detail);

  }

  /**
   * BeanUtils.getProperty
   * 
   * @throws Exception
   */
  @Ignore
  @Test
  public void testGetProperty() throws Exception {
    log.info(BeanUtils.getProperty(this.employee, "name"));

    log.info(BeanUtils.getProperty(this.employee, "address.detail"));
  }

  /**
   * BeanUtils.setProperty
   * 
   * @throws Exception
   */
  @Ignore
  @Test
  public void testSetProperty() throws Exception {
    BeanUtils.setProperty(this.employee, "name", "ken");
    log.info(BeanUtils.getProperty(this.employee, "name"));

    BeanUtils.setProperty(this.employee, "address.detail", "台北市忠孝東路一段1號");
    log.info(BeanUtils.getProperty(this.employee, "address.detail"));

  }

  /**
   * BeanUtils.getProperty 進階用法
   * 
   * @throws Exception
   */
  @Ignore
  @Test
  public void testGetProperty2() throws Exception {
    log.info(BeanUtils.getProperty(this.employee, "mobilePhones[1].name"));
  }

  /**
   * BeanUtils.populate
   * 
   * @throws Exception
   */
  @Ignore
  @Test
  public void testPopulateProperties() throws Exception {
    Map<String, String> paramsMap = new HashMap<String, String>();
    Enumeration<String> names = req.getParameterNames();
    while (names.hasMoreElements()) {
      String name = (String) names.nextElement();
      paramsMap.put(name, req.getParameter(name));
    }

    Employee employee = new Employee();
    BeanUtils.populate(employee, paramsMap);
    log.info(employee.getName());
  }

  /**
   * BeanUtils.copyProperties(dest,orig)
   * 
   * @throws Exception
   */
  @Ignore
  @Test
  public void testCopyProperties() throws Exception {
    EmployeeFormBean employeeFormBean = new EmployeeFormBean();
    BeanUtils.copyProperties(employeeFormBean, this.employee);
    // log.info(employeeFormBean.getAddress().getDetail());
    log.info(BeanUtils.getProperty(employeeFormBean, "address.detail"));
  }

  /**
   * ConstructorUtils
   * 
   * @throws Exception
   */
  @Ignore
  @Test
  public void testConstructorUtils() throws Exception {
    Employee employee = (Employee) ConstructorUtils.invokeConstructor(Employee.class, null);
    employee.setName("test");
    log.info(BeanUtils.getProperty(employee, "name"));
  }

  /**
   * MethodUtils
   * 
   * @throws Exception
   */
  // @Ignore
  @Test
  public void testMethodUtils() throws Exception {
    // log.info(MethodUtils.invokeMethod(this.employee, "getName", null));
    Map<String, Class[]> methodsMap = new HashMap<String, Class[]>();
    for (Method m : Employee.class.getMethods()) {
      methodsMap.put(m.getName(), m.getParameterTypes());
    }
    Method testMethod = MethodUtils.getAccessibleMethod(this.employee.getClass(), "getName", methodsMap.get("getName"));
    log.info(testMethod.invoke(this.employee, null));
  }
}
