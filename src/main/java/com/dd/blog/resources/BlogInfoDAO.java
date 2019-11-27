package com.dd.blog.resources;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.bson.Document;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BlogInfoDAO {
	@Autowired
	private MongoOperations mongoOperations;

	public BlogInfoVO getBlogInfoIndex() {
		BlogInfoVO blogInfoVO = new BlogInfoVO();
		List<SubMenuContent> subMenuContents = new ArrayList<SubMenuContent>();
		Query query = null;

		query = new Query();
		query.with(new Sort(Sort.Direction.ASC, "created_date"));
		List<Document> docs = mongoOperations.find(query, Document.class, "submenucontent");
		if (docs != null && !docs.isEmpty()) {
			for (Document document : docs) {
				SubMenuContent subMenuContent = new SubMenuContent();
				subMenuContent.setConetent_id(document.getString("conetent_id"));
				subMenuContent.setContent_header(document.getString("content_header"));
				subMenuContent.setContentHeaderTag(document.getString("contentheaderTag"));
				subMenuContent.setPostedBy(document.getString("postedBy"));
				subMenuContent.setSubmenu_ref(document.getString("submenu_ref"));
				Binary imageData = document.get("themeImage", Binary.class);
				if (imageData != null) {
					byte[] imageByteData = imageData.getData();
					subMenuContent.setIndivisualThemeimage(Base64.getEncoder().encodeToString(imageByteData));
				}
				subMenuContents.add(subMenuContent);
			}
		}

		blogInfoVO.setSubMenuContents(subMenuContents);
		return blogInfoVO;
	}

}
