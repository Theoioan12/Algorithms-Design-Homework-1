// Buliga Theodor Ioan
// 323 CA

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Sushi {

	static int n, m, x;
	static int[] prices;
	static int[][] grades;

	Sushi(){}

	static int task1() {
		// TODO solve task 1
		// basically knapsack - taken from the lab
		int [][]dp = new int[m + 1][n * x + 1];

		// base case
		for (int pr = 0; pr <= n * x; ++pr) {
			dp[0][pr] = 0;
		}

		// sum of a sushi plater instead of profit
		int []sum = new int[m];
		Arrays.fill(sum, 0);

		// insetad of weight we have the prices
		// computing the sum of the grades
		// given to each platter
		for (int j = 0; j < m; ++j) {
			for (int i = 0; i < n; ++i) {
				sum[j] += grades[i][j];
			}
		}

		// computing the solution
		for (int i = 1; i <= m; ++i) {
			// going through all the prices
			for (int pr = 0; pr <= n * x; ++pr) {
				dp[i][pr] = dp[i - 1][pr];

				// checking if I can add the current platter
				// to the solution
				if (pr - prices[i - 1] >= 0) {
					int sol_aux = dp[i - 1][pr - prices[i - 1]] + sum[i - 1];
					dp[i][pr] = Math.max(dp[i][pr], sol_aux);
				}
			}
		}

		return dp[m][n * x];
	}

	static int task2() {
		// TODO solve task 2
		// same as above
		int [][]dp = new int[m + 1][n * x + 1];
		for (int pr = 0; pr <= n * x; ++pr) {
			dp[0][pr] = 0;
		}
		int []sum = new int[m];
		Arrays.fill(sum, 0);

		for (int j = 0; j < m; ++j) {
			for (int i = 0; i < n; ++i) {
				sum[j] += grades[i][j];
			}
		}

		// computing the solution
		for (int i = 1; i <= m; ++i) {
			for (int pr = 0; pr <= n * x; ++pr) {
				dp[i][pr] = dp[i - 1][pr];

				if (pr - prices[i - 1] >= 0) {
					int sol_aux = dp[i - 1][pr - prices[i - 1]] + sum[i - 1];
					dp[i][pr] = Math.max(dp[i][pr], sol_aux);
				}

				// but this time checking if I can have two platers
				if (pr - 2 * prices[i - 1] >= 0) {
					int sol_aux = dp[i - 1][pr - 2 * prices[i - 1]] + 2 * sum[i - 1];

					dp[i][pr] = Math.max(dp[i][pr], sol_aux);
				}
			}
		}

		return dp[m][n * x];	
	}

	static int task3() {
		// TODO solve task 3
		int [][][]dp = new int[m + 1][n * x + 1][n + 1];
		
		int []sum = new int[m];
		Arrays.fill(sum, 0);

		for (int j = 0; j < m; ++j) {
			for (int i = 0; i < n; ++i) {
				sum[j] += grades[i][j];
			}
		}
		for (int i = 1; i <= m; ++i) {
			for (int pr = 0; pr <= n * x; ++pr) {
				for (int j = 1; j <= n; j++) {
					dp[i][pr][j] = dp[i - 1][pr][j];

					if (pr - prices[i - 1] >= 0) {
						int sol_aux = dp[i - 1][pr - prices[i - 1]][j - 1] + sum[i - 1];
						dp[i][pr][j] = Math.max(dp[i][pr][j], sol_aux);
					}

					// checking if I can have 2 platters
					// and also at most n platters in total
					if (pr - 2 * prices[i - 1] >= 0 && j - 2 >= 0) {
						int sol_aux = dp[i - 1][pr - 2 * prices[i - 1]][j - 2] + 2 * sum[i - 1];
						dp[i][pr][j] = Math.max(dp[i][pr][j], sol_aux);
					}
				}
			}
		}
		return dp[m][n * x][n];
	}

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("sushi.in"));

			final int task = sc.nextInt(); // task number

			n = sc.nextInt(); // number of friends
			m = sc.nextInt(); // number of sushi types
			x = sc.nextInt(); // how much each of you is willing to spend

			prices = new int[m]; // prices of each sushi type
			grades = new int[n][m]; // the grades you and your friends gave to each sushi type

			// price of each sushi
			for (int i = 0; i < m; ++i) {
				prices[i] = sc.nextInt();
			}

			// each friends rankings of sushi types
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < m; ++j) {
					grades[i][j] = sc.nextInt();
				}
			}

			int ans;
			switch (task) {
				case 1:
					ans = Sushi.task1();
					break;
				case 2:
					ans = Sushi.task2();
					break;
				case 3:
					ans = Sushi.task3();
					break;
				default:
					ans = -1;
					System.out.println("wrong task number");
			}

			try {
				FileWriter fw = new FileWriter("sushi.out");
				fw.write(Integer.toString(ans) + '\n');
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
