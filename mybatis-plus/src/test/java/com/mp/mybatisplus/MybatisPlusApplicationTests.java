package com.mp.mybatisplus;

import com.mp.mybatisplus.bean.Weather;
import com.mp.mybatisplus.mapper.ProductMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {


		@Autowired
		private ProductMapper productMapper;

		@Test
		public void testSelect() {
			System.out.println(("----- selectAll method test ------"));
			List<Weather> userList = productMapper.selectList(null);
//			Assert.assertEquals(5, userList.size());
			userList.forEach(System.out::println);
		}



	@Test
	public void contextLoads() {
	}

}
