package SearchTest;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
/**
 * 运用布尔搜索对新闻内容进行搜索
 * @author sdu20
 *
 */
public class NewsSearcher {
	
	private final String INDEX_STORE_PATH = "D:\\suoyin\\";
	
	/**
	 * 获取分词结果
	 * @param analyzer
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> getAnalyzerResult(Analyzer analyzer, String s) throws Exception {

		ArrayList<String> result = new ArrayList<>();
		StringReader reader=new StringReader(s);
		TokenStream ts=analyzer.tokenStream(s, reader);

		Token t=ts.next();
		while (t != null) {
			//System.out.print(t.termText()+" ");
			result.add(t.termText());
			t=ts.next();
		}
		return result;
	}
	
	/**
	 * 获取查询结果
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public Document[] getQueryResult(ArrayList<String> keys) throws Exception{
		
		IndexSearcher searcher = new IndexSearcher(INDEX_STORE_PATH);
		BooleanQuery query = new BooleanQuery();
		
		if(keys==null)
			return null;
		
		int key_length = keys.size();
		TermQuery[] term = new TermQuery[key_length];
		for(int i = 0;i<key_length;i++){
			term[i] = new TermQuery(new Term("newsBody",keys.get(i)));
			query.add(term[i],BooleanClause.Occur.MUST);
		}
		
		TopDocs topdocs = searcher.search(query, null,1000 , new Sort(new SortField("newsDate2", SortField.STRING,true)));
		
		ScoreDoc[] scoredocs = topdocs.scoreDocs;
		if(scoredocs.length==0)
			return null;
		
		Document[] documents = new Document[scoredocs.length];
		for(int i = 0;i<scoredocs.length;i++){
			int docid = scoredocs[i].doc;
			documents[i] = searcher.doc(docid);
		}
		
		
		
		return documents;
	}
	
}
