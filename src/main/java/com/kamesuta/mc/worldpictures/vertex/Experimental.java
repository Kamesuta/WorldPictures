package com.kamesuta.mc.worldpictures.vertex;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Experimental {
	public static void rotationTest(Logger logger) {
		Vector3f vec = new Vector3f(5,4,3);
		logger.info(vec);
		vec.rotate(90*Math.PI/180, 0*Math.PI/180, 0*Math.PI/180);
		logger.info(vec);
		
		logger.info("SPEED TEST");
		int trycount = 1 << 16;
		logger.info(trycount + " times!");
		logger.info("-------------------------");
		
		// Rotation
		double dig90 = 90*Math.PI/180;
		long startA = System.nanoTime();
		logger.info("Rotation Test");
		for (int i = 0; i < trycount; i++) {
			vec.rotate(dig90, dig90, dig90);
		}
		long timeA = System.nanoTime() - startA;
		logger.info(timeA + " nano seconds");
		logger.info((double)timeA / (trycount * 1000 * 1000) + " seconds/calculate");

	
		// Move
		float move = (float) dig90;
		long startB = System.nanoTime();
		logger.info("Move Test");
		for (int i = 0; i < trycount; i++) {
			vec.add(move, move, move);
		}
		long timeB = System.nanoTime() - startB;
		logger.info(timeB + " nano seconds");
		logger.info((double)timeB / (trycount * 1000 * 1000) + " seconds/calculate");
	}
	

	public static void main(String[] args) {
		Logger logger = LogManager.getLogger("Experimental");
		rotationTest(logger);
	}

}
