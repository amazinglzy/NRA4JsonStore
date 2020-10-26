package jp4js.algebra.tpl;

import jp4js.utils.Utils;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class DictTemplate implements Template, Iterable<String> {
    private TreeMap<String, Template> mapping;
    private TreeMap<String, Integer> index;

    public DictTemplate() {
        this.mapping = new TreeMap<>();
        this.index = new TreeMap<>();
    }

    public Template get(String fieldname) {
        Utils.notNull(this.mapping, "Mapping must not be null to be called");
        return this.mapping.get(fieldname);
    }
    
    public boolean contains(String fieldname) {
        Utils.notNull(this.mapping, "Mapping must not be null to be called");
        return this.mapping.containsKey(fieldname);
    }

    public void put(String fieldname, Template type) {
        Utils.notNull(this.mapping, "Mapping must not be null to be called");
        this.mapping.put(fieldname, type);
        if (!this.index.containsKey(fieldname)) {
            this.index.put(fieldname, this.index.size());
        }
    }

    public int index(String fieldname) {
        Utils.notNull(this.mapping, "Mapping must not be null to be called");
        return this.index.get(fieldname);
    }

    public Iterator<String> iterator() {
        Utils.notNull(this.mapping, "Mapping must not be null to be called");
        return this.mapping.keySet().iterator();
    }

    public int size() {
        return this.mapping.size();
    }

    @Override
    public String toString() {
        String ret = "";
        for (Map.Entry<String, Template> entry: this.mapping.entrySet()) {
            if (ret.length() != 0) ret += ", ";
            ret += entry.getKey();
            if (entry.getValue() != null)
                ret += entry.getValue().toString();
        }

        return "(" + ret + ")";
    }
}