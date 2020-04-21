package jp4js.index.node;

import jp4js.utils.Utils;
import java.util.List;
import java.util.LinkedList;

public class ArrayNode extends Node {
    private long index;
    public ArrayNode(long index, Object value, Object rootDocument) {
        super(-1, -1, -1, value, rootDocument);
        this.index = index;
    }

    public ArrayNode(long index, long firstVisit, long lastVisit, int level, Object value, Object rootDocument) {
        super(firstVisit, lastVisit, level, value, rootDocument);
        this.index = index;
    }

    public long getIndex() {
        return this.index;
    }


    public interface ArraySelections {
        List<Integer> select();
    }

    public static class ArrayIndex implements ArraySelections {
        private int index;

        public ArrayIndex(int index) {
            this.index = index;
        }

        @Override
        public List<Integer> select() {
            int indexCopy = this.index;
            return new LinkedList<>() {{ add(indexCopy); }};
        }
    }

    public static class ArraySlice implements ArraySelections {
        private int from;
        private int to;

        public ArraySlice(int from, int to) {
            Utils.isTrue(from < to, "from of Slice must be greater than to");
            this.from  = from;
            this.to = to;
        }

        @Override
        public List<Integer> select() {
            return new LinkedList<>() {{
                for (int i = from; i < to; i++) {
                    add(i);
                }
            }};
        }
    }

    public static class ArrayOperation implements ArraySelections {
        private List<ArraySelections> selections;

        public ArrayOperation(List<ArraySelections> selections) {
            this.selections = selections;
        }

        @Override
        public List<Integer> select() {
            return new LinkedList<>() {{
                for (ArraySelections selection: selections) {
                    addAll(selection.select());
                }
            }};
        }
    }
}
