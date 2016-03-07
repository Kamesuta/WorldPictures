package com.kamesuta.mc.worldpictures.vertex;

/**
 * 3つの頂点から平行四辺形を作成します
 * @author Kamesuta
 */
public class ParallelogramSquareBuilder extends VertexSquareBuilder {

	public ParallelogramSquareBuilder() {
		super();
	}

	public ParallelogramSquareBuilder(Square square) {
		super(square);
	}

	public ParallelogramSquareBuilder(Vector3f... preinit) {
		super(preinit);
	}

	public ParallelogramSquareBuilder(ISquareBuilder builder) {
		super(builder);
	}

	@Override
	public void set(int pos, Vector3f vec) {
		super.set(pos, vec);
		if (listSize() >= 3) super.set(3, calculateLastOne(get(0), get(1), get(2)));
	}

	@Override
	public void add(int pos, Vector3f vec) {
		super.add(pos, vec);
		if (listSize() >= 3) super.set(3, calculateLastOne(get(0), get(1), get(2)));
	}

	@Override
	public void set(Vector3f vec) {
		super.set(vec);
		if (listSize() >= 3) super.set(3, calculateLastOne(get(0), get(1), get(2)));
	}

	@Override
	public void addLast(Vector3f vec) {
		super.addLast(vec);
		if (listSize() >= 3) super.set(3, calculateLastOne(get(0), get(1), get(2)));
	}

	public Vector3f calculateLastOne(Vector3f vA, Vector3f vB, Vector3f vC) {
//		return new Vector3f(vC).add(vA).scale(0.5f).sub(vB).scale(2).add(vB);
		return new Vector3f(vA).sub(vB).add(vC);
	}
}
