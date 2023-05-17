package poker;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CardTest1
 */
@WebServlet("/CardTest1")
public class CardTest1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CardTest1() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); // 文字コードがUTF-8のHTML
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<link href='style.css' rel='stylesheet'");
		out.println("</head>");
		out.println("<img src=\"cards/1.png\">");
		out.println("スペードA");
		out.println("<hr>");
		int card = (int) (Math.random() * 52) + 1;
		out.println("<img src=\"cards/" + card + ".png\">");
		out.println("ランダムなカード");
		out.println("<hr>");
		
		out.println("<div class='cards'>");
		for(int i = 14; i <= 26; i ++) {
			out.println("<img src=\"cards/" + i + ".png\" class='card'>");
		}
		out.println("</div>");
		
		out.println("</html>");
		System.out.println("doGet()"); // コンソールに表示される(動作確認のため)
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost()"); // この例ではdoPost()は実行されないので表示もされない
	}
}
