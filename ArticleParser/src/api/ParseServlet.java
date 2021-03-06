package api;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.aylien.textapi.TextAPIException;
import com.aylien.textapi.responses.Article;
import com.aylien.textapi.responses.Summarize;

import aylien.AylienConnection;



/**
 * Servlet implementation class ParseServlet
 */
@WebServlet("/ParseServlet")
public class ParseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final AylienConnection conn = new AylienConnection();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject res = new JSONObject();
		if (request.getParameterMap().containsKey("read_URL")) {
			URL url = new URL(request.getParameter("read_URL"));
			try {
				Article article = conn.getExtraction(url);
				conn.writeArtile(article, res);
			} catch (TextAPIException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				Summarize summary = conn.getSummarize(url);
				conn.writeSummary(summary, res);
			} catch (TextAPIException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		
		RpcParser.writeOutput(response, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject res = new JSONObject();
		if (request.getParameterMap().containsKey("read_URL")) {
			URL url = new URL(request.getParameter("read_URL"));
			try {
				Article article = conn.getExtraction(url);
				conn.writeArtile(article, res);
			} catch (TextAPIException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				Summarize summary = conn.getSummarize(url);
				conn.writeSummary(summary, res);
			} catch (TextAPIException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if (request.getParameterMap().containsKey("write_URL")) {
				URL writeURL = new URL(request.getParameter("write_URL"));
				conn.writeTo(writeURL);
			}
		}
		
		RpcParser.writeOutput(response, res);
	}

}
