package com.huazai.aiyou.item.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 测试生成静态Html文件
 *              </ul>
 * @className GenHtmlController
 * @package com.huazai.b2c.aiyou.controller
 * @createdTime 2017年06月18日
 *
 * @version V1.0.0
 */
@Controller
public class GenHtmlController {

	@Autowired
	private FreeMarkerConfig cMarkerConfig;
	
	@RequestMapping(value = "/genhtml",method = RequestMethod.GET)
	@ResponseBody
	public String genHtml() throws Exception {
		// 1、从spring容器中获得FreeMarkerConfigurer对象
		// 2、从FreeMarkerConfigurer对象中获得Configuration对象
		Configuration configuration = cMarkerConfig.getConfiguration();
		// 3、使用Configuration对象获得Template对象
		Template template = configuration.getTemplate("template.ftl");
		// 4、创建数据集
		Map<String, Object> model = new HashMap<>();
		model.put("contact", "who.seek.me@java98k.vip");
		// 5、创建输出文件的Writer对象
		File file = new File("D:/fremarker/result.html");
		Writer writer = new FileWriter(file);
		// 6、调用模板对象的process方法，生成文件
		template.process(model, writer);
		// 7、关闭流
		writer.close();
		return "successfully";
	}
	
	
}
