package SearchTest;

/**
 * ����������
 * @author sdu20
 *
 */
public class SearchRequest {

	private int startindex;	//��ҳ�����ʼ������
	private String query;//�û�����Ĳ�ѯ�ؼ���
	
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
