package com.kamesuta.mc.worldpictures.vertex;

public class SceneEditor {
	private int pos;
	private Scene scene;

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
		setPosLast();
	}

	public int size() {
		return scene.size();
	}

	public int getPos() {
		return pos;
	}

	public int setPos(int pos) {
		return this.pos = Math.max(0, Math.min(size(), pos));
	}

	public int setPosLast() {
		if (getScene() != null)
			return setPos(size());
		else
			return setPos(0);
	}

	public int setPosFirst() {
		return setPos(0);
	}

	public int next() {
		return setPos(getPos()+1);
	}

	public int prev() {
		return setPos(getPos()-1);
	}

	public void add() {

	}

	public SceneEditor() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

}
