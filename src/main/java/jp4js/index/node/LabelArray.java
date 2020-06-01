package jp4js.index.node;

import jp4js.utils.Utils;
import java.util.List;
import java.util.LinkedList;

public class LabelArray extends LabelNode {
    private long index;
    public LabelArray(String path, long index, Object value, Object rootDocument) {
        super(path, -1, -1, -1, value, rootDocument);
        this.index = index;
    }

    public LabelArray(String path, long index, long firstVisit, long lastVisit, int level, Object value, Object rootDocument) {
        super(path, firstVisit, lastVisit, level, value, rootDocument);
        this.index = index;
    }

    public long getIndex() {
        return this.index;
    }


    public interface ArraySelections {
        List<Integer> select();
        List<ArraySelections> asList();
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

        @Override
        public List<ArraySelections> asList() {
            ArraySelections toAdd = this;
            return new LinkedList<>(){{
                add(toAdd);
            }};
        }

        @Override
        public String toString() {
            return String.valueOf(this.index);
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

        @Override
        public List<ArraySelections> asList() {
            ArraySelections toAdd = this;
            return new LinkedList<>(){{
                add(toAdd);
            }};
        }

        @Override
        public String toString() {
            return String.valueOf(this.from) + ", " + String.valueOf(this.to);
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

        @Override
        public List<ArraySelections> asList() {
            return new LinkedList<>(){{
                addAll(selections);
            }};
        }

        @Override
        public String toString() {
            String ret = "";
            for (ArraySelections selection: selections) {
                if (ret.length() != 0) ret += ", ";
                ret += selection.toString();
            }
            return ret;
        }
    }
}
