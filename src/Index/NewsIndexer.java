package Index;

import org.apache.lucene.analysis.*;
import org.apache.lucene.index.*;

import jeasy.analysis.*;

/**
 * �����࣬������Lucene���������Document
 * @author sdu20
 *
 */
public class NewsIndexer {

	private String indexPath = "";//�������λ��
	private IndexWriter writer = null;
	private Analyzer analyzer = null;
	
	public NewsIndexer(String indexPath) throws Exception{
		this.indexPath = indexPath;
		initialize();
	}
	
	/**
	 * ��ʼ��
	 * @throws Exception
	 */
	public void initialize() throws Exception{
		analyzer = new MMAnalyzer();
		writer = new IndexWriter(indexPath,analyzer,true);
	}
	
	/**
	 * �ر�IndexWriter
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
	 * �������м����ĵ�
	 * @param news
	 * @param id
	 * @throws Exception
	 */
	public void addNews(News news,Long id) throws Exception{
		writer.addDocument(NewsDocument.buildNewsDocument(news, id));
	}
	
	/**
	 * �Ż�����
	 * @throws Exception
	 */
	public void optimizeIndex() throws Exception{
		writer.optimize();
	}
}
