package jp4js.utils.iter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;

public class CompareIter {
    public static <E, T> void assertEqual(Iter<E> iter, LinkedList<T> data, BiFunction<E, T, Boolean> func) {
        Iterator<T> dataIterator = data.iterator();
        while (dataIterator.hasNext()) {
            assertThat(iter.valid()).as("iter must be valid").isTrue();
            E e = iter.read();
            T t = dataIterator.next();
            assertThat(func.apply(e, t)).as(e.toString() + " = " + t.toString()).isTrue();
            iter.next();
        }

        assertThat(iter.valid()).as("iter must be unvalid after compare").isFalse();
    }
}
