package com.kamesuta.mc.worldpictures.component;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kamesuta.mc.worldpictures.component.builder.Vector3f;
import com.kamesuta.mc.worldpictures.net.DownloadTask;
import com.kamesuta.mc.worldpictures.net.LocalManager;
import com.kamesuta.mc.worldpictures.net.LocalResource;
import com.kamesuta.mc.worldpictures.net.NetManager;
import com.kamesuta.mc.worldpictures.net.RemoteResource;

public class Experimental {
	//	public static void rotationTest(Logger logger) {
	//		Vector3f vec = new Vector3f(5,4,3);
	//		logger.info(vec);
	//		vec.rotate(90*Math.PI/180, 0*Math.PI/180, 0*Math.PI/180);
	//		logger.info(vec);
	//
	//		logger.info("SPEED TEST");
	//		int trycount = 1 << 16;
	//		logger.info(trycount + " times!");
	//		logger.info("-------------------------");
	//
	//		// Rotation
	//		double dig90 = 90*Math.PI/180;
	//		long startA = System.nanoTime();
	//		logger.info("Rotation Test");
	//		for (int i = 0; i < trycount; i++) {
	//			vec.rotate(dig90, dig90, dig90);
	//		}
	//		long timeA = System.nanoTime() - startA;
	//		logger.info(timeA + " nano seconds");
	//		logger.info((double)timeA / (trycount * 1000 * 1000) + " seconds/calculate");
	//
	//
	//		// Move
	//		float move = (float) dig90;
	//		long startB = System.nanoTime();
	//		logger.info("Move Test");
	//		for (int i = 0; i < trycount; i++) {
	//			vec.add(move, move, move);
	//		}
	//		long timeB = System.nanoTime() - startB;
	//		logger.info(timeB + " nano seconds");
	//		logger.info((double)timeB / (trycount * 1000 * 1000) + " seconds/calculate");
	//	}

	public static void collisionTest(final Logger logger) {
		final Square s = new Square(new Position(-80.0f, 99.0f, 48.0f), new Position(-71.0f, 99.0f, 45.0f), new Position(-68.0f, 100.0f, 58.0f), new Position(-77.0f, 100.0f, 61.0f));
		logger.info(s);
		logger.info(s.collisionWithLine(new Position(-171, 109, 53), new Position(-176, 92, 54)));
	}

	public static void newspeed(final Logger logger) {
		final int trycount = Integer.MAX_VALUE;
		logger.info(trycount + " times!");
		final long startB = System.nanoTime();
		logger.info("New Test");
		for (int i = 0; i < trycount; i++) {
			new Vector3f(i,i,i);
		}
		final long timeB = System.nanoTime() - startB;
		logger.info(timeB + " nano seconds");
		final double d = (double)timeB / trycount / (1000 * 1000);
		logger.info(d + " seconds/calculate");
		logger.info(String.format("%.32f seconds/calculate", d));
	}

	public static void urltest(final Logger logger) {
		final LocalManager m = new LocalManager(new File("D:/data"));
		final LocalResource l = m.getLocalResource(new RemoteResource(""));
		logger.info(l.url);
	}

	public static void main(final String[] args) throws Exception {
		final Logger logger = LogManager.getLogger("Experimental");
		// rotationTest(logger);
		//		collisionTest(logger);
		//newspeed(logger);
		//urltest(logger);
		final NetManager m = new NetManager(5);
		for (int i=0; i<100; i++) {
			m.addTask(new DownloadTask(new RemoteResource("http://auth.kamesuta.com/experimental/sleep.php"), new LocalResource("D:/data/local/")));
		}
	}

}
