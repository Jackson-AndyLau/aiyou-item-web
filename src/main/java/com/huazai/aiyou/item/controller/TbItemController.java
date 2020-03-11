package com.huazai.aiyou.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huazai.aiyou.item.vo.TbItemVO;
import com.huazai.aiyou.manager.pojo.TbItem;
import com.huazai.aiyou.manager.pojo.TbItemDesc;
import com.huazai.aiyou.manager.service.TbItemDescService;
import com.huazai.aiyou.manager.service.TbItemService;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 商品详情 Controller
 *              </ul>
 * @className TbItemController
 * @package com.huazai.b2c.aiyou.controller
 * @createdTime 2017年06月18日
 *
 * @version V1.0.0
 */
@Controller
@RequestMapping("/item")
public class TbItemController
{

	@Autowired
	private TbItemService tbItemService;

	@Autowired
	private TbItemDescService tbItemDescService;

	@RequestMapping(value = "/{itemId}",method = RequestMethod.GET)
	public String showTbItemInfo(@PathVariable Long itemId, Model model)
	{
		// 通过商品Id，获取商品详情
		TbItem tbItem = tbItemService.getTbItemById(itemId);
		// 转换视图对象
		TbItemVO item = new TbItemVO(tbItem);
		// 通过商品Id，获取商品描述
		TbItemDesc itemDesc = tbItemDescService.geTbItemDescById(itemId);
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", itemDesc);
		return "item";
	}
}
