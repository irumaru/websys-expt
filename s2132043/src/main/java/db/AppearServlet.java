package db;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AppearServlet")
public class AppearServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AppearServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String item = request.getParameter("item"); // 並び替えの項目
		String order = request.getParameter("order"); // asc:昇順 desc:降順
		String mode = request.getParameter("mode"); // "並び替え" "登録" "削除"
		String newnumber = request.getParameter("newnumber"); // 登録するポケモン番号
		String newshicode = request.getParameter("newshicode"); // 登録する市コード
		String newname = request.getParameter("newname");
		String newregion = request.getParameter("newregion");// 県と市を入力
		String arrivalDate = request.getParameter("arrivalDate"); //日を入力
		String arrivalTime = request.getParameter("arrivalTime"); //時間を入力
		String deleteid = request.getParameter("deleteid"); // 削除するID
		String shimei = request.getParameter("shimei"); // 市名をクリックした場合
		
		System.out.printf("\n%s:%s:%s:\n", item, order, mode);
		System.out.printf("%s:%s:\n", newnumber, newshicode);
		System.out.printf("Name: %s, Region: %s\n", newname, newregion);
		System.out.printf("%s:%s:\n", deleteid, shimei);
		System.out.printf("ArrivalDate: %s, ArrivalTime: %s\n", arrivalDate, arrivalTime);
		
		if (mode != null) {
			if (mode.equals("並び替え")) { // この場合は特に何もしない
			} else if (mode.equals("登録")) {
				int space;
				for(space = 0; space < 1000; space ++) {
					if(newregion.charAt(space) == ' ') {
						break;
					}
				}
				
				String kenName = newregion.substring(0, space);
				String shiName = newregion.substring(space + 1);
				
				String pokemoncode = getPokemonCode(newname);
				String shicode = getShicode(kenName, shiName);
				
				System.out.printf("SHI: %s, NAME: %s\n", shicode, pokemoncode);
				
				insert(pokemoncode, shicode, arrivalDate, arrivalTime + ":00");
			} else if (mode.equals("削除")) {
				delete(deleteid);
			}
		}
		
		selectAll(request, response, item, order);
		selectAllPokemon(request, response);
		selectAllRegion(request, response);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/appear.jsp");
		dispatcher.forward(request, response);
	}

	/** サーブレットがPOSTメソッドで実行された場合でもdoGet()で処理する */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/** DAOを呼び出す */
	void selectAll(HttpServletRequest request, HttpServletResponse response, String item, String order)
			throws ServletException {
		AppearDAO appearDAO = new AppearDAO();
		List<Appear> list = appearDAO.findAll(item, order);
		request.setAttribute("appearList", list);
	}
	
	/** 登録されているポケモンリストを呼び出す **/
	void selectAllPokemon(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		PokemonDAO pokemonDAO = new PokemonDAO();
		List<Pokemon> list = pokemonDAO.findAll();
		request.setAttribute("pokemonList", list);
	}
	
	void selectAllRegion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		RegionDAO regionDAO = new RegionDAO();
		List<Region> list = regionDAO.findAll();
		request.setAttribute("regionList", list);
	}
	
	String getShicode(String kencode, String shicode) {
		RegionDAO regionDAO = new RegionDAO();
		return regionDAO.getShicode(kencode, shicode);
	}
	
	String getPokemonCode(String name) {
		RegionDAO regionDAO = new RegionDAO();
		return regionDAO.getPokemonCode(name);
	}

	/** DAOを呼び出す */
	void insert(String newnumber, String newshicode, String arrivalDate, String arrivalTime) {
		try {
			int num = Integer.parseInt(newnumber);
			int code = Integer.parseInt(newshicode);
			// Date format %4d-%02d-%02d
			Date date = Date.valueOf(arrivalDate);
			// Time format %02d:%02d:%02d
			Time time = Time.valueOf(arrivalTime);
			
			AppearDAO appear = new AppearDAO();
			boolean status = appear.insert(num, code, date, time);
			
			System.out.println("AppearDAOは" + status + "で終了しました。");
		} catch (NumberFormatException e) {
			System.out.println("不正な番号または市コードが入力されました" + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("不正な日付が入力されました" + e.getMessage());
		}
	}

	/** DAOを呼び出す */
	void delete(String deleteid) {
		try {
			int id = Integer.parseInt(deleteid);
			
			AppearDAO appear = new AppearDAO();
			boolean status = appear.delete(id);
			
			System.out.println("AppearDAOは" + status + "で終了しました。");
		} catch (NumberFormatException e) {
			System.out.println("不正なIDが入力されました" + e.getMessage());
		}
	}
}
