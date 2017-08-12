package SearchTest;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.*;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;

import jeasy.analysis.MMAnalyzer;
/**
 * 一个简单的搜索尝试
 * @author sdu20
 *
 */
public class IndexSeacherTest1 {

	public static void main(String[] args){
		try {
			IndexSearcher searcher = new IndexSearcher("D:\\jiansuo\\");
			Term t = new Term("newsTitle","李克强");
			Query q = new TermQuery(t);
			
			Hits hits = searcher.search(q);
		
			System.out.println("共找到"+hits.length()+"条");	
			if(hits.length()>0)
				System.out.println(hits.doc(0));


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
