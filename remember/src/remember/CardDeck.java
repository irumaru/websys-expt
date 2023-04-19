package remember;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
	List<Integer> cards; // 要素が Integer の ArrayList を保持する変数
	
	public static void main(String args[]) {
		CardDeck deck = new CardDeck();
		System.out.println(deck.getCodeAt(3));
		System.out.println(deck.getSize());
		
		int c1 = deck.getCodeAt(0);
		System.out.println(code2label(c1));
		int c2 = deck.getCodeAt(2);
		System.out.println(code2label(c2));
		deck.print();
		
		deck.takeCard();
		deck.print();
		
		deck.shuffle();
		deck.print();
		deck.takeCard();
		deck.print();
	}
	
	public CardDeck() {
		cards = new ArrayList<>(); // 要素数 0 の ArrayList 生成
		/*
		cards.add(1); // スペード 1 を追加
		cards.add(15); // ハート 2 〃
		cards.add(31); // ダイヤ 5 〃
		cards.add(49); // クラブ 10 〃
		cards.add(52); // クラブ K 〃 ここまでで cards の要素数=5
		*/
		for(int i = 1; i <= 52; i ++) {
			cards.add(i);
		}
	}
	/** 全てのカードを"SA H2 D5 CT CK"の形式で表示する */
	public void print() {
		for (int i=0; i<cards.size(); i++) { // i を 0 から(要素数-1)まで変化させる
			int c = cards.get(i);
			System.out.print(code2label(c) + " ");
		}
		System.out.println();
	}

	/** カードデッキの残り枚数(cards の要素数)を返す */
	public int getSize() {
		return cards.size();
	}
	/** カードデッキの x 番目のコード(リストの x 番目の要素)を返す */
	public int getCodeAt(int x) {
		return cards.get(x);
	}
	/** code で指定されたカード(1～52)を 2 文字で返す("SA", "H2", "D5", "CT", "CK"など) */
	private static String code2label(int code) {
		char top, bottom;
		char topList[] = {'S', 'H', 'D', 'C'};
		char bottomList[] = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
		
		top = topList[(code - 1) / 13];
		bottom = bottomList[(code - 1) % 13];
		
		return "" + top + bottom; // int を String にする方法
	}
	/** デッキをランダムに並べなおす */
	public void shuffle() {
		Collections.shuffle(cards);
	}
	/** デッキから 1 枚抜き取り，そのカード番号(1～52)を返す */
	public int takeCard() {
		int v = cards.get(0);
		cards.remove(0);
		
		return v;
	}
	
}
