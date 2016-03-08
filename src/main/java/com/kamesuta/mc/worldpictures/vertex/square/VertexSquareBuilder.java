package com.kamesuta.mc.worldpictures.vertex.square;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.worldpictures.reference.Names;
import com.kamesuta.mc.worldpictures.vertex.Vector3f;

/**
 * 4つの頂点から四角形を作成します
 * @author Kamesuta
 */
public class VertexSquareBuilder extends BaseSquareBuilder {

	@Override
	public void renderAssist() {
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (Vector3f vec : data) {
			GL11.glVertex3f(vec.x, vec.y, vec.z);
		}
		GL11.glEnd();
	}

/*	@Override
	public void renderAssistLine(Vector3f target) {
		if (hasSpace() && hasData()) {
			for (ListIterator<Vector3f> it = data.listIterator(); it.hasNext();) {
				int i = it.nextIndex();
				Vector3f vec = it.next();
				if (isListEnds(i)) {
					float sx = target.x - vec.x;
					float sy = target.y - vec.y;
					float sz = target.z - vec.z;
					float dx = Math.abs(sx);
					float dy = Math.abs(sy);
					float dz = Math.abs(sz);
					GL11.glBegin(GL11.GL_LINES);
					float assistLength = getAssistLength();
					if (dx > dy && dx > dz) {
						GL11.glVertex3f(vec.x-(sx<0?assistLength:0), vec.y, vec.z);
						GL11.glVertex3f(vec.x+(sx>0?assistLength:0), vec.y, vec.z);
					} else if (dy > dx && dy > dz) {
						GL11.glVertex3f(vec.x, vec.y-(sy<0?assistLength:0), vec.z);
						GL11.glVertex3f(vec.x, vec.y+(sy>0?assistLength:0), vec.z);
					} else if (dz > dx && dz > dy) {
						GL11.glVertex3f(vec.x, vec.y, vec.z-(sz<0?assistLength:0));
						GL11.glVertex3f(vec.x, vec.y, vec.z+(sz>0?assistLength:0));
					}
					GL11.glEnd();
				}
			}
		}
	}
*/
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

	@Override
	public String getName() {
		return Names.SquareBuilder.Vertex;
	}

}
