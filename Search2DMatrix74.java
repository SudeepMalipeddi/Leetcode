public class search2dMatrix74 {
    public boolean searchMatrix(int[][] matrix,int target){
        int row = matrix.length;
        int col = matrix[0].length;
        
        // Almost Binary search
        for(int i = 0; i<row; i++){
            int l = matrix[i][0];
            int r = matrix[i][col-1];
            if(l <= target && r>= target){
                for(int j = 0; j<col; j++){
                    if(matrix[i][j] == target) return true;
                }
            }
        }
        return false;
    }
    public boolean searchMatrix1(int[][] matrix,int target){
        // Linear search line by line
        int row = matrix.length;
        int col = matrix[0].length;
        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                if(matrix[i][j] == target) return true;
            }
        }
        return false;   
    }
}
