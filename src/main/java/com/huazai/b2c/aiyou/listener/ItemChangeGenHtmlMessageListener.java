package com.huazai.b2c.aiyou.listener;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import com.huazai.b2c.aiyou.pojo.TbItem;
import com.huazai.b2c.aiyou.pojo.TbItemDesc;
import com.huazai.b2c.aiyou.service.TbItemDescService;
import com.huazai.b2c.aiyou.service.TbItemService;
import com.huazai.b2c.aiyou.vo.TbItemVO;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 商品详情消息监听器
 *              </ul>
 * @className ItemChangeGenHtmlMessageListener
 * @package com.huazai.b2c.aiyou.listener
 * @createdTime 2017年06月18日
 *
 * @version V1.0.0
 */
public class ItemChangeGenHtmlMessageListener implements MessageListener {

	@Autowired
	private FreeMarkerConfig freeMarkerConfig;

	@Autowired
	private TbItemService tbItemService;
	
	@Autowired
	private TbItemDescService tbItemDescService;

	/**
	 * 商品消息处理
	 */
	@Override
	public void onMessage(Message message) {
		// 1、获取商品消息
		if (message instanceof TextMessage) {
			TextMessage tMessage = (TextMessage) message;
			try {
				String text = tMessage.getText();
				if (StringUtils.isNotBlank(text)) {
					// 2、通过接收到的消息转换成商品id
					Long itemId = Long.valueOf(text);
					// 3、调用商品服务查询商品的信息
					TbItem item = tbItemService.getTbItemById(itemId);
					TbItemDesc itemDesc = tbItemDescService.geTbItemDescById(itemId);
					// 3.生成静态页面
					this.genHtml("item.ftl", item, itemDesc);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title genHtml
	 *        <ul>
	 * @description 生成静态HTML页面
	 *              </ul>
	 * @createdTime 2017年06月18日
	 * @param templateName 模板名称
	 * @param tbItem       商品信息
	 * @param tbItemDesc   商品详情
	 * @throws Exception
	 * @return void
	 *
	 * @version : V1.0.0
	 */
	private void genHtml(String templateName, TbItem tbItem, TbItemDesc tbItemDesc) throws Exception {
		// 1、从 Spring 容器中获取 Configuration 对象
		Configuration configuration = freeMarkerConfig.getConfiguration();
		// 2、设置模板文件的字符集
		configuration.setDefaultEncoding("UTF-8");
		// 3、首先创建模板文件，再加载模板文件 模板文件的后缀官方统一的标准是.ftl 其实任何类型都行。
		Template template = configuration.getTemplate(templateName);// 可以是<相对路径>，也可以是<绝对路径>
		// 4、创建模板文件需要展示数据的数据集对象，可以使用POJO，也可以使用map 一般是使用map
		Map<String, Object> model = new HashMap<>();
		model.put("item", new TbItemVO(tbItem));
		model.put("itemDesc", tbItemDesc);
		// 5、创建一个FileWriter对象 指定生成的静态文件的文件路径及文件名
		// 拼接一个前缀和后缀（注意，后面优化的时候，需要将路径写到配置文件中）
		String win_pre_file_path = "D:\\fremarker\\"; // windows 路径
		// String linux_pre_file_path = "/home/huazai/freemarker/item"; // linux 路径（注意授权）
		FileWriter writer = new FileWriter(new File(win_pre_file_path + tbItem.getId() + ".html"));
		// 6、调用模板对象的process方法，执行输出文件。
		template.process(model, writer);
		// 7、关闭流
		writer.close();
	}

}
