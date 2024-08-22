import java.io.*;
import java.util.*;

public class Ex_08_02 {
    public static void main(String[] args) {
        String fileName = args[0];
        List<String[]> students = new ArrayList<>();

        // ファイルを読み込み
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.trim().split("\\s+");
                students.add(data);
            }
        } catch (IOException e) {
            System.out.println("ファイルを読み込めません: " + e.getMessage());
            return;
        }

        // 合計点の高い順、学生IDの昇順にソート
        Collections.sort(students, (s1, s2) -> {
            int score1 = Integer.parseInt(s1[2]) + Integer.parseInt(s1[3]) + Integer.parseInt(s1[4]);
            int score2 = Integer.parseInt(s2[2]) + Integer.parseInt(s2[3]) + Integer.parseInt(s2[4]);
            return Integer.compare(score2, score1) != 0 ? Integer.compare(score2, score1) : s1[0].compareTo(s2[0]);
        });

        // 同一3位までの学生を出力
        int rank = 1;
        int count = 0;
        int previousScore = -1;
        for (String[] student : students) {
            int totalScore = Integer.parseInt(student[2]) + Integer.parseInt(student[3]) + Integer.parseInt(student[4]);
            
            if (totalScore != previousScore) {
                rank = count + 1; // 新しい順位を設定
            }
            
            if (rank > 3) {
                break; // 3位以内の学生のみを表示
            }
            
            System.out.printf("%s,%s,%d\n", student[0], student[1], totalScore);
            previousScore = totalScore;
            count++;
        }
    }
}
