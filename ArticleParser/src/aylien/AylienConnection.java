package aylien;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.aylien.textapi.TextAPIClient;
import com.aylien.textapi.TextAPIException;
import com.aylien.textapi.parameters.ExtractParams;
import com.aylien.textapi.parameters.SummarizeParams;
import com.aylien.textapi.responses.Article;
import com.aylien.textapi.responses.Summarize;

public class AylienConnection {
	private static TextAPIClient client;
	public AylienConnection() {
		AylienConnection.client = new TextAPIClient(AylienUtil.API_ID, AylienUtil.API_KEY);
	}
	public AylienConnection(String id, String key) {
		AylienConnection.client = new TextAPIClient(id, key);
	}
	
	public Article getExtraction(URL url) throws TextAPIException {
		ExtractParams.Builder builder = ExtractParams.newBuilder();
		builder.setUrl(url);
		Article article = client.extract(builder.build());
		return article;
	}
	public Summarize getSummarize(URL url) throws TextAPIException {
		SummarizeParams.Builder builder = SummarizeParams.newBuilder();
		builder.setUrl(url);
		builder.setNumberOfSentences(3);
		Summarize summary = client.summarize(builder.build());
		return summary;
	}
	public void writeArtile(Article article, JSONObject obj) throws JSONException {
		obj.put("Author", article.getAuthor());
		obj.put("Title", article.getTitle());
		obj.put("Date", article.getPublishDate());
	}
	public void writeSummary(Summarize summary, JSONObject obj) throws JSONException {
		obj.put("Description_and_Summary", summary.getSentences());
	}
	public void writeTo(URL url) {
		
	}
//	public static void main(String[] s) throws MalformedURLException, TextAPIException {
//		AylienConnection conn = new AylienConnection();
//		URL url = new URL("http://techcrunch.com/2015/04/06/john-oliver-just-changed-the-surveillance-reform-debate");
//		Article a = conn.getExtraction(url);
//		System.out.println(a);
//	}
}
