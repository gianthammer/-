package servlet;

import java.util.*;
import java.util.Date;
import java.io.IOException;
import java.sql.*; //导入数据库处理所有库
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

//用户登录处理Servlet 系统登录主页处理表单
public class HomePageAction extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection cn=null;     //定义数据库连接对象
	
	//初始化方法，取得数据库连接对象
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);

	}
	
	//处理GET请求方法
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{

		//查询数据库和跳转到登录主界面
		try {
			//查询数据库操作
			//跳转到主界面

	        String input = request.getParameter("inputMessage");
	        if(input==null||input.trim().length()==0) {  
	        	response.sendRedirect("index.jsp"); 
	        	
	        }else{
	        
		        
		        Date begintime = new Date();
					
		        NewsSearcher searcher = new NewsSearcher();
		        ArrayList<String> str = searcher.getAnalyzerResult(new MMAnalyzer(), input);
				Document[] results = searcher.getQueryResult(str);
				
					
				if(results.length==0){
					request.setAttribute("noResult","没有找到相关内容");
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
			System.out.println("错误："+e.getMessage());
			response.sendRedirect("index.jsp");
		}
	}
	
	//处理POST请求方法
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException 
	{
		doGet(request,response);
	}
	
	//销毁方法
	public void destroy() {
		super.destroy();
		try {
			cn.close();
		}catch(Exception e) {
			System.out.println("关闭数据库错误："+e.getMessage());
		}
	}		
}