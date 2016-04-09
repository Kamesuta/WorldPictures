package com.kamesuta.mc.worldpictures.vertex;

import java.util.ArrayList;
import java.util.Collection;

import com.kamesuta.mc.worldpictures.vertex.square.Square;

public class Scene extends ArrayList<AnimatedSquare> {

	public static final Scene NULL = new Scene();

	public Scene() {
		super();
	}

	public Scene(Collection<AnimatedSquare> c) {
		super(c);
	}

	/**
	 * 現在の推移をvectorpoolに書き込みます
	 *
	 * @param vectorpool
	 *            受け取り
	 * @return vectorpool
	 */
	public Square takeashot(long now, Square vectorpool) {
		if (!this.isEmpty()) {
			AnimatedSquare last = this.get(this.size() - 1);
			AnimatedSquare prevlast = last, nextlast = last;

			long alltime = 0;
			for (AnimatedSquare vertex : this) {
				if (vertex != null)
					alltime += vertex.getLength();
			}

			if (alltime > 0) {
				long nowtime = now % alltime;

				long nowFrameTime = 0;
				for (AnimatedSquare vertex : this) {
					nowFrameTime += vertex.getLength();
					if (nowtime > nowFrameTime) {
						prevlast = vertex;
					} else {
						nextlast = vertex;
						break;
					}
				}

				long betweentime = (long) (prevlast.getLength());
				long betweennowtime = (long) (nowtime - (nowFrameTime - prevlast.getLength()));
				float progress = (float) betweennowtime / betweentime;

				vectorpool.lt.set(nextlast.lt).sub(prevlast.lt).scale(progress).add(prevlast.lt);
				vectorpool.lb.set(nextlast.lb).sub(prevlast.lb).scale(progress).add(prevlast.lb);
				vectorpool.rb.set(nextlast.rb).sub(prevlast.rb).scale(progress).add(prevlast.rb);
				vectorpool.rt.set(nextlast.rt).sub(prevlast.rt).scale(progress).add(prevlast.rt);
			}
		}
		return vectorpool;
	}

	/**
	 * 現在の推移を返します
	 *
	 * @return vectorpool
	 */
	public Square takeashot(long now) {
		return takeashot(now, new Square());
	}
}
