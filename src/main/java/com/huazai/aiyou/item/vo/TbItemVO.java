package com.huazai.aiyou.item.vo;

import com.huazai.aiyou.manager.pojo.TbItem;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 商品详情视图对象
 *              </ul>
 * @className TbItemVO
 * @package com.huazai.b2c.aiyou.vo
 * @createdTime 2017年06月18日
 *
 * @version V1.0.0
 */
public class TbItemVO extends TbItem
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String[] getImages()
	{
		String image = this.getImage();
		if (image != null && !image.equals(""))
		{
			String[] images = image.split(",");
			return images;
		}
		return null;
	}

	public TbItemVO()
	{

	}

	public TbItemVO(TbItem tbItem)
	{
		this.setBarcode(tbItem.getBarcode());
		this.setCid(tbItem.getCid());
		this.setCreated(tbItem.getCreated());
		this.setId(tbItem.getId());
		this.setImage(tbItem.getImage());
		this.setNum(tbItem.getNum());
		this.setPrice(tbItem.getPrice());
		this.setSellPoint(tbItem.getSellPoint());
		this.setStatus(tbItem.getStatus());
		this.setTitle(tbItem.getTitle());
		this.setUpdated(tbItem.getUpdated());
	}

}
