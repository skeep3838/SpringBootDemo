package com.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.SpringBootDemoApplication;
import com.example.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SpringBootDemoApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class OrderTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private OrderRepository dao;
	
	@Before()
	public void setup()
	{
	    //Init MockMvc Object and build
	    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
//	@Test
//	void test() throws JsonProcessingException {
//		fail(objectMapper.writeValueAsString(dao.findOrderByOid()));
//	}
	
	/**
	 * 查詢訂單資訊
	 * 查回來的物件需要調整
	 * 
	 * @throws Exception
	 */
	@Test
	public void findOrderByOidTest() throws Exception {
		this.mvc.perform(get("/order/findOrderByOid").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

}
