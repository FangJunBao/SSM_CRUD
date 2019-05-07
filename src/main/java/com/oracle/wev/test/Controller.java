package com.oracle.wev.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.oralce.web.bean.PageBean;
import com.oralce.web.bean.subMonster;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class Controller {
 @Autowired
 WebApplicationContext context;
 
 MockMvc mvc;
@Before
 public void init(){
	 
	 mvc=MockMvcBuilders.webAppContextSetup(context).build();
 }
 
 @Test
 public void testController() throws Exception{
	 
	 MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/monsters?pageNow=2")).andReturn();
	 
	 MockHttpServletRequest request=result.getRequest();
	 
	 PageBean<subMonster> pb=(PageBean<subMonster>) request.getAttribute("pb");
	 
	 List<subMonster> list=pb.getBeanList();
	 
	 for (subMonster subMonster : list) {
		 
		 System.out.println(subMonster);
		
	}
 }
}
