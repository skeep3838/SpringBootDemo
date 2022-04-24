package com.example.customer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.SpringBootDemoApplication;
import com.example.entity.Customer;
import com.example.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SpringBootDemoApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
//@Transactional	//加 Transactional 不會真的存資料庫
class CustomerTests {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private CustomerService customerService;
	
	private Integer deleteCid;

//    @Autowired
//    private CustomerRepository customerRepository;
	
	@Before()
	public void setup()
	{
	    //Init MockMvc Object and build
	    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * 查詢所有會員
	 * 
	 * @throws Exception
	 */
	@Test
	public void selectCustomerTest() throws Exception {
		this.mvc.perform(get("/customer/findAllCustomer").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	/**
	 * 新增客戶資料
	 * @throws Exception
	 */
	@Test
	public void insertCustomerTest() throws Exception {
		Customer customer = new Customer();
		customer.setUserName("test");
		customer.setPassword("000000");
		customer.setEnabled("1");
		this.mvc.perform(post("/customer/insertCustomer")
				.content(objectMapper.writeValueAsString(customer))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
		
		//	設定要刪除的資料
		deleteCid = customerService.findByUserName(customer.getUserName()).getCid();
	}

	/**
	 * 修改會員資料
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateCustomerTest() throws Exception {
		Customer customer = new Customer();
		customer.setUserName("test");
		customer.setPassword("000000");
		customer.setEnabled("1");
		this.mvc.perform(put("/customer/updateCustomer")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	/**
	 * 查詢客戶by id
	 * 
	 * @throws Exception
	 */
	@Test
	public void findCustomerByIdTest() throws Exception {
		this.mvc.perform(get("/customer/findCustomerById?id=1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * 刪除客戶資料
	 * @throws Exception
	 */
	@WithMockUser(authorities = "admin")
	@Test
	public void deleteCustomerTest() throws Exception {
		Customer customer = new Customer();
		customer.setCid(deleteCid);
		this.mvc.perform(delete("/customer/deleteCustomer")
				.content(objectMapper.writeValueAsString(customer))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

}
