package com.kamesuta.mc.worldpictures.component.builder.square;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.worldpictures.component.builder.Vector3f;
import com.kamesuta.mc.worldpictures.reference.Names;

/**
 * Minecraft軸に沿った四角形を作成します。
 * @author Kamesuta
 */
public class MinecraftSquareBuilder extends BaseSquareBuilder {

	public static PendingSquareController ctr = new PendingSquareController() {
		@Override
		public void set(ArrayList<Vector3f> data, int pos, Vector3f vec) {
			int size = data.size();
			if (0 < size && 1 <= pos) {
				Vector3f vecA = data.get(0);
				if (2 <= size && 2 <= pos) {
					Vector3f vecB = data.get(1);
					Vector3f vecC = (2 < size) ? data.get(2) : new Vector3f();
					Vector3f vecD = (3 < size) ? data.get(3) : new Vector3f();
					makeMinecraftSquare(vecA, vecB, vec, vecC, vecD);
					super.set(data, 2, vecC);
					super.set(data, 3, vecD);
				} else if (!vecA.equals(vec)) {
					Vector3f vecB = (1 < size) ? data.get(1) : new Vector3f();
					toLine(vecB.set(vecA).negate().add(vec)).add(vecA);
					super.set(data, 1, vecB);
				}
			} else {
				super.set(data, 0, vec);
			}
		}

		@Override
		public void add(ArrayList<Vector3f> data, int pos, Vector3f vec) {
			set(data, pos, vec);
		}
	};

	protected PendingSquareController getCtr() {
		return ctr;
	}

	/**
	 * 一番長いベクトル以外を0にします
	 * @param target 操作対象
	 * @return 操作対象
	 */
	private static Vector3f toLine(Vector3f target) {
		float dx = Math.abs(target.x);
		float dy = Math.abs(target.y);
		float dz = Math.abs(target.z);

		if (dx >= dy && dx >= dz) {
			target.y = 0;
			target.z = 0;
		} else if (dy >= dx && dy >= dz) {
			target.x = 0;
			target.z = 0;
		} else if (dz >= dx && dz >= dy) {
			target.x = 0;
			target.y = 0;
		}

		return target;
	}

	/**
	 * except方向以外の一番長いベクトル以外を0にします
	 * @param target 操作対象
	 * @param except 除外方向
	 * @return 操作対象
	 */
	private static Vector3f toLineExcept(Vector3f target, Vector3f except) {
		float edx = Math.abs(except.x);
		float edy = Math.abs(except.y);
		float edz = Math.abs(except.z);

		if (edx > edy && edx > edz) {
			target.x = 0;
		} else if (edy > edx && edy > edz) {
			target.y = 0;
		} else if (edz > edx && edz > edy) {
			target.z = 0;
		}

		return toLine(target);
	}

	/**
	 * 頂点A、頂点Bと、補助点Pを使い、2つのMinecraft軸と平行になる長方形ABCDとなるような頂点C、頂点Dを求めます
	 * @param vecA 頂点A
	 * @param vecB 頂点B
	 * @param vecP 補助点P
	 * @param vecC 頂点C 書き換え対象
	 * @param vecD 頂点D 書き換え対象
	 */
	private static void makeMinecraftSquare(Vector3f vecA, Vector3f vecB, Vector3f vecP, Vector3f vecC, Vector3f vecD) {
		// ベクトルAB (vecCのインスタンスを代用)
		Vector3f vecAB = vecC.set(vecB).sub(vecA);
		// ベクトルAP (vecDのインスタンスを代用)
		Vector3f vecAP = vecD.set(vecP).sub(vecA);
		// vecDのインスタンスにセット
		toLineExcept(vecAP, vecAB).add(vecA);
		// vecCのインスタンスにセット
		vecC.add(vecAP);
	}

	private Vector3f rendererAssistLinePoolAtoB = new Vector3f();
	private Vector3f rendererAssistLinePoolC = new Vector3f();
	private Vector3f rendererAssistLinePoolD = new Vector3f();
	@Override
	public void renderAssistLine(Vector3f target) {
		int size = listSize();
		if (0 < size) {
			if (size == 1) {
				Vector3f vecA = get(0);
				Vector3f vecAtoB = rendererAssistLinePoolAtoB.set(target).sub(vecA);
				toLine(vecAtoB).add(vecA);
				GL11.glBegin(GL11.GL_LINES);
				GL11.glVertex3f(vecA.x, vecA.y, vecA.z);
				GL11.glVertex3f(vecAtoB.x, vecAtoB.y, vecAtoB.z);
				GL11.glEnd();
			} else if (2 <= size) {
				Vector3f vecA = get(0);
				Vector3f vecB = get(1);
				Vector3f vecC = rendererAssistLinePoolC;
				Vector3f vecD = rendererAssistLinePoolD;
				makeMinecraftSquare(vecA, vecB, target, vecC, vecD);
				GL11.glBegin(GL11.GL_LINE_LOOP);
				GL11.glVertex3f(vecA.x, vecA.y, vecA.z);
				GL11.glVertex3f(vecB.x, vecB.y, vecB.z);
				GL11.glVertex3f(vecC.x, vecC.y, vecC.z);
				GL11.glVertex3f(vecD.x, vecD.y, vecD.z);
				GL11.glVertex3f(vecA.x, vecA.y, vecA.z);
				GL11.glVertex3f(vecC.x, vecC.y, vecC.z);
				GL11.glVertex3f(vecB.x, vecB.y, vecB.z);
				GL11.glVertex3f(vecD.x, vecD.y, vecD.z);
				GL11.glEnd();
			}
		}
	}

	@Override
	public String getName() {
		return Names.SquareBuilder.Minecraft;
	}

}