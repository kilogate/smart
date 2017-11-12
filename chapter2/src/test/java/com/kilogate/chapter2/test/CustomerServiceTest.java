package com.kilogate.chapter2.test;

import com.kilogate.chapter2.helper.DatabaseHelper;
import com.kilogate.chapter2.model.Customer;
import com.kilogate.chapter2.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 客户服务测试
 *
 * @author fengquanwei
 * @create 2017/11/11 15:44
 **/
public class CustomerServiceTest {
    private CustomerService customerService;

    @Before
    public void init() throws IOException {
        DatabaseHelper.executeSqlFile("sql/customer_init.sql");
        this.customerService = new CustomerService();
    }

    @Test
    public void createCustomerTest() throws Exception {
        HashMap<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("name", "customer100");
        fieldMap.put("contact", "John");
        fieldMap.put("telephone", "13777777777");
        boolean result = customerService.createCustomer(fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        long id = 1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }

    @Test
    public void updateCustomerTest() throws Exception {
        long id = 1;
        HashMap<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("contact", "Eric");
        boolean result = customerService.updateCustomer(id, fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void getCustomerListTest() throws Exception {
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(2, customerList.size());
    }

    @Test
    public void getCustomerTest() throws Exception {
        long id = 1;
        Customer customer = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }

}
