package SearchTest;

import java.util.*;

/**
 * 结果集合类
 * @author sdu20
 *
 */
public class SearchResults {

	private ArrayList results = new ArrayList();//保存了所有结果的ID号
	private int startIndex;	//当前页起始的索引号
	private int minpage;	//当前页中所要显示的最小页
	private int maxpage;	//当前页中所要显示的最大页
	private int hasnext;	//还有比maxpage更大的页数吗
	
	public int getHasnext(){
		return hasnext;
	}
	
	public void setHasnext(int hasnext){
		this.hasnext = hasnext;
	}
	
	public int getMaxpage(){
		return maxpage;
	}
	
	public void setMaxpage(int maxpage){
		this.maxpage = maxpage;
	}
	
	public ArrayList getResults(){
		return results;
	}
	
	public void setResults(ArrayList result){
		this.results = result;
	}
	
	public void setStartindex(int start){
		this.startIndex = start;
	}
	
	public int getStartindex(){
		return startIndex;
	}
}
