package SearchTest;

import java.util.*;

/**
 * ���������
 * @author sdu20
 *
 */
public class SearchResults {

	private ArrayList results = new ArrayList();//���������н����ID��
	private int startIndex;	//��ǰҳ��ʼ��������
	private int minpage;	//��ǰҳ����Ҫ��ʾ����Сҳ
	private int maxpage;	//��ǰҳ����Ҫ��ʾ�����ҳ
	private int hasnext;	//���б�maxpage�����ҳ����
	
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
