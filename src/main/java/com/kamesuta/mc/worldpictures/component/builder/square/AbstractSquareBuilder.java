package com.kamesuta.mc.worldpictures.component.builder.square;

/**
 * 編集データの抽象的な操作を定義します
 * @author Kamesuta
 */
public abstract class AbstractSquareBuilder implements ISquareBuilder {

	/**
	 * 補助線の長さ
	 * @return 補助線の長さ
	 */
	public float getAssistLength() {
		// TODO Configuration Handler
		return 64f;
	}

}