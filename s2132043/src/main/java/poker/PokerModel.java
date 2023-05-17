package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PokerModel {
	/** ゲーム回数 */
	int games;

	/** 現在のチップ数 初期値は500 */
	int chips;
	/** 山札 */
	List<Integer> deckcards;
	/** 手札 */
	List<Integer> handcards;
	/** 送信ボタンに表示する文字列("交換","次のゲーム"のどちらか */
	String buttonLabel;
	/** プレイヤーへのメッセージ */
	String message;
	
	//カード(1-13)の枚数
	int count[];

	/** コンストラクタ */
	public PokerModel() {
		deckcards = new ArrayList<>();
		handcards = new ArrayList<>();
	}

	/** 一連のゲームを開始する */
	public void reset() {
		games = 0;
		chips = 500;
	}

	/** 次のゲームのためにカードを配りなおす */
	public void nextgame() {
		// 52枚の山札を作る
		deckcards.clear();
		for (int i = 1; i <= 52; i++) {
			deckcards.add(i);
		}
		Collections.shuffle(deckcards);
		// 山札の先頭から5枚抜いて手札にする
		handcards.clear();
		for (int i = 0; i < 5; i++) {
			int n = deckcards.remove(0);
			handcards.add(n);
		}
		// 残りの場札の枚数とカード番号をコンソールに表示する
		System.out.printf("%d: ", deckcards.size());
		for (int n : deckcards) {
			System.out.printf("%d ", n);
		}
		System.out.println();
		message = "交換するカードをチェックしてください";
		buttonLabel = "交換";
		games++;
	}

	/** indexで指定された位置のカードを、山札から補充したカードを置き換える */
	public void change(List<String> index) {
		System.out.println("index=" + index);
		for (String is: index) {
			int i = Integer.parseInt(is);
			int c = deckcards.remove(0); // 山札の先頭を取り除き、
			handcards.set(i, c); // 手札の指定場所に入れる
		}
		evaluate();
		buttonLabel = "次のゲーム";
	}

	/** 役の判別を行い、チップを増減させる */
	public void evaluate() {
		countNumber();

		//ペアのカウント
		//2, 3, 4ペアの数
		int pair[] = new int[3];
		Arrays.fill(pair, 0);

		for (int v : count) {
			for (int i = 0; i < 3; i++) {
				if (v == i + 2) {
					pair[i]++;
				}
			}
		}

		int red = countRed();
		int seven = countSeven();
		int point = 0;
		if (Arrays.equals(pair, new int[] { 0, 0, 1 })) {
			message = "フォーカード";
		} else if (Arrays.equals(pair, new int[] { 1, 1, 0 })) {
			message = "フルハウス";
		} else if (Arrays.equals(pair, new int[] { 0, 1, 0 })) {
			message = "スリーカード";
		} else if (Arrays.equals(pair, new int[] { 2, 0, 0 })) {
			message = "ツーペア";
		} else if (Arrays.equals(pair, new int[] { 1, 0, 0 })) {
			message = "ワンペア";
		} else if (red == 5) {
			message = "レッド";
			point = 50;
		} else if (seven > 0) {
			message = "セブン";
			point = seven * 10;
		} else {
			message = "ハイカード";
			point = -100;
		}
		
		chips += point;
		message += ": " + chips;
	}
	
	void countNumber(){
		//初期化
		count = new int[13];
		Arrays.fill(count, 0);
		
		//各カードの枚数を数える
		for(int v: handcards) {
			count[(v - 1) % 13] ++;
		}
		
		//表示
		System.out.print("各カードの枚数: ");
		for(int v: count) {
			System.out.print(v + " ");
		}
		System.out.println();
	}

	/** 7 の枚数を返す */
	int countSeven() {
		int count = 0;
		for(int v: handcards) {
			if((v - 1) % 13 == (7 - 1)) {
				count ++;
			}
		}
		
		//ディバッグ
		System.out.println("7の枚数: " + count);
		
		return count;
	}

	/** 赤の枚数を返す */
	int countRed() {
		int count = 0;
		for(int v: handcards) {
			if(14 <= v && v <= 39) {
				count ++;
			}
		}
		
		//ディバッグ
		System.out.println("赤の枚数: " + count);
		
		return count;
	}
	
	/** 手札をセットする（テスト用） */
	public void setHandcards(int a, int b, int c, int d, int e) {
		handcards.set(0, a);
		handcards.set(1, b);
		handcards.set(2, c);
		handcards.set(3, d);
		handcards.set(4, e);
	}

	/** JSPから呼び出されるメソッド */
	public int getGames() {
		return games;
	}

	/** 現在のチップ数を返す */
	public int getChips() {
		return chips;
	}

	/** 5枚の手札のうち，i番目のカード番号を返す (i=0～4) */
	public int getHandcardAt(int i) {
		return handcards.get(i);
	}

	/** 送信ボタンのラベルを返す */
	public String getButtonLabel() {
		return buttonLabel;
	}

	/** プレイヤーへのメッセージを返す */
	public String getMessage() {
		return message;
	}
}
