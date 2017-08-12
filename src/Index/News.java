package Index;
/**
 * –¬Œ≈¿‡
 * @author sdu20
 *
 */
public class News {

	private String url;
	private String title;
	private String date;
	private String body;
	
	public News(){
		url = null;
		title = null;
		date = null;
		body = null;
	}
	
	public News(String url,String title,String date,String body){
		this.url = url;
		this.title = title;
		this.date = date;
		this.body = body;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setBody(String body){
		this.body = body;
	}
	
	public String getBody(){
		return body;
	}
}
