package com.kamesuta.mc.worldpictures.vertex;

import java.util.List;

import com.google.common.collect.Lists;
import com.kamesuta.mc.worldpictures.vertex.square.Square;

public class OneCut {
	public static final OneCut NULL = new OneCut();

	public List<Scene> vertexes;

	public OneCut(List<Scene> vertexes) {
		this.vertexes = vertexes;
	}

	public OneCut() {
		this(Lists.<Scene>newArrayList());
	}

	public OneCut(Scene[] scene) {
		this(Lists.<Scene>newArrayList(scene));
	}

	/**
	 * 現在の推移をvectorpoolに書き込みます
	 * @param vectorpool 受け取り
	 * @return vectorpool
	 */
	public boolean takeashot(long now, Square vectorpool) {
		if (!vertexes.isEmpty()) {
			Scene first = vertexes.get(0);
			if (first.getSquare() != null) {
				Scene last = vertexes.get(vertexes.size()-1);
				Scene
					prevlast = last,
					nextlast = last;

				long alltime = 0;
				for (Scene vertex : vertexes) {
					if (vertex.getSquare() != null)
						alltime += vertex.getLength();
				}
				alltime = Math.max(alltime, Scene.DefaultLength);
				long nowtime = now % alltime;

				long nowFrameTime = 0;
				for (Scene vertex : vertexes) {
					if (vertex.getSquare() != null) {
						nowFrameTime += vertex.getLength();
						if (nowtime > nowFrameTime) {
							prevlast = vertex;
						} else {
							nextlast = vertex;
							break;
						}
					}
				}

				long betweentime = (long) (prevlast.getLength());
				long betweennowtime = (long) (nowtime - (nowFrameTime - prevlast.getLength()));
				float progress = (float) betweennowtime / betweentime;

				Square
					prevlastsquare = prevlast.getSquare(),
					nextlastsquare = nextlast.getSquare();

				vectorpool.lt.set(nextlastsquare.lt).sub(prevlastsquare.lt).scale(progress).add(prevlastsquare.lt);
				vectorpool.lb.set(nextlastsquare.lb).sub(prevlastsquare.lb).scale(progress).add(prevlastsquare.lb);
				vectorpool.rb.set(nextlastsquare.rb).sub(prevlastsquare.rb).scale(progress).add(prevlastsquare.rb);
				vectorpool.rt.set(nextlastsquare.rt).sub(prevlastsquare.rt).scale(progress).add(prevlastsquare.rt);

				return true;
			}
		}
		return false;
	}

}
