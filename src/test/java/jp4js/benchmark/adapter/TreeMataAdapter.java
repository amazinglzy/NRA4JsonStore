package jp4js.benchmark.adapter;

import java.util.List;

import com.jayway.jsonpath.Configuration;

import jp4js.algebra.Domain;
import jp4js.algebra.operator.structure.StructureList;
import jp4js.algebra.tpl.NestedData;
import jp4js.storage.dewey.DeweyIndex;
import jp4js.utils.algebra.Trans;

public class TreeMataAdapter implements TplAdapter {
    private DeweyIndex index;

    public TreeMataAdapter() {

    }

    @Override
    public void index(Object json, Configuration configuration) {
        Domain.Instance instance = Trans.fromJSON(json, configuration);
        this.index = DeweyIndex.build(instance);
    }

    @Override
    public int query(StructureList lst) {
        List<NestedData> res = this.index.query(lst);
        return res.size();
    }
}
