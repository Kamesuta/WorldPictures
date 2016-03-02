package com.kamesuta.mc.worldpictures.vertex;

import org.lwjgl.opengl.GL11;

public class SquareBuilder {
	public static final float AssistLength = 64f;

	private Vector3f[] data;
	private int pos = 0;

	public SquareBuilder(Vector3f[] preinit) {
		data = preinit;
	}

	public SquareBuilder() {
		this(new Vector3f[4]);
	}

	public SquareBuilder(Square square) {
		this(new Vector3f[] {
				square.lt,
				square.lb,
				square.rb,
				square.rt,
		});
	}

	public int inRangePos(int pos) {
		return this.pos = Math.max(0, Math.min(3, pos));
	}

	public Vector3f get(int pos) {
		return data[inRangePos(pos)];
	}

	public Vector3f get() {
		return data[pos];
	}

	public int select(int pos) {
		return this.pos = inRangePos(pos);
	}

	public int getPos() {
		return pos;
	}

	public boolean set(Vector3f vec) {
		boolean setable = canSet();
		this.data[pos] = vec;
		return setable;
	}

	public boolean canSet() {
		return this.data[pos] == null;
	}

	public boolean add(Vector3f vec) {
		this.select(pos + 1);
		boolean setable = canSet();
		if (setable) set(vec);
		return setable;
	}

	/**
	 * return First Space Position
	 * @return First Space Position
	 * @throws IllegalStateException No Space Found. use hasSpace() before try this.
	 */
	public int getSpace() throws IllegalStateException  {
		int space = -1;
		for (int i = 0; i < 4; i++) {
			if (data[i] == null) space = i;
		}
		if (space == -1) throw new IllegalStateException("No Space Found");
		return space;
	}

	public boolean hasSpace() {
		for (int i = 0; i < 4; i++) {
			if (data[i] == null) return true;
		}
		return false;
	}

	/**
	 * return First Data Position
	 * @return First Data Position
	 * @throws IllegalStateException No Data Found. use hasData() before try this.
	 */
	public int getData() throws IllegalStateException  {
		int space = -1;
		for (int i = 0; i < 4; i++) {
			if (data[i] == null) space = i;
		}
		if (space == -1) throw new IllegalStateException("No Data Found");
		return space;
	}

	public boolean hasData() {
		for (int i = 0; i < 4; i++) {
			if (data[i] != null) return true;
		}
		return false;
	}

	public void renderAssist() {
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (int i = 0; i < 4; i++) {
			Vector3f vec = data[i];
			if (vec != null)
				GL11.glVertex3f(vec.x, vec.y, vec.z);
		}
		GL11.glEnd();
	}

	public void renderAssistLine() {
		if (hasSpace() && hasData()) {
			Vector3f vec = get(getData());
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

	public void renderAssistLineNearest(Vector3f target) {
		if (hasSpace() && hasData()) {
			Vector3f vec = get(getData());
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

	/**
	 * build Square
	 * @return Square instance
	 * @throws IllegalStateException Space Found. use hasSpace() before try this;
	 */
	public Square build() throws IllegalStateException {
		if (!hasSpace()) {
			return new Square(data[0], data[1], data[2], data[3]);
		} else {
			throw new IllegalStateException("Space Found");
		}
	}
}
