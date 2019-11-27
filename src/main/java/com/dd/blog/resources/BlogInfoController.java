package com.dd.blog.resources;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogInfoController {
	@Autowired
	private BlogInfoDAO blogInfoDAO;


	@RequestMapping("/blogInfo")
	public BlogInfoVO blogInfo() throws IOException {
		return blogInfoDAO.getBlogInfoIndex();
	}
	
}