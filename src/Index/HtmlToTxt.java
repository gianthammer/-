package Index;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
/**
 * ����ȡ������ҳ��ȡ��Ҫ���ݲ�ת�浽txt��
 * ����URL�����ű��⡢�������ں�����
 * @author sdu20
 *
 */
public class HtmlToTxt {
	
	//private String storePath = "E:\\astro.txt";
	/**
	 * 
	 * @param filePath ���HTML�ļ����ļ���Ŀ¼
	 * @param storePath Ҫ���txt�ļ����ļ���Ŀ¼
	 * @param fileName Ҫ��ŵ�txt�ļ���
	 * @throws IOException
	 */
	public void extractHtml(String filePath,String storePath,String fileName) throws IOException{
		String title = null;//����
		String days = null;//����
		String detail = null;//��ϸ��Ϣ
		String body = null;//����
		
		BufferedWriter bw= null;
		
		try{
			String sumpath = storePath+"\\"+fileName;
			bw = new BufferedWriter(new FileWriter(new File(sumpath)));
			String str = FileReadTest.readFile(filePath);
			
			//System.out.println(fileName.substring(7, 11));
			String url = "http://www.view.sdu.edu.cn/info/"+fileName.substring(7, 11)+"/";
			
			//��ȡURL
			Pattern pt_url = Pattern.compile("_(.*)_",Pattern.MULTILINE|Pattern.DOTALL);
			Matcher mcurl = pt_url.matcher(fileName);
			while(mcurl.find()){
				url = url + mcurl.group(1).trim()+".htm";
				//System.out.println("url:"+url);
				bw.write(url+"\r\n");
			}
			
			//������ȡ�����������ʽ
			Pattern pt_title = Pattern.compile("<title>(.*)-ɽ����ѧ������</title>",Pattern.MULTILINE|Pattern.DOTALL);
			Matcher mc = pt_title.matcher(str);
			while(mc.find()){
				title = mc.group(1).trim();
				//System.out.println("title:"+title);
				bw.write(title+"\r\n");
			}
			
			//��ȡ����
			Pattern pt_date = Pattern.compile("�������ڣ�(.*)    <span>�������",Pattern.MULTILINE|Pattern.DOTALL);
			Matcher mcdate = pt_date.matcher(str);
			while(mcdate.find()){
				days = mcdate.group(1).trim();
				//System.out.println("Date:"+days);
				bw.write(days+"\r\n");
			}			
			
			//��ȡ���ĵ�������ʽ
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
	 * ��filePath�µ�HTML�ļ�ת��Ϊtxt�ļ����洢��storePath��
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
                        System.out.println("·������ȷ");
                }
                else if (file.isDirectory()) {
                        System.out.println("�ļ���");
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
			System.out.println("�ĵ���ȫ��ת���ɹ�");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ʧ��");
			e.printStackTrace();
		}
	}

}
