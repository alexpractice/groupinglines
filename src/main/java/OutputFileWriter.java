import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.PriorityQueue;
import java.util.Set;

public class OutputFileWriter {

    private static final String OUTPUT_FILE_NAME = "out.txt";
    private static final String GROUP_HEADLINE_TEMPLATE = "группа %d";

    public static void writeToOutputFile(
            PriorityQueue<Set<String>> priorityQueue
    ) throws IOException {
        try (
                var writer = new FileWriter(OUTPUT_FILE_NAME, StandardCharsets.UTF_8);
                BufferedWriter bwr = new BufferedWriter(writer)
        ) {
            bwr.write(String.valueOf(priorityQueue.size()));
            bwr.write("\n");
            int currentOutputGroupNumber = 1;
            while (!priorityQueue.isEmpty()) {
                bwr.write(String.format(GROUP_HEADLINE_TEMPLATE, currentOutputGroupNumber));
                bwr.write("\n");
                var group = priorityQueue.poll();
                group.forEach(it -> {
                    try {
                        bwr.write(it);
                        bwr.write("\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                bwr.write("\n");
                currentOutputGroupNumber++;
            }
        }
    }
}
