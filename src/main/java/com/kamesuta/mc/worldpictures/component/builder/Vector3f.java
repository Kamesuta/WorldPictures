package com.kamesuta.mc.worldpictures.component.builder;

import java.io.Serializable;

import com.kamesuta.mc.worldpictures.component.Position;

/**
 * ベクトルクラス
 * @author Kamesuta
 */
public class Vector3f implements Serializable {
	public static final float FLOAT_EPSILON = 10e-6f;

	/**
	 * X成分
	 */
	public float x;
	/**
	 * Y成分
	 */
	public float y;
	/**
	 * Z成分
	 */
	public float z;

	/**
	 * 力のないベクトルを作成します。
	 */
	public Vector3f() {
		this(0, 0, 0);
	}

	/**
	 * 位置ベクトルの作成
	 */
	public Vector3f(final Position pos) {
		this(pos.x, pos.y, pos.z);
	}

	/**
	 * ベクトルインスタンスのコピー
	 */
	public Vector3f(final Vector3f vec) {
		this(vec.x, vec.y, vec.z);
	}

	/**
	 * 各成分に同じ力を持ったベクトル
	 */
	public Vector3f(final float num) {
		this(num, num, num);
	}

	/**
	 * 成分から作成
	 */
	public Vector3f(final float x, final float y, final float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * X成分取得
	 */
	public final float getX() {
		return this.x;
	}

	/**
	 * Y成分取得
	 */
	public final float getY() {
		return this.y;
	}

	/**
	 * Z成分取得
	 */
	public final float getZ() {
		return this.z;
	}

	/**
	 * X成分設定
	 */
	public final void setX(final float x) {
		this.x = x;
	}

	/**
	 * Y成分設定
	 */
	public final void setY(final float y) {
		this.y = y;
	}

	/**
	 * Z成分設定
	 */
	public final void setZ(final float z) {
		this.z = z;
	}

	public Vector3f set(final Vector3f vec) {
		return set(vec.x, vec.y, vec.z);
	}

	public Vector3f set(final float x, final float y, final float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	/**
	 * 原点からの距離の2乗
	 * @return 原点からの距離の2乗
	 */
	public float lengthSquared() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}

	/**
	 * 2点の距離
	 * @return 2点の距離
	 */
	public final double lengthTo(final Vector3f vec) {
		return Math.sqrt(lengthSquaredTo(vec));
	}

	/**
	 * 2点の距離の2乗
	 * @return 2点の距離の2乗
	 */
	public float lengthSquaredTo(final Vector3f vec) {
		return pow2(this.x - vec.x) + pow2(this.y - vec.y) + pow2(this.z - vec.z);
	}

	/**
	 * 2乗
	 * @param num 元
	 * @return 2乗
	 */
	protected final float pow2(final float num) {
		return num * num;
	}

	/**
	 * 原点を中心に反転
	 */
	public Vector3f negate() {
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
		return this;
	}

	public Vector3f scale(final double scale) {
		this.x *= scale;
		this.y *= scale;
		this.z *= scale;
		return this;
	}

	//	public Vector3f rotate(Matrix rotationMatrix) {
	//		Matrix vec0 = new Matrix(new double[][] {
	//			{this.x,			},
	//			{this.y,			},
	//			{this.z,			},
	//		}, 3, 1);
	//
	//		Matrix vec1 = rotationMatrix.solve(vec0);
	//
	//		this.x = (float) vec1.get(0, 0);
	//		this.y = (float) vec1.get(1, 0);
	//		this.z = (float) vec1.get(2, 0);
	//
	//		return this;
	//	}
	//
	//	public Vector3f rotate(double rotX, double rotY, double rotZ) {
	//		Matrix m = getRotationMatrix(rotX, rotY, rotZ);
	//
	//		return rotate(m);
	//	}
	//
	//	public Matrix getRotationMatrix(double rotX, double rotY, double rotZ) {
	//		Matrix mX = new Matrix(new double[][] {
	//			{1,					0,					0,					},
	//			{0,					Math.cos(rotX),		-Math.sin(rotX),	},
	//			{0,					Math.sin(rotX), 	Math.cos(rotX),		},
	//		}, 3, 3);
	//
	//		Matrix mY = new Matrix(new double[][] {
	//			{Math.cos(rotY),	0,					Math.sin(rotY),		},
	//			{0,					1,					0,					},
	//			{-Math.sin(rotY),	0,					Math.cos(rotY),		},
	//		}, 3, 3);
	//
	//		Matrix mZ = new Matrix(new double[][] {
	//			{Math.cos(rotZ),	-Math.sin(rotZ),	0,					},
	//			{Math.sin(rotZ), 	Math.cos(rotZ),		0,					},
	//			{0,					0,					1,					},
	//		}, 3, 3);
	//
	//		return (mX).times(mY).times(mZ);
	//	}

	/**
	 * 内積
	 */
	public float dot(final Vector3f vec) {
		return this.x * vec.x + this.y * vec.y + this.z * vec.z;
	}

	/**
	 * 外積
	 */
	public Vector3f cross(final Vector3f vec) {
		// a = ( d, e, f ) , b = ( g, h, i ) の外積は,
		// a × b = ( ei-fh, fg-di, dh-eg ) です。

		final float d = this.x;
		final float e = this.y;
		final float f = this.z;
		final float g = vec.x;
		final float h = vec.y;
		final float i = vec.z;

		this.x = e * i - f * h;
		this.y = f * g - d * i;
		this.z = d * h - e * g;

		return this;
	}

	public Vector3f add(final Vector3f vec) {
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
		return this;
	}

	public Vector3f add(final float x, final float y, final float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vector3f sub(final Vector3f vec) {
		this.x -= vec.x;
		this.y -= vec.y;
		this.z -= vec.z;
		return this;
	}

	public Vector3f sub(final float x, final float y, final float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	@Override
	public Vector3f clone() {
		return new Vector3f(this);
	}

	@Override
	public boolean equals(final Object obj) {
		return obj instanceof Vector3f && equals((Vector3f) obj);
	}

	public boolean equals(final Vector3f vec) {
		return equals(vec, FLOAT_EPSILON);
	}

	public boolean equals(final Vector3f vec, final float epsilon) {
		return Math.abs(this.x - vec.x) < epsilon && Math.abs(this.y - vec.y) < epsilon
				&& Math.abs(this.z - vec.z) < epsilon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(this.x);
		result = prime * result + Float.floatToIntBits(this.y);
		result = prime * result + Float.floatToIntBits(this.z);
		return result;
	}

	@Override
	public String toString() {
		return String.format("[%s, %s, %s]", this.x, this.y, this.z);
	}
}
