/*
Problem Statement:
- Generate first numRows of Pascal's triangle.

Intuition:
- Each row starts/ends with 1; middle values derive from previous row neighbors.

Approach:
- Build one row in place by inserting leading 1 and updating interior from left to right on stored values.

Time Complexity:
- O(numRows^2).

Space Complexity:
- O(numRows) auxiliary current-row storage, plus output.

Edge Cases:
- numRows=0 returns empty result.

Dry Run:
- Row evolves as [1] -> [1,1] -> [1,2,1].
*/
import java.util.*;

public class Pascals_Triangle {
    public List<List<Integer>> generate(int numRows){
	List<List<Integer>> allrows = new ArrayList<List<Integer>>();
	ArrayList<Integer> row = new ArrayList<Integer>();
	
	for(int i=0;i<numRows;i++)
	{
		row.add(0, 1);
		
		// Update interior cells using previous row values retained in the same list.
		for(int j=1;j<row.size()-1;j++)
			row.set(j, row.get(j)+row.get(j+1));
		allrows.add(new ArrayList<Integer>(row));
	}
	return allrows;
    }
}
