package servlet;

import java.util.*;
import java.util.Date;
import java.io.IOException;
import java.sql.*; //�������ݿ⴦�����п�
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;

import SearchTest.*;

import jeasy.analysis.MMAnalyzer;

//�û���¼����Servlet ϵͳ��¼��ҳ�����
public class HomePageAction extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection cn=null;     //�������ݿ����Ӷ���
	
	//��ʼ��������ȡ�����ݿ����Ӷ���
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);

	}
	
	//����GET���󷽷�
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{

		//��ѯ���ݿ����ת����¼������
		try {
			//��ѯ���ݿ����
			//��ת��������

	        String input = request.getParameter("inputMessage");
	        if(input==null||input.trim().length()==0) {  
	        	response.sendRedirect("index.jsp"); 
	        	
	        }else{
	        
		        
		        Date begintime = new Date();
					
		        NewsSearcher searcher = new NewsSearcher();
		        ArrayList<String> str = searcher.getAnalyzerResult(new MMAnalyzer(), input);
				Document[] results = searcher.getQueryResult(str);
				
					
				if(results.length==0){
					request.setAttribute("noResult","û���ҵ��������");
					RequestDispatcher dispatcher = request.getRequestDispatcher("noresult.jsp");
			        dispatcher.forward(request, response);
				}else{
					int totalnum = results.length;
					
					request.setAttribute("newsNum",""+totalnum);
						
					for(int i = 0;i<totalnum;i++){	
 
						request.setAttribute("newsTitle"+i,results[i].getField("newsTitle").stringValue());
					    request.setAttribute("newsUrl"+i, results[i].getField("newsUrl").stringValue());
					    request.setAttribute("newsBody"+i, results[i].getField("newsBody").stringValue());
					    request.setAttribute("newsDate"+i, results[i].getField("newsDate").stringValue());
					}
						
					Date endtime = new Date();
					long timeOfsearch = endtime.getTime()-begintime.getTime();
					request.setAttribute("searchTime",""+timeOfsearch);
				        
			        	        	        	   
			        RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
			        dispatcher.forward(request, response);
				}
				
	
		        
	        }
	        //System.out.println("demo5");

			//response.sendRedirect("success.jsp");
		} catch(Exception e) {
			System.out.println("����"+e.getMessage());
			response.sendRedirect("index.jsp");
		}
	}
	
	//����POST���󷽷�
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException 
	{
		doGet(request,response);
	}
	
	//���ٷ���
	public void destroy() {
		super.destroy();
		try {
			cn.close();
		}catch(Exception e) {
			System.out.println("�ر����ݿ����"+e.getMessage());
		}
	}		
}