package itk.tasks.collection.Filter;

import java.lang.reflect.Array;

public class ArrayFilter {

    /**
     * Метод фильтрации массива.
     * @param array исходный массив любого типа T
     * @param filter класс, реализующий интерфейс Filter<T>
     * @return новый массив T[] с примененной функцией apply
     */
    @SuppressWarnings("unchecked")
    public <T> T[] filter(T[] array, Filter<T> filter) {
        // Проверка на null
        if (array == null || filter == null) {
            return null;
        }

        T[] result = (T[]) Array.newInstance(
                array.getClass().getComponentType(),
                array.length
        );

        for (int i = 0; i < array.length; i++) {
            result[i] = filter.apply(array[i]);
        }

        return result;
    }
}
