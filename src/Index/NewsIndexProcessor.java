package Index;
import java.io.*;
/**
 * 调用NewsIndex来建立索引
 * @author sdu20
 *
 */
public class NewsIndexProcessor {
	
	private String indexpath = null;	//索引存放目录
	private String txtpath = null;	//文本文件存放目录
	private NewsIndexer indexer = null;
	public final static int SUMMARY_LENGTH = 100;	//正文摘要长度
	public static Long indexid = 0L;
	private static int count = 0;//用于计数
	
	/**
	 * 构造方法
	 * @param indexpath 索引存放目录
	 * @param txtpath 文本文件存放目录
	 */
	public NewsIndexProcessor(String indexpath,String txtpath){
		try {
			this.indexpath = indexpath;
			this.txtpath = txtpath;
			indexer = new NewsIndexer(indexpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void process() throws Exception{
		if(indexer == null){
			throw new Exception("Lucene index failed,please retry");
		}
		
		try{
			File f = new File(txtpath);
			traverse(f);
			
			indexer.close();
			System.out.println("索引建立完毕");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void traverse(File file) throws Exception{
		String[] files = file.list();
		for(int i = 0;i<files.length;i++){
			//System.out.println(i);
			File newsfile = new File(file,files[i]);
			
			String filename = newsfile.getName();
			News news = new News();
			BufferedReader reader = new BufferedReader(new FileReader(newsfile));
			String url = reader.readLine();
			news.setUrl(url);
			String title = null;
			String date = null;
			String body = null;
			
			try{
				title = reader.readLine();
				date = reader.readLine();
				StringBuffer content = new StringBuffer();
				String line = reader.readLine();
				while(line != null){
					content.append(line).append("\r\n");
					line = reader.readLine();
				}
				body = content.toString();
//				if(body.length()>SUMMARY_LENGTH){
//					body = body.substring(0,SUMMARY_LENGTH-1);
//				}
				if(title.length()>1)
					news.setTitle(title);
				else
					news.setTitle("title");
				if(date.length()>1)
					news.setDate(date);
				else
					news.setDate("2001年01月01日");
				if(body.length()>1)
					news.setBody(body);
				else
					news.setBody("内容已被删除");
				
				
			}catch(Exception e){
				news.setTitle("title");
				news.setDate("2001年01月01日");
				news.setBody("内容已被删除");
				continue;
			}
			
			buildIndex(news);
		}
		
		optimizeIndex();
	}

	/**
	 * 创建索引
	 * @param news
	 * @throws Exception
	 */
	private void buildIndex(News news) throws Exception{
		indexer.addNews(news, indexid);
		indexid++;
		count++;//用于计数
		if(count % 1000 ==0)
			System.out.println(count);
	}
	
	/**
	 * 优化索引
	 * @throws Exception
	 */
	private void optimizeIndex() throws Exception{
		indexer.optimizeIndex();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String ipath = "D:\\suoyin";
		String tpath = "D:\\jiansuo\\Index\\";
		NewsIndexProcessor pro = new NewsIndexProcessor(ipath,tpath);
		try {
			pro.process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
