package jp4js.nf2.tpl;

import jp4js.nf2.DType;
import java.util.Map;
import java.util.Iterator;
import java.util.TreeMap;

public class FixedMapping implements DType, Iterable<String> { 
    private final TreeMap<String, DHeader> mapping;
    private final TreeMap<String, Integer> index;

    public FixedMapping() {
        this.mapping = new TreeMap<>();
        this.index = new TreeMap<>();
    }

    public FixedMapping(TreeMap<String, DHeader> mapping, TreeMap<String, Integer> index) {
        this.mapping = mapping;
        this.index = index;
    }

    public DHeader get(String fieldname) {
        return this.mapping.get(fieldname);
    }

    public void put(String fieldname, DHeader type) {
        this.mapping.put(fieldname, type);
        if (!this.index.containsKey(fieldname)) {
            this.index.put(fieldname, this.index.size());
        }
    }

    public int index(String fieldname) {
        return this.index.get(fieldname);
    }

    public Iterator<String> iterator() {
        return this.mapping.keySet().iterator();
    }

    public TreeMap<String, DHeader> mapping() {
        return this.mapping;
    }

    @Override
    public String toString() {
        String ret = "";
        for (Map.Entry<String, DHeader> entry: this.mapping.entrySet()) {
            if (ret.length() != 0) ret += ", ";
            ret += entry.getKey() + entry.getValue().toString();
        }

        return ret;
    }
}