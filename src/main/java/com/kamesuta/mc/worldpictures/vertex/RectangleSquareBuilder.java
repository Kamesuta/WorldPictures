package com.kamesuta.mc.worldpictures.vertex;

/**
 * 2つの頂点と一つの向きから長方形を作成します。
 * @author Kamesuta
 */
public class RectangleSquareBuilder extends ParallelogramSquareBuilder {

	public RectangleSquareBuilder() {
		super();
	}

	public RectangleSquareBuilder(Square square) {
		super(square);
	}

	public RectangleSquareBuilder(Vector3f... preinit) {
		super(preinit);
	}

	public RectangleSquareBuilder(ISquareBuilder builder) {
		super(builder);
	}

	@Override
	public void set(int pos, Vector3f vec) {
		super.set(pos, vec);
		if (inListRange(2)) super.set(2, calculateThirdOne(get(0), get(1), get(2)));
	}

	@Override
	public void add(int pos, Vector3f vec) {
		super.add(pos, vec);
		if (inListRange(2)) super.set(2, calculateThirdOne(get(0), get(1), get(2)));
	}

	@Override
	public void set(Vector3f vec) {
		super.set(vec);
		if (inListRange(2)) super.set(2, calculateThirdOne(get(0), get(1), get(2)));
	}

	@Override
	public void addLast(Vector3f vec) {
		super.add(vec);
		if (inListRange(2)) super.set(2, calculateThirdOne(get(0), get(1), vec));
	}

	public Vector3f calculateThirdOne(Vector3f a, Vector3f b, Vector3f c) {
//		float length = (float) vA.lengthTo(vB);
//		Vector3f o = new Vector3f(vA).add(vB).scale(0.5f);
//		return new Vector3f(o).add(new Vector3f(vC).sub(o).scale((vA.lengthTo(vB)/2) / (vC.lengthTo(o))));
//		Vector3f vAtoO = new Vector3f(vA).sub(o);
//		Vector3f rotate_vAtoO = new Vector3f(vAtoO.y, -vAtoO.x, -vAtoO.z).add(o);
//		Vector3f AB = new Vector3f(vB).sub(vA);

//		Vector3f z = new Vector3f(a.x, b.y - b.x, a.y);
//		Vector3f x = new Vector3f(a.y, b.z - b.y, a.z);
//		Vector3f y = new Vector3f(a.z, b.x - b.z, a.x);

		return c;
//		return new Vector3f(vB).add(new Vector3f(vA).sub(vC).scale((vA.lengthTo(vB)) / (vC.lengthTo(vA)))).;

	}

	public Vector3f rotX(Vector3f vec, double radX) {
		double x = vec.x;
		double y = Math.sin(radX) * vec.y;
		double z = Math.cos(radX) * vec.z;
		return null;
	}

}
