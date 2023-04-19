package remember;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListSample {
	int prime[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 }; // 素数を格納する配列の初期化

	List<Integer> data = new ArrayList<>(); // 空のリスト

	public static void main(String args[]) {
		ListSample sample = new ListSample();

		sample.initData();
		sample.printData();
		sample.printData2();

		sample.swap(0, 4);
		sample.printData();

		sample.primesum();
		
		sample.shuffle();
		sample.printData();
	}

	/** リスト data を初期化する(配列 primedata[]の値をコピーする) */
	void initData() {
		for (int i=0; i<prime.length; i++) { // 1 つずつ値をコピーする
			data.add(prime[i]);
		}
	}

	/** リスト data の全ての要素を表示する */
	void printData() {
		for (int i=0; i<data.size(); i++) {
			System.out.print(data.get(i) + " ");
		}
		System.out.println();
	}

	/** 拡張 for 文を使った書き方 */
	void printData2() {
		for (int d: data) {
			System.out.print(d + " ");
		}
		System.out.println();
	}

	/** リスト data の x 番目の要素と y 番目の要素を入れ替える */
	void swap(int x, int y) {
		Collections.swap(data, x, y);
	}

	/** 課題(8) */
	void primesum() {
		List<Integer> ps = new ArrayList<>();
		
		for(int i = 0; i < prime.length - 1; i ++) {
			ps.add(prime[i] + prime[i + 1]);
		}
		
		for(int d: ps) {
			System.out.print(d + " ");
		}
		System.out.println();
	}

	/** 課題(9) */
	void shuffle() {
		// リスト data の要素をランダムな順序に並べ替える
		Collections.shuffle(data);
	}
}
