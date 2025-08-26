import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.zip.GZIPInputStream;

public class InputFileProcessor {


    public static void fileToGroupedFile(
            String filePath
    ) throws IOException {
        try (
                GZIPInputStream gzip = new GZIPInputStream(new FileInputStream(filePath));
                BufferedReader br = new BufferedReader(new InputStreamReader(gzip));
        ) {
            var startTime = System.currentTimeMillis();

            String line = br.readLine();
            while (line != null) {
                LineProcessor.process(line);
                line = br.readLine();
            }

            PriorityQueue<Set<String>> priorityQueue = new PriorityQueue<>(
                    new StringSetSizeComparator()
            );

            LineProcessor.getKeyMap()
                    .values().stream()
                    .flatMap(it -> it.values().stream())
                    .distinct()
                    .forEach(priorityQueue::add);

            OutputFileWriter.writeToOutputFile(priorityQueue);

            var finishTime = System.currentTimeMillis();
            System.out.println(finishTime - startTime);
        }
    }
}
