import java.util.Arrays;
import java.util.Scanner;

public class Ex_07_02 {

    // 数値を受け取り、その数値に対する次の数値を計算するメソッド
    public static int nextValue(int n) {
        // 4桁の文字列に変換する
        char[] digits = String.format("%04d", n).toCharArray();
        
        // 最大値を計算
        Arrays.sort(digits);
        int minValue = Integer.parseInt(new String(digits));
        
        // 最小値を計算
        reverseArray(digits);
        int maxValue = Integer.parseInt(new String(digits));
        
        // 差を計算して次の値を返す
        return maxValue - minValue;
    }

    // 配列を反転するメソッド
    public static void reverseArray(char[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // コマンドライン引数として4桁以下の正の整数を受け取る
        int n = Integer.parseInt(args[0]);

        // 計算結果を出力する
        while (true) {
            // 次の値を計算
            int next = nextValue(n);
            
            // 結果を表示
            System.out.println(next);
            
            // 同じ値が再度出現したら終了
            if (next == n) {
                break;
            }
            
            // 次のループのために現在の値を更新
            n = next;
        }
        
        scanner.close();
    }
}
