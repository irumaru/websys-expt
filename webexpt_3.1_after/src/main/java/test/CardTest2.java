package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CardTest2
 */
@WebServlet("/CardTest2")
public class CardTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CardTest2() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 受信内容の文字コード
		response.setContentType("text/html; charset=UTF-8"); // 送信内容の文字コード
		// 受け取った内容を変数に入れる
		String suit = request.getParameter("suit");
		String number[] = request.getParameterValues("number");
		String color = request.getParameter("color");
		String query = request.getParameter("query");
		// コンソールへの出力
		System.out.println("--------");
		List<String> numberlist = null;
		if (number == null) {
			numberlist = new ArrayList<>();
		} else {
			numberlist = Arrays.asList(number);
		}
		System.out.println(numberlist);
		System.out.println("color=" + color);
		System.out.println("query=" + query);
		// ブラウザへの出力
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println(suit);
		out.println("<hr>");
		out.println(numberlist);
		out.println("<hr>");
		out.println(color);
		out.println("<hr>");
		out.println(query);
		
		out.println("<hr>");
		boolean suitFlag[] = new boolean[53];
		boolean numberFlag[] = new boolean[53];
		boolean colorFlag[] = new boolean[53];

		// 記号による選別
		if (suit.equals("all")) {
			for (int i = 1; i <= 52; i++) {
				suitFlag[i] = true;
			}
		} else if (suit.equals("spade")) {
			for (int i = 1; i <= 13; i++) {
				suitFlag[i] = true;
			}
		} else if (suit.equals("heart")) {
			for (int i = 14; i <= 26; i++) {
				suitFlag[i] = true;
			}
		} else if (suit.equals("dia")) {
			for (int i = 27; i <= 39; i++) {
				suitFlag[i] = true;
			}
		} else if (suit.equals("club")) {
			for (int i = 40; i <= 52; i++) {
				suitFlag[i] = true;
			}
		}

		// 数字による選別
		if(numberlist.contains("0")) {
			for(int i = 1; i <= 52; i ++) {
				numberFlag[i] = true;
			}
		}else {
			for(int i = 1; i <= 52; i ++) {
				int num = ((i - 1) % 13) + 1;
				if(numberlist.contains("" + num)) {
					numberFlag[i] = true;
				}
			}
		}

		// 色による選別
		for(int i = 1; i <= 52; i ++) {
			if(color.equals("red") && 14 <= i && i <= 39) {
				colorFlag[i] = true;
			}else if(color.equals("black") && (i <= 13 || 40 <= i)) {
				colorFlag[i] = true;
			}else if(color.equals("both")) {
				colorFlag[i] = true;
			}
		}
		
		// カードの表示
		for (int i = 1; i <= 52; i++) {
			if (suitFlag[i] && numberFlag[i] && colorFlag[i]) {
				String filename = "cards/" + i + ".png";
				out.printf("<img src=\"%s\" width=\"100\" height=\"150\">\n", filename);
			}
		}

		out.println("</html>");
	}
}
