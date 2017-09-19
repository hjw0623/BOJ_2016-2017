package basic.stringmatching;

public class KmpAlgorithm {
	int[] fail;

	public KmpAlgorithm(int size) {
		fail = new int[size];
	}

	public void fail(String pattern) {
		int i, j;
		fail[0] = -1;
		for (i = 1; i < pattern.length(); i++) {
			j = fail[i - 1] + 1;
			while (pattern.charAt(i) == pattern.charAt(j) && j > 0) {
				j = fail[j - 1] + 1;
			}
			if (pattern.charAt(i) == pattern.charAt(j)) {
				fail[i] = j;
			} else {
				fail[i] = -1;
			}
		}
	}

	public int search(String strings, String pattern, int startIndex) {
		int i, j;
		for (i = startIndex, j = 0; i < strings.length(); i++, j++) {
			System.out.println(i + " " + j);
			if (j == pattern.length() - 1 && strings.charAt(i) == pattern.charAt(j)) {
				return i - j;
			}
			while ((strings.charAt(i) != pattern.charAt(j)) && (j > 0)) {
				j = fail[j - 1] + 1;
			}
			if (strings.charAt(i) != pattern.charAt(j)) {
				j = -1;
				System.out.println(i + " " + j);
			}
		}
		return -1;
	}

	public void printFail(String strings, String pattern) {
		for (int i = 0; i < strings.length(); i++) {
			System.out.print("\t" + i);
		}
		System.out.println();
		for (int i = 0; i < strings.length(); i++) {
			System.out.print("\t" + strings.charAt(i));
		}
		System.out.println();
		for (int i = 0; i < pattern.length(); i++) {
			System.out.print("\t" + pattern.charAt(i));
		}
		System.out.println();
		for (int i = 0; i < fail.length; i++) {
			System.out.print("\t" + fail[i]);
		}
		System.out.println();
	}
}
