import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class Dictionary {
    private Map<String, WordEntry> entries;

    public Dictionary() {
        entries = new HashMap<>();
    }

    public void addWord(String word, String meaning) {
        WordEntry entry = new WordEntry(word, meaning);
        entries.put(word.toLowerCase(), entry);
    }

    public String lookupWord(String word) {
        WordEntry entry = entries.get(word.toLowerCase());
        if (entry != null) {
            return entry.getMeaning();
        } else {
            return "Word not found!";
        }
    }

    public void updateWord(String word, String newMeaning) {
        WordEntry entry = entries.get(word.toLowerCase());
        if (entry != null) {
            entry.setMeaning(newMeaning);
        }
    }

    public void displayAllWords() {
        if (entries.isEmpty()) {
            System.out.println("Dictionary is empty.");
        } else {
            System.out.println("Dictionary contents:");
            for (WordEntry entry : entries.values()) {
                System.out.println(entry);
            }
        }
    }

    // New method to get all entries
    public Collection<WordEntry> getEntries() {
        return entries.values();
    }
}
