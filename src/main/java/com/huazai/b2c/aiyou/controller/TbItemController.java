package com.huazai.b2c.aiyou.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/item")
public class TbItemController
{

//	@Autowired
//	private TbItemService tbItemService;

//	@Autowired
//	private TbItemDescService tbItemDescService;

	@RequestMapping("/{itemId}")
	public String showTbItemInfo(@PathVariable Long itemId, Model model)
	{
		// 通过商品Id，获取商品详情
//		TbItem tbItem = tbItemService.getTbItemById(itemId);
		// 转换视图对象
//		TbItemVO item = new TbItemVO(tbItem);
		// 通过商品Id，获取商品描述
//		TbItemDesc itemDesc = tbItemDescService.geTbItemDescById(itemId);
//		model.addAttribute("item", item);
//		model.addAttribute("itemDesc", itemDesc);
		return "item";
	}
}
