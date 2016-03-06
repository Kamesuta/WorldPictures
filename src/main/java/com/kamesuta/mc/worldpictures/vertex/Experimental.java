package com.kamesuta.mc.worldpictures.vertex;

import Jama.Matrix;

public class Experimental {

	public static void main(String[] args) {
		double[][] array = { { 1., 2., 3 }, { 4., 5., 6. }, { 7., 8., 10. }, { 3., 4., 5. } };
		Matrix A = new Matrix(array);
		Matrix b = new Matrix(new double[][] { { 4. }, { 5. }, { 6. } });
		// A * b = x
		Matrix x = A.times(b);
		print(x);
	}

	private static void print(Matrix mat) {
		System.out.print("[");
		for (int i = 0; i < mat.getRowDimension(); i++) {
			for (int j = 0; j < mat.getColumnDimension(); j++) {
				System.out.print((int) mat.get(i, j));
				System.out.print(",");
			}
			System.out.println();
		}
		System.out.print("]");
	}
}
