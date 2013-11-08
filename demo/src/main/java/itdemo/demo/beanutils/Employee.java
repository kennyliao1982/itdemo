package itdemo.demo.beanutils;

import java.util.List;

public class Employee {

  private String name;
  private Address address;
  private List<MobilePhone> mobilePhones;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public List<MobilePhone> getMobilePhones() {
    return mobilePhones;
  }

  public void setMobilePhones(List<MobilePhone> mobilePhones) {
    this.mobilePhones = mobilePhones;
  }

}
