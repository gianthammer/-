package Index;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
/**
 * 将爬取到的网页提取主要内容并转存到txt中
 * 包括URL、新闻标题、发布日期和正文
 * @author sdu20
 *
 */
public class HtmlToTxt {
	
	//private String storePath = "E:\\astro.txt";
	/**
	 * 
	 * @param filePath 存放HTML文件的文件夹目录
	 * @param storePath 要存放txt文件的文件夹目录
	 * @param fileName 要存放的txt文件名
	 * @throws IOException
	 */
	public void extractHtml(String filePath,String storePath,String fileName) throws IOException{
		String title = null;//标题
		String days = null;//日期
		String detail = null;//详细信息
		String body = null;//正文
		
		BufferedWriter bw= null;
		
		try{
			String sumpath = storePath+"\\"+fileName;
			bw = new BufferedWriter(new FileWriter(new File(sumpath)));
			String str = FileReadTest.readFile(filePath);
			
			//System.out.println(fileName.substring(7, 11));
			String url = "http://www.view.sdu.edu.cn/info/"+fileName.substring(7, 11)+"/";
			
			//获取URL
			Pattern pt_url = Pattern.compile("_(.*)_",Pattern.MULTILINE|Pattern.DOTALL);
			Matcher mcurl = pt_url.matcher(fileName);
			while(mcurl.find()){
				url = url + mcurl.group(1).trim()+".htm";
				//System.out.println("url:"+url);
				bw.write(url+"\r\n");
			}
			
			//创建获取标题的正则表达式
			Pattern pt_title = Pattern.compile("<title>(.*)-山东大学新闻网</title>",Pattern.MULTILINE|Pattern.DOTALL);
			Matcher mc = pt_title.matcher(str);
			while(mc.find()){
				title = mc.group(1).trim();
				//System.out.println("title:"+title);
				bw.write(title+"\r\n");
			}
			
			//获取日期
			Pattern pt_date = Pattern.compile("发布日期：(.*)    <span>点击次数",Pattern.MULTILINE|Pattern.DOTALL);
			Matcher mcdate = pt_date.matcher(str);
			while(mcdate.find()){
				days = mcdate.group(1).trim();
				//System.out.println("Date:"+days);
				bw.write(days+"\r\n");
			}			
			
			//获取正文的正则表达式
			Pattern pt_body = Pattern.compile("<div class=\"news_content\" id=\"vsb_content\">(.*)</div><div id=\"div_vote_id\"></div>",Pattern.MULTILINE|Pattern.DOTALL);
			Matcher mc2 = pt_body.matcher(str);
			while(mc2.find()){
				body = mc2.group(1).trim();
				//System.out.println("body:"+body);
				bw.write(body+"\r\n");
			}
			
			
		}catch(PatternSyntaxException e){
			e.printStackTrace();
		}catch(IllegalStateException e){
			e.printStackTrace();
		}finally{
			if(bw != null)
				bw.close();
		}
	}
	
	/**
	 * 将filePath下的HTML文件转化为txt文件并存储在storePath中
	 * @param filepath
	 * @param storePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void transfile(String filepath,String storePath) throws FileNotFoundException, IOException {
        try {

                File file = new File(filepath);
                if (!file.isDirectory()) {
                        System.out.println("路径不正确");
                }
                else if (file.isDirectory()) {
                        System.out.println("文件夹");
                        String[] filelist = file.list();
                        for (int i = 0; i < filelist.length; i++) {                        	
                        	File readfile = new File(filepath + "\\" + filelist[i]);                       	
                        	if (!readfile.isDirectory()) {                   
	                            String path = readfile.getAbsolutePath();                                
	                            String name = readfile.getName();
	                            String subname = name.substring(0, name.length()-5)+".txt";	              
	                            HtmlToTxt txt = new HtmlToTxt();
	                            txt.extractHtml(path, storePath, subname);

                            } else if (readfile.isDirectory()) {                       
                            	transfile(filepath + "\\" + filelist[i],storePath);
                            }
                        }

                }

        } catch (FileNotFoundException e) {
                System.out.println("transfile()   Exception:" + e.getMessage());
                e.printStackTrace();
        }
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HtmlToTxt test = new HtmlToTxt();
		String filepath = "D:\\jiansuo";
		String storepath = "D:\\suoyin";
		try {
			HtmlToTxt.transfile(filepath,storepath);
			System.out.println("文档已全部转化成功");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("失败");
			e.printStackTrace();
		}
	}

}
