import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Некорректное число аргументов");
            return;
        }
        var filePath = args[0];

        InputFileProcessor.fileToGroupedFile(filePath);
    }
}
