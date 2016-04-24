package com.kamesuta.mc.worldpictures.component.builder.square;

import com.kamesuta.mc.worldpictures.component.Square;
import com.kamesuta.mc.worldpictures.component.builder.Vector3f;

/**
 * 四角形を作成/編集します
 * @author Kamesuta
 */
public interface ISquareBuilder {

	/**
	 * SquareBuilderの種類を変更します
	 */
	void load(PendingSquare ps);

	/**
	 * 編集を開始します
	 */
	void load(Square square);

	/**
	 * データを破棄します
	 */
	void clear();

	/**
	 * 要素のサイズ
	 * @return 要素のサイズ
	 */
	int listSize();

	/**
	 * 要素の取得
	 * @param pos 位置
	 * @return 要素
	 */
	Vector3f get(int pos);

	/**
	 * 要素の設定
	 * @param pos 位置
	 * @param vec 要素
	 */
	void set(int pos, Vector3f vec);

	/**
	 * 要素の追加
	 * @param vec 要素
	 */
	void add(int pos, Vector3f vec);

	/**
	 * 要素の削除
	 */
	void remove(int pos);

	/**
	 * 現在位置の要素の取得
	 * @return 要素
	 */
	Vector3f get();

	/**
	 * 現在位置への要素の設定
	 * @param vec 要素
	 */
	void set(Vector3f vec);

	/**
	 * 現在位置への要素の追加
	 * @param vec 要素
	 */
	void add(Vector3f vec);

	/**
	 * 現在位置の要素の削除
	 */
	void remove();

	/**
	 * 現在位置の取得
	 * @return 位置
	 */
	int getPos();

	/**
	 * 現在位置の設定
	 * @param setpos 位置
	 * @return 修正された位置
	 */
	int setPos(int setpos);

	int setPosLast();

	int setPosFirst();

	/**
	 * 次へ
	 * @return
	 */
	int next();

	/**
	 * 前へ
	 * @return
	 */
	int prev();

	/**
	 * 完成図描画
	 */
	void renderAssist();

	/**
	 * 補助線描画
	 * @param target プレーヤーの位置
	 */
	void renderAssistLine(Vector3f target);

	/**
	 * 完成状況
	 * @return 完成状況 (完成=true)
	 */
	boolean isReady();

	/**
	 * build Square
	 * @param square to write
	 * @return Square instance
	 * @throws IllegalStateException Space Found. use hasSpace() before try this;
	 */
	Square build() throws IllegalStateException;

	/**
	 * 作りかけのデータをエクスポートします。
	 * @return 作りかけのデータ
	 */
	PendingSquare export();

	/**
	 * 種類
	 * @return 種類
	 */
	String getName();

}