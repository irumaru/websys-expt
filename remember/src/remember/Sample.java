package remember;

public class Sample {
	public static void main(String args[]) {
		Sample sample = new Sample();
		sample.hello();
		sample.morning("サトシ");
		sample.printAllCodes();
		sample.printOddCodes();
		sample.printSpadeCodes();
		sample.printDiamondCodes();
		sample.printNot3Codes1();
		sample.printNot3Codes2();
		sample.printNot23Codes1();
		sample.printNot23Codes2();
		System.out.println(sample.code2num(42));
		System.out.println(sample.code2suit(52));
	}
	
	void hello() {
		System.out.println("Hello, world");
	}
	
	void morning(String name) {
		System.out.println("おはよう" + name);
	}
	
	void printAllCodes() {
		for(int i = 1; i <= 52; i ++) {
			System.out.print(i + " ");
		}
		
		System.out.println();
	}
	
	void printOddCodes() {
		for(int i = 1; i <= 52; i ++) {
			if(i % 2 == 1) {
				System.out.print(i + " ");
			}
		}
		
		System.out.println();
	}
	
	void printSpadeCodes() {
		for(int i = 1; i <= 13; i ++) {
			System.out.print(i + " ");
		}
		
		System.out.println();
	}
	
	void printDiamondCodes() {
		for(int i = 27; i <= 39; i ++) {
			System.out.print(i + " ");
		}
		
		System.out.println();
	}
	
	void printNot3Codes1() {
		for(int i = 1; i <= 52; i ++) {
			if(i % 3 != 0) {
				System.out.print(i + " ");
			}
		}
		
		System.out.println();
	}
	
	void printNot3Codes2() {
		for(int i = 1; i <= 52; i ++) {
			if(!(i % 3 == 0)) {
				System.out.print(i + " ");
			}
		}
		
		System.out.println();
	}
	
	void printNot23Codes1() {
		for(int i = 1; i <= 52; i ++) {
			if(i % 2 != 0 && i % 3 != 0) {
				System.out.print(i + " ");
			}
		}
		
		System.out.println();
	}
	
	void printNot23Codes2() {
		for(int i = 1; i <= 52; i ++) {
			if(!(i % 2 == 0 || i % 3 == 0)) {
				System.out.print(i + " ");
			}
		}
		
		System.out.println();
	}
	
	int code2num(int code) {
		return (code - 1) % 13 + 1;
	}
	
	String code2suit(int code) {
		String[] name = {"スペド", "ハート", "ダイア", "クロバ"};
		
		return name[(code - 1) / 13];
	}
}
