import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Ex01_02 {
    public static void main(String[] args) {

        String fileName = args[0];
        Map<Character, Integer> totalMinutesPerBoat = new HashMap<>();
        totalMinutesPerBoat.put('A', 0);
        totalMinutesPerBoat.put('B', 0);
        totalMinutesPerBoat.put('C', 0);
        totalMinutesPerBoat.put('D', 0);
        totalMinutesPerBoat.put('E', 0);

        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                records.add(parts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 時系列に並び替える
        records.sort((r1, r2) -> {
            int hour1 = Integer.parseInt(r1[2]);
            int minute1 = Integer.parseInt(r1[3]);
            int hour2 = Integer.parseInt(r2[2]);
            int minute2 = Integer.parseInt(r2[3]);
            return (hour1 * 60 + minute1) - (hour2 * 60 + minute2);
        });

        Map<Character, Integer> startTime = new HashMap<>();
        for (String[] parts : records) {
            char boat = parts[0].charAt(0);
            char action = parts[1].charAt(0);
            int hour = Integer.parseInt(parts[2]);
            int minute = Integer.parseInt(parts[3]);

            int timeInMinutes = hour * 60 + minute;

            if (action == 'O') {
                startTime.put(boat, timeInMinutes);
            } else if (action == 'I' && startTime.containsKey(boat)) {
                int duration = timeInMinutes - startTime.get(boat);
                totalMinutesPerBoat.put(boat, totalMinutesPerBoat.get(boat) + duration);
                startTime.remove(boat);
            }
        }

        int totalMinutes = 0;

        for (char boat : totalMinutesPerBoat.keySet()) {
            int minutes = totalMinutesPerBoat.get(boat);
            if (minutes > 0) {
                int hours = minutes / 60;
                int remainingMinutes = minutes % 60;
                System.out.printf("%c %d:%02d\n", boat, hours, remainingMinutes);
            }
            totalMinutes += minutes;
        }

        int totalHours = totalMinutes / 60;
        int remainingMinutes = totalMinutes % 60;
        System.out.printf("%d:%02d\n", totalHours, remainingMinutes);
    }
}
