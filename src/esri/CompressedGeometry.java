package esri;

import java.util.ArrayList;
import java.util.List;


public class CompressedGeometry {
	
	public static List<List<Double>> getLineSting(String compressedGeometry) {
		char[] tokenChars = compressedGeometry.toCharArray();

		List<String> tokens = new ArrayList<>();
		int fromIndex = 0;
		while (fromIndex < tokenChars.length) {
			fromIndex = extractTokens(tokenChars, fromIndex, tokens);
		}

		int nLastDiffX = 0;
		int nLastDiffY = 0;
		int length = tokens.size();
		double dMultBy = fromStringRadix32(tokens.get(0).toCharArray());
		List<List<Double>> lineString = new ArrayList<>();
		for (int i = 1; i < length; i += 2) {
			int nDiffX = fromStringRadix32(tokens.get(i).toCharArray());
			int nDiffY = fromStringRadix32(tokens.get(i + 1).toCharArray());
			int nX = nDiffX + nLastDiffX;
			int nY = nDiffY + nLastDiffY;
			double dX = nX / dMultBy;
			double dY = nY / dMultBy;
			//System.out.println("X : " + dX + " Y:" + dY);
			List<Double> eachPoint = new ArrayList<>();
			eachPoint.add(dX);
			eachPoint.add(dY);
			lineString.add(eachPoint);
			nLastDiffX = nX;
			nLastDiffY = nY;
		}
		return lineString;
	}

	private static int extractTokens(char[] tokenChars, int fromIndex, List<String> tokens) {
		StringBuilder eachToken = new StringBuilder();
		boolean stop = false;
		int nCurrentPos = fromIndex;
		while (!stop) {
			if (tokenChars[nCurrentPos] == '+' || tokenChars[nCurrentPos] == '-') {
				if (fromIndex != nCurrentPos) {
					break;
				}
			}
			eachToken.append(tokenChars[nCurrentPos]);
			nCurrentPos++;
			if (nCurrentPos == tokenChars.length) {
				stop = true;
			}
		}
		tokens.add(eachToken.toString());
		//System.out.println(eachToken.toString());
		fromIndex = nCurrentPos;
		return fromIndex;
	}

	private static int fromStringRadix32(char[] nextToken) {
		//System.out.println(Arrays.toString(nextToken));
		int result = 0;
		for (int i = 0; i < nextToken.length; i++) {
			char cur = nextToken[i];
			if (cur >= '0' && cur <= '9') {
				result = (result << 5) + cur - '0';
			} else if (cur >= 'a' && cur <= 'v') {
				result = (result << 5) + cur - 'a' + 10;
			}
		}
		if (nextToken[0] == '-') {
			result = -result;
		} else if (nextToken[0] != '+') {
			// throw new System.ArgumentOutOfRangeException(); // exception
		}
		return result;
	}
	
}
