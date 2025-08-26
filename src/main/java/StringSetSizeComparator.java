import java.util.Comparator;
import java.util.Set;

public class StringSetSizeComparator implements Comparator<Set<String>> {
    @Override
    public int compare(Set<String> o1, Set<String> o2) {
        if (o1.size() < o2.size()) {
            return 1;
        }
        if (o1.size() > o2.size()) {
            return -1;
        }
        return 0;
    }
}