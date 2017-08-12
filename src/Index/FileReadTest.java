package Index;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReadTest {
	 /**
     * ���ı��ļ��е����ݶ��뵽buffer��
     * @param buffer buffer
     * @param filePath �ļ�·��
     * @throws IOException �쳣
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // ��������ÿ�ж�ȡ������
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // ��ȡ��һ��
        while (line != null) { // ��� line Ϊ��˵��������
            buffer.append(line); // ��������������ӵ� buffer ��
            buffer.append("\n"); // ��ӻ��з�
            line = reader.readLine(); // ��ȡ��һ��
        }
        reader.close();
        is.close();
    }

    /**
     * ��ȡ�ı��ļ�����
     * @param filePath �ļ�����·��
     * @return �ı�����
     * @throws IOException �쳣
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static String readFile(String filePath) throws IOException {
    	//System.out.println("filepath in FileReadTest: "+filePath);
        StringBuffer sb = new StringBuffer();
        FileReadTest.readToBuffer(sb, filePath);
        return sb.toString();
    }
    
    public static void main(String[] args){
    	
    	FileReadTest test = new FileReadTest();
    	String path = "C:\\hello.html";
    	try {
			String result = readFile(path);
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
}
