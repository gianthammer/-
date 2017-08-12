package SearchTest;

/**
 * 索引检索类接口
 * @author sdu20
 *
 */
public interface SearchService {

	//返回一个SearchResults
	public abstract SearchResults getSearchResults(SearchRequest request);
	
	//返回单个结果
	public abstract SearchResult getSearchResultById(int id);
}
