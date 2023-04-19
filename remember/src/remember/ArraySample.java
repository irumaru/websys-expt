package remember;

public class ArraySample {
	int prime[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
	int data[];
	
	public static void main(String args[]) {
		ArraySample sample = new ArraySample();
		
		sample.initData();
		sample.printData();
		sample.printData2();
		
		//sample.swap(0, 4);
		//sample.printData();
		
		//sample.primesum();
		
		sample.shuffle();
		sample.printData();
		
		int r = (int)(Math.random() * 5);
		System.out.println(r);
	}
	
	// 配列dataを初期化する
	void initData() {
		data = new int[prime.length];
		
		for(int i = 0; i < prime.length; i++) {
			data[i] = prime[i];
		}
	}
	
	// 配列dataの全ての要素を表示
	void printData() {
		for(int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "  ");
		}
		System.out.println();
	}
	
	// 拡張for文を使った書き方
	void printData2() {
		for(int d: data) {
			System.out.print(d + "  ");
		}
		System.out.println();
	}
	
	// 配列dataのx番目とy番目の要素を入れ替える
	void swap(int x, int y) {
		int temp = data[x];
		data[x] = data[y];
		data[y] = temp;
	}
	
	// 課題(6)
	void primesum() {
		int ps[] = new int[prime.length - 1];
		
		for(int i = 0; i < ps.length; i ++) {
			ps[i] = prime[i] + prime[i + 1];
		}
		
		for(int d: ps) {
			System.out.print(d + "  ");
		}
		System.out.println();
	}
	
	// 課題(7)
	void shuffle() {
		for(int i = 0; i < data.length; i ++) {
			this.swap(i, (int)(Math.random() * (prime.length)));
		}
	}
}
