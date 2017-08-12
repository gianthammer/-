package Index;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

/**
 * ����Lucene�����ṹ
 * @author sdu20
 *
 */
public class NewsDocument {
	
	private static final String NEWS_ID = "newsId";	//�����������ݿ��е�ID
	private static final String NEWS_URL = "newsUrl";	//���ŵ���ַ
	private static final String NEWS_TITLE = "newsTitle";	//���ű���
	private static final String NEWS_DATE = "newsDate";	//���ŷ�����ʱ��
	private static final String NEWS_BODY = "newsBody";	//��������
	private static final String INDEX_TIME = "indexTime";	//���ű�����������ʱ��
	private static final String NEWS_DATE2 = "newsDate2";	//���ŷ�����ʱ��
	
	public static Document buildNewsDocument(News news, Long id){
		Document doc = new Document();
		
		Field identifier = new Field(NEWS_ID,id+"",Field.Store.YES,Field.Index.UN_TOKENIZED);
		Field newsurl = new Field(NEWS_URL,news.getUrl(),Field.Store.YES,Field.Index.UN_TOKENIZED);
		Field newstitle = new Field(NEWS_TITLE,news.getTitle(),Field.Store.YES,Field.Index.TOKENIZED);
		Field newsdate = new Field(NEWS_DATE,news.getDate(),Field.Store.YES,Field.Index.TOKENIZED);
		Field newsbody = new Field(NEWS_BODY,news.getBody(),Field.Store.YES,Field.Index.TOKENIZED);
		long mills = System.currentTimeMillis();
		Field indextime = new Field(INDEX_TIME,mills+"",Field.Store.YES,Field.Index.UN_TOKENIZED);
		Field newsdate2 = new Field(NEWS_DATE2,news.getDate().substring(0, 4)+news.getDate().substring(5, 7),Field.Store.YES,Field.Index.TOKENIZED);
		
		doc.add(identifier);
		doc.add(indextime);
		doc.add(newsbody);
		doc.add(newsdate);
		doc.add(newstitle);
		doc.add(newsurl);
		doc.add(newsdate2);
				
		return doc;
	}

}
