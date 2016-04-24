package com.kamesuta.mc.worldpictures.component.util;

import java.util.List;

import com.kamesuta.mc.worldpictures.component.Keyframe;
import com.kamesuta.mc.worldpictures.component.Position;
import com.kamesuta.mc.worldpictures.component.Square;

public class ComponentUtil {
	/**
	 * 現在の四角形を返します
	 * @return vectorpool
	 */
	public static Square getSquareFromKeyframes(final List<Keyframe> kfs, final long time) {
		if (kfs != null) {
			if (!kfs.isEmpty()) {
				final Keyframe last = kfs.get(kfs.size() - 1);
				Keyframe prevlast = last, nextlast = last;

				long alltime = 0;
				for (final Keyframe vertex : kfs) {
					if (vertex != null)
						alltime += vertex.length;
				}

				if (alltime > 0) {
					final long nowtime = time % alltime;

					long nowFrameTime = 0;
					for (final Keyframe vertex : kfs) {
						nowFrameTime += vertex.length;
						if (nowtime > nowFrameTime) {
							prevlast = vertex;
						} else {
							nextlast = vertex;
							break;
						}
					}

					final long betweentime = (prevlast.length);
					final long betweennowtime = nowtime - (nowFrameTime - prevlast.length);
					final float progress = (float) betweennowtime / betweentime;

					final Square pl = prevlast.square;
					final Square nl = nextlast.square;

					final Position lt = new Position((pl.lt.toVec().scale(1-progress)).add(nl.lt.toVec().scale(progress)));
					final Position lb = new Position((pl.lb.toVec().scale(1-progress)).add(nl.lb.toVec().scale(progress)));
					final Position rb = new Position((pl.rb.toVec().scale(1-progress)).add(nl.rb.toVec().scale(progress)));
					final Position rt = new Position((pl.rt.toVec().scale(1-progress)).add(nl.rt.toVec().scale(progress)));

					return new Square(lt, lb, rb, rt);
				}
			}
		}
		return null;
	}
}
