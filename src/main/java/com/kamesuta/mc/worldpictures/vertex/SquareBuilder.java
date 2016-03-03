package com.kamesuta.mc.worldpictures.vertex;

import java.util.ArrayList;
import java.util.ListIterator;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

public class SquareBuilder {
	public static final float AssistLength = 64f;

	private ArrayList<Vector3f> data;
	private int pos = 0;

	public SquareBuilder(Vector3f[] preinit) {
		data = Lists.newArrayList(preinit);
	}

	public SquareBuilder() {
		data = new ArrayList<Vector3f>();
	}

	public SquareBuilder(Square square) {
		data = Lists.newArrayList(
				square.lt,
				square.lb,
				square.rb,
				square.rt
		);
	}

	public int lastSquarePos() {
		return 3;
	}

	public int lastListPos() {
		return Math.max(0, data.size()-1);
	}

	public boolean hasSpace() {
		return lastListPos() < lastSquarePos();
	}

	public boolean hasData() {
		return !data.isEmpty();
	}

	public boolean isEnds(int min, int max, int pos) {
		return min == pos || max == pos;
	}

	public boolean isSquareEnds(int pos) {
		return isEnds(0, lastSquarePos(), pos);
	}

	public boolean isListEnds(int pos) {
		return isEnds(0, lastListPos(), pos);
	}

	public Vector3f get(int pos) {
		if (data.size() > 0)
			return data.get(inListRangePos(pos));
		else
			return null;
	}

	public void set(int pos, Vector3f vec) {
		int inpos = inSquareRangePos(pos);
		if (inpos < data.size())
			data.set(inpos, vec);
		else
			data.add(vec);
	}

	public void add(Vector3f vec) {
		if (hasSpace()) {
			data.add(vec);
			setPosLast();
		}
	}

	public int inRangePos(int min, int max, int pos) {
		return Math.max(min, Math.min(max, pos));
	}

	public int inSquareRangePos(int pos) {
		return inRangePos(0, lastSquarePos(), pos);
	}

	public int inListRangePos(int pos) {
		return inRangePos(0, lastListPos(), pos);
	}

	public boolean inRange(int min, int max, int pos) {
		return min <= pos && pos <= max;
	}

	public boolean inSquareRange(int pos) {
		return inRange(0, lastSquarePos(), pos);
	}

	public boolean inListRange(int pos) {
		return inRange(0, lastListPos(), pos);
	}

	public Vector3f get() {
		return get(pos);
	}

	public void set(Vector3f vec) {
		set(pos, vec);
	}

	public int getPos() {
		return pos;
	}

	public int setPos(int setpos) {
		return pos = inListRangePos(setpos);
	}

	public int setPosFirst() {
		return pos = 0;
	}

	public int setPosLast() {
		return pos = lastListPos();
	}

	public void renderAssist() {
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (Vector3f vec : data) {
			GL11.glVertex3f(vec.x, vec.y, vec.z);
		}
		GL11.glEnd();
	}

	public void renderAssistLine() {
		if (hasSpace() && hasData()) {
			for (ListIterator<Vector3f> it = data.listIterator(); it.hasNext();) {
				int i = it.nextIndex();
				Vector3f vec = it.next();
				if (isListEnds(i)) {
					GL11.glBegin(GL11.GL_LINES);
					GL11.glVertex3f(vec.x-AssistLength, vec.y, vec.z);
					GL11.glVertex3f(vec.x+AssistLength, vec.y, vec.z);
					GL11.glVertex3f(vec.x, vec.y-AssistLength, vec.z);
					GL11.glVertex3f(vec.x, vec.y+AssistLength, vec.z);
					GL11.glVertex3f(vec.x, vec.y, vec.z-AssistLength);
					GL11.glVertex3f(vec.x, vec.y, vec.z+AssistLength);
					GL11.glEnd();
				}
			}
		}
	}

	public void renderAssistLineNearest(Vector3f target) {
		if (hasSpace() && hasData()) {
			for (ListIterator<Vector3f> it = data.listIterator(); it.hasNext();) {
				Vector3f vec = it.next();
				if (isListEnds(it.nextIndex())) {
					float dx = Math.abs(target.x - vec.x);
					float dy = Math.abs(target.y - vec.y);
					float dz = Math.abs(target.z - vec.z);
					GL11.glBegin(GL11.GL_LINES);
					if (dx > dy && dx > dz) {
						GL11.glVertex3f(vec.x-AssistLength, vec.y, vec.z);
						GL11.glVertex3f(vec.x+AssistLength, vec.y, vec.z);
					} else if (dy > dx && dy > dz) {
						GL11.glVertex3f(vec.x, vec.y-AssistLength, vec.z);
						GL11.glVertex3f(vec.x, vec.y+AssistLength, vec.z);
					} else if (dz > dx && dz > dy) {
						GL11.glVertex3f(vec.x, vec.y, vec.z-AssistLength);
						GL11.glVertex3f(vec.x, vec.y, vec.z+AssistLength);
					}
					GL11.glEnd();
				}
			}
		}
	}

	/**
	 * build Square
	 * @return Square instance
	 * @throws IllegalStateException Space Found. use hasSpace() before try this;
	 */
	public Square build() throws IllegalStateException {
		if (!hasSpace()) {
			return new Square(get(0), get(1), get(2), get(3));
		} else {
			throw new IllegalStateException("Not Ready");
		}
	}
}
