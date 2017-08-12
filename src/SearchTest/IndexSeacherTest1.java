package SearchTest;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.*;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;

import jeasy.analysis.MMAnalyzer;
/**
 * һ���򵥵���������
 * @author sdu20
 *
 */
public class IndexSeacherTest1 {

	public static void main(String[] args){
		try {
			IndexSearcher searcher = new IndexSearcher("D:\\jiansuo\\");
			Term t = new Term("newsTitle","���ǿ");
			Query q = new TermQuery(t);
			
			Hits hits = searcher.search(q);
		
			System.out.println("���ҵ�"+hits.length()+"��");	
			if(hits.length()>0)
				System.out.println(hits.doc(0));


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
