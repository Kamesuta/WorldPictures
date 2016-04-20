package com.kamesuta.mc.worldpictures.component.builder;

import java.util.Iterator;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.worldpictures.component.Position;
import com.kamesuta.mc.worldpictures.component.Square;

/**
 * 編集データの具体的な操作、保持、管理をします。
 * @author Kamesuta
 */
public abstract class BaseSquareBuilder extends AbstractSquareBuilder {

	protected PendingSquare data;

	/**
	 * リストから初期化します。
	 * @param list
	 */
	protected BaseSquareBuilder() {
		data = new PendingSquare();
	}

	/**
	 * 完成品の修正を開始します
	 * @param square 完成品
	 */
	@Override
	public void load(Square square) {
		set(0, square.lt.toVec());
		set(1, square.lb.toVec());
		set(2, square.rb.toVec());
		set(3, square.rt.toVec());
	}

	/**
	 * 編集方法を切り替えます
	 * @param builder 別の編集方法
	 */
	@Override
	public void load(PendingSquare ps) {
		data = ps;
	}

	protected abstract PendingSquareController getCtr();

	@Override
	public void clear() {
		data.clear(getCtr());
	}

	@Override
	public int listSize() {
		return data.size();
	}

	@Override
	public Vector3f get(int pos) {
		return data.get(getCtr(), pos);
	}

	@Override
	public void set(int pos, Vector3f vec) {
		data.set(getCtr(), pos, vec);
	}

	@Override
	public void add(int pos, Vector3f vec) {
		data.add(getCtr(), pos, vec);
	}

	@Override
	public void remove(int pos) {
		data.remove(getCtr(), pos);
	}

	/**
	 * 完成することが可能か
	 */
	@Override
	public boolean isReady() {
		return !data.isReady(getCtr());
	}

	@Override
	public Vector3f get() {
		return data.get(getCtr());
	}

	@Override
	public void set(Vector3f vec) {
		data.set(getCtr(), vec);
	}

	@Override
	public void add(Vector3f vec) {
		data.add(getCtr(), vec);
	}

	@Override
	public void remove() {
		data.remove(getCtr());
	}

	@Override
	public int getPos() {
		return data.getPos();
	}

	@Override
	public int setPos(int setpos) {
		return data.setPos(setpos);
	}

	@Override
	public int setPosFirst() {
		return data.setPosFirst();
	}

	@Override
	public int setPosLast() {
		return data.setPosLast();
	}

	@Override
	public int next() {
		return data.next();
	}

	@Override
	public int prev() {
		return data.prev();
	}

	@Override
	public Square build() throws IllegalStateException {
		if (isReady()) {
			return new Square(new Position(get(0)), new Position(get(1)), new Position(get(2)), new Position(get(3)));
		}
		return null;
	}

	@Override
	public PendingSquare export() {
		return data;
	}

	@Override
	public void renderAssist() {
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (Iterator<Vector3f> itr = data.iterator(getCtr()); itr.hasNext();) {
			Vector3f vec = itr.next();
			GL11.glVertex3f(vec.x, vec.y, vec.z);
		}
		GL11.glEnd();
	}

	@Override
	public void renderAssistLine(Vector3f target) {
		float corner = 0.5f;
		int size = listSize();
		if (0 < size) {
			if (size == 1) {
				Vector3f vec = get(0);
				GL11.glBegin(GL11.GL_LINES);
				GL11.glVertex3f(vec.x-corner, vec.y, vec.z);
				GL11.glVertex3f(vec.x+corner, vec.y, vec.z);
				GL11.glVertex3f(vec.x, vec.y-corner, vec.z);
				GL11.glVertex3f(vec.x, vec.y+corner, vec.z);
				GL11.glVertex3f(vec.x, vec.y, vec.z-corner);
				GL11.glVertex3f(vec.x, vec.y, vec.z+corner);
				GL11.glEnd();
			}
		}
	}

}