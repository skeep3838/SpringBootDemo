package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.service.ItemService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class ItemTest {
	
	@Autowired
	private ItemService itemService;
	
	@Test
	public void queryItemByTest(){
		assertThat(itemService.queryItemByTypeAndPrice("2", 95));
	}
}
