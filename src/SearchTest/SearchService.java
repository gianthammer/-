package SearchTest;

/**
 * ����������ӿ�
 * @author sdu20
 *
 */
public interface SearchService {

	//����һ��SearchResults
	public abstract SearchResults getSearchResults(SearchRequest request);
	
	//���ص������
	public abstract SearchResult getSearchResultById(int id);
}
