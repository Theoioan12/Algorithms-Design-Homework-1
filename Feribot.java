// Buliga Theodor Ioan
// 323 CA

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Feribot {
	public static void main(final String[] args) throws IOException {
		var scanner = new MyScanner(new FileReader("feribot.in"));

		// n cars, k ferries
		var n = scanner.nextInt();
		var k = scanner.nextLong();
		
		// keeping the weight of the cars
		var a = new long[n];
		for (var i = 0; i < n; i += 1) {
			a[i] = scanner.nextLong();
		}

		// printing the result
		try (var printer = new PrintStream("feribot.out")) {
			printer.println(solutionFeribot(a, k));	
		}
	}

	public static long solutionFeribot(long[] a, long k) {
		// setting the length, left and right staring point
		long n = a.length, left = 0, right = 0;
		for (int i = 0; i < a.length; i++) {
			// left as the maximum
			left = Math.max(left, a[i]);

			// right as the sum of elements
			right += a[i];
		}

		// if there is only one ferry
		// I return the greatest value
		if (k == 1) { 
			return right;
		}

		while (left < right) {
			// setting the middle
			long mid = left + (right - left) / 2;
			boolean ok = true;
			long ferries = 1, sum = 0;

			// iterating through all the cars
			for (int i = 0; i < a.length && ok; i++) {
				// calculating the sum
				sum += a[i];

				// if the sum is over the maximum
				if (sum > mid) {
					// increasing the number of ferries used
					ferries++;

					// resetting the sum
					// as the current car's weight
					sum = a[i];

					// if the number of ferries used overtook the
					// maximum available
					if (ferries > k) {
						ok = false;
					}
				}
			
			}
			
			// if all the ferries did not overtake
			// we are searching in the smaller interval
			if (ok) {
				right = mid;
			} else {
				// otherwise searching in the greater interval
				left = mid + 1;
			}
		}

		return right;
	}

	// I took this from the other skels
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

