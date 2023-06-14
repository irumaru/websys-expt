package db;

import java.util.ArrayList;
import java.util.List;

public class AppearMain {
	void insert(int number, int shicode) {
		AppearDAO appearDAO = new AppearDAO();
		boolean success = appearDAO.insert(number, shicode, 2023, 6, 14, 13, 18, 0);
		//boolean success = appearDAO.delete(5);
		System.out.println(success);
	}
	
	void findAll() {
		AppearDAO appearDAO = new AppearDAO();
		
		List<Appear> list = new ArrayList<>();
		
		list = appearDAO.findAll();
		//id, number, name, ken, shi, date, time
		for(Appear a: list) {
			System.out.printf("%-6d %-6s %-20s %-6s %-6s %-10s %-10s\n", a.getId(), a.getNumber(), a.getName(), a.getKen(), a.getShi(), a.getDate(), a.getTime());
		}
		
	}

	public static void main(String args[]) {
		AppearMain main = new AppearMain();
		//main.insert(6, 12206);
		main.findAll();
	}
}
