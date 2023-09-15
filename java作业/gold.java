public class gold {
    private static int[][] source = {{1, 0, 1}, {0, 0, 0}, {1, 1, 0}, {1, 0, 1}};
    private static int[][] target = {{1, 0, 1}, {1, 1, 1}, {0, 1, 1}, {1, 0, 1}};
    // 临时矩阵，用于保存变换过程中的矩阵状态
    private static int[][] tempMatrix = new int[4][3];
    private static int rows = 4;       // 矩阵行数
    private static int columns = 3;    // 矩阵列数
    private static int count, best = 9999;  // 变换步数和最大步数


    private static void turnrow(int i) {//行反转函数
        for (int j = 0; j < columns; j++)
            tempMatrix[i][j] = 1 - tempMatrix[i][j]; // 翻转

        count++; 
    }
    // 列交换函数
    private static void exchangecolumns(int i, int j) {
        if (i == j)
            return;

        for (int k = 0; k < rows; k++) {
            int tempValue = tempMatrix[k][i];
            tempMatrix[k][i] = tempMatrix[k][j];
            tempMatrix[k][j] = tempValue;
        }
        if (i != j)
            count++; 
    }
    
    // 检查两列是否相同
    private static boolean samecolumns(int i, int j) {
        boolean isSame = true;
        for (int k = 0; k < rows; k++) {
            if (target[k][i] != tempMatrix[k][j]) {
                isSame = false;
                break;
            }
        }
        return isSame;
    }
    public static void main(String[] args) {
        // 一、每一列运用列交换作为第1列
        for (int k = 0; k < columns; k++) {
            // 复制源矩阵到临时矩阵
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < columns; j++)
                    tempMatrix[i][j] = source[i][j];
            count = 0;
            
            exchangecolumns(0, k);  // 进行列交换

            // 二、对每一行进行判断，如果元素与目标元素不相等，则进行行翻转处理
            for (int i = 0; i < rows; i++) {
                if (tempMatrix[i][0] != target[i][0])
                    turnrow(i); // 进行行翻转
            }

            // 三、向后判断后面列是否相同
            boolean found = true;
            for (int i = 0; i < columns; i++) {
                found = false;
                // 1 先判断tempMatrix当前列是否与目标矩阵的当前列相同
                if (samecolumns(i, i)) {
                    found = true;
                    continue;
                }
                // 2 tempMatrix当前列与目标矩阵的当前列不相同时，看tempMatrix当前列之后的列是否与目标矩阵当前列相同
                //  1）若之后有相同的列，则进行列交换操作，继续比较后面的列
                //  2）若之后也没有相同的列，则说明此次变换不能变换成目标矩阵，进行下一种变换尝试
                
                for (int j = i + 1; j < columns; j++) {
                    if (samecolumns(i, j)) {
                        exchangecolumns(i, j); // 进行列交换
                        found = true;
                        break;
                    }
                }
                if (!found)
                    break;
            }
            if (found)
                best = (count < best) ? count : best;
        }
        if (best < 9999)
            System.out.println("至少需要 " + best + " 步完成矩阵变换");
        else
            System.out.println("无解");
    }
    
}
