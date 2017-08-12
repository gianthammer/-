package SearchTest;

/**
 * 检索请求类
 * @author sdu20
 *
 */
public class SearchRequest {

	private int startindex;	//新页面的起始索引号
	private String query;//用户输入的查询关键字
	
	public int getStartindex(){
		return startindex;
	}
	
	public void setStartindex(int startindex){
		this.startindex = startindex;
	}
	
	public String getQuery(){
		return query;
	}
	
	public void setQuery(String query){
		this.query = query;
	}
}
