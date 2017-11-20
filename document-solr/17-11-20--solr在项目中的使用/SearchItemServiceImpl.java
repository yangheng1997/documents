package cn.e3mall.search.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.search.mapper.ItemMapper;
import cn.e3mall.search.service.SearchItemService;

/**
 * 索引库维护Service
 * 
 * @author yang
 *
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Resource
	private ItemMapper itemMapper;

	@Resource
	private SolrServer solrServer;

	/**
	 * 从数据库中取出需要被索引库维护的数据，并导入的索引库
	 */
	@Override
	public E3Result importAllItems() {
		try {
			List<SearchItem> list = itemMapper.getItemList();

			// 将list中的数据添加到solrDocument 中
			for (SearchItem searchItem : list) {
				//创建文档对象
				SolrInputDocument document = new SolrInputDocument();
				//向文档对象中添加域
				document.setField("id", searchItem.getId());
				document.addField("item_title", searchItem.getTitle());
				document.addField("item_sell_point", searchItem.getSell_point());
				document.addField("item_price", searchItem.getPrice());
				document.addField("item_image", searchItem.getImage());
				document.addField("item_category_name", searchItem.getCategory_name());
				// 将文章写入索引库，
				solrServer.add(document);
			}
			//提交
			solrServer.commit();
			//返回 导入成功
			return E3Result.ok();
		} catch (Exception e) {	
			e.printStackTrace();
			return E3Result.build(500, "数据导入时发生异常");
			
		}

		
	}

}
