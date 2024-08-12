import java.util.Random;

public class Ex_07_01 {

    // 配列を昇順または降順にソートするメソッド
    public static void sort(int[] array, int mode) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (mode == 1) { // 昇順の場合
                    if (array[j] > array[j + 1]) {
                        // 交換
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                } else if (mode == -1) { // 降順の場合
                    if (array[j] < array[j + 1]) {
                        // 交換
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        int seed = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int mode = Integer.parseInt(args[2]);

        // ランダム配列の生成
        int[] array = new int[n];
        Random rand = new Random(seed);
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt(1000); // 0～999の乱数を生成
        }

        // ソート実行
        sort(array, mode);

        // ソート結果を標準出力に表示
        for (int i = 0; i < n; i++) {
            System.out.println(array[i]);
        }
    }
}
