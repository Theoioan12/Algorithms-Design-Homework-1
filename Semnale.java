// Buliga Theodor Ioan
// 323 CA

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Semnale {

	static int sig_type, x, y;
	static final int  mod = 1000000007;

	Semnale(){}

	static int type1() {
		// 3d matrix to store
		// the number of bits (x + y + 1)
		// the number of zeros (x + 1)
		// and what the signal is ending with (2 -> 0 and 1)
		int [][][]dp = new int[x + y + 1][x + 1][2];

		// signals ending in 0
		dp[1][1][0] = 1;

		// signals ending in 1
		dp[1][0][1] = 1;

		// computing the number of signals
		// iterating though the number of bits
		for (int i = 2; i <= x + y; i++) {
			for (int j = 1; j <= x; j++) {
				// adding the bits from the anterior step
				// computing the number of signals of the current iteration
				// ending in 0 by adding the number of signals ending in 0
				// and the ones ending in one from the anterior step
				dp[i][j][0] = (dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1]) % mod;

				// because I cannot have two bits of 1 next to each other 
				// I set it to the number of signals ending in 0 from
				// the anterior step
				dp[i][j][1] = (dp[i - 1][j][0]);
			}
		}

		// returning the number of signals
		return (dp[x + y][x][1] + dp[x + y][x][0]) % mod;
	}

	static int type2() {
		//TODO Compute the number of type 2 signals.
		if (y > 2 * (x + 1)) {
			return 0;
		}

		int [][][]dp = new int[x + y + 1][x + 1][2];
		// same as above, but this time, I can have
		// two bits of 1 next to each other
		dp[1][1][0] = 1;
		dp[1][0][1] = 1;
		dp[2][0][1] = 1;

		// computing the number of signals exactly as above
		for (int i = 2; i <= x + y; i++) {
			for (int j = 1; j <= x; j++) {
				dp[i][j][0] = (dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1]) % mod;

				// the number of signals ending in 1 here
				// is the number of signals ending in 0 from the anterior
				// step added to the ones from two steps behind
				dp[i][j][1] = (dp[i - 1][j][0] + dp[i - 2][j][0]) % mod;
			}
		}

		// returning the number of signals
		return (dp[x + y][x][1] + dp[x + y][x][0]) % mod;
	}

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("semnale.in"));

			sig_type = sc.nextInt();
			x = sc.nextInt();
			y = sc.nextInt();

			int ans;
			switch (sig_type) {
				case 1:
					ans = Semnale.type1();
					break;
				case 2:
					ans = Semnale.type2();
					break;
				default:
					ans = -1;
					System.out.println("wrong task number");
			}

			try {
				FileWriter fw = new FileWriter("semnale.out");
				fw.write(Integer.toString(ans));
				fw.close();

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
