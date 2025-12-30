package itk.tasks.collection.countOfelements;

import java.util.*;

public class CountOfElements {

    public <T> Map<T, Integer> count(T[] elements) {
        if (elements == null) return Collections.emptyMap();

        Map<T, Integer> result = new HashMap<>();

        for (T el : elements) {
            result.put(el, result.getOrDefault(el, 0) + 1);
        }
        return result;
    }

}
