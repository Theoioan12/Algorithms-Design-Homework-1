// Buliga Theodor Ioan
// 323 CA

/*
 * Acest schelet citește datele de intrare și scrie răspunsul generat de voi,
 * astfel că e suficient să completați cele două metode.
 *
 * Scheletul este doar un punct de plecare, îl puteți modifica oricum doriți.
 *
 * Dacă păstrați scheletul, nu uitați să redenumiți clasa și fișierul.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Nostory {
	public static void main(final String[] args) throws IOException {
		var scanner = new MyScanner(new FileReader("nostory.in"));

		var task = scanner.nextInt();
		var n = scanner.nextInt();
		var moves = task == 2 ? scanner.nextInt() : 0;

		var a = new int[n];
		for (var i = 0; i < n; i += 1) {
			a[i] = scanner.nextInt();
		}

		var b = new int[n];
		for (var i = 0; i < n; i += 1) {
			b[i] = scanner.nextInt();
		}

		try (var printer = new PrintStream("nostory.out")) {
			if (task == 1) {
				printer.println(solveTask1(a, b));
			} else {
				printer.println(solveTask2(a, b, moves));
			}
		}
	}
	public static void reverse(int[] array) {

		// array length
		int n = array.length;

		// swapping the elements
		// going to n / 2
		for (int i = 0; i < n / 2; i++) {

			// swapping the element
			int temp = array[i];

			// with its corresponednt from the other half
			array[i] = array[n - i - 1];
			array[n - i - 1] = temp;
		}
	}

	private static long solveTask1(int[] a, int[] b) {
		// sorting the first array
		Arrays.sort(a);

		// sorting the second array
		Arrays.sort(b);

		// reversing the second array
		reverse(b);

		// calculating the score
		long score = 0;
		for (int i = 0; i < a.length; i++) {
			score += Math.max(a[i], b[i]);
		}
		return score;
	}
	
	private static long solveTask2(int[] a, int[] b, int moves) {
		// firstly taking the greater values in a array
		for (int i = 0; i < a.length; i++) {
			if (a[i] > b[i]) {
				int aux = a[i];
				a[i] = b[i];
				b[i] = aux;
			}
		}

		// sorting both arrays
		Arrays.sort(a);
		Arrays.sort(b);

		// calculating the last element for the
		// the array a
		int last = a.length - 1;
		
		// switching the elements
		for (int i = 0; moves > 0 && last > 0; i++) {
			// if the great ones from the array of great values
			// are greater than the smaller ones from the array of lower values
			if (a[last] >= b[i]) {
				// I switch their places
				int aux = a[last];
				a[last] = b[i];
				b[i] = aux;

				// and decreasing the number of moves
				moves--;
			}
			// updating the last
			last--;
		}

		// calculating the score
		long score = 0;
		for (int i = 0; i < a.length; i++) {
			score += Math.max(a[i], b[i]);
		}
		return score;
	}

	/**
	 * A class for buffering read operations, inspired from here:
	 * https://pastebin.com/XGUjEyMN.
	 */
	private static class MyScanner {
		private BufferedReader br;
		private StringTokenizer st;

		public MyScanner(Reader reader) {
			br = new BufferedReader(reader);
		}

		public String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}

