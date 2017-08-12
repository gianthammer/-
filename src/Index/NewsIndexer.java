package Index;

import org.apache.lucene.analysis.*;
import org.apache.lucene.index.*;

import jeasy.analysis.*;

/**
 * 索引类，用于向Lucene索引中添加Document
 * @author sdu20
 *
 */
public class NewsIndexer {

	private String indexPath = "";//索引存放位置
	private IndexWriter writer = null;
	private Analyzer analyzer = null;
	
	public NewsIndexer(String indexPath) throws Exception{
		this.indexPath = indexPath;
		initialize();
	}
	
	/**
	 * 初始化
	 * @throws Exception
	 */
	public void initialize() throws Exception{
		analyzer = new MMAnalyzer();
		writer = new IndexWriter(indexPath,analyzer,true);
	}
	
	/**
	 * 关闭IndexWriter
	 */
	public void close(){
		try{
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
			writer = null;
		}
	}
	
	/**
	 * 向索引中加入文档
	 * @param news
	 * @param id
	 * @throws Exception
	 */
	public void addNews(News news,Long id) throws Exception{
		writer.addDocument(NewsDocument.buildNewsDocument(news, id));
	}
	
	/**
	 * 优化索引
	 * @throws Exception
	 */
	public void optimizeIndex() throws Exception{
		writer.optimize();
	}
}
