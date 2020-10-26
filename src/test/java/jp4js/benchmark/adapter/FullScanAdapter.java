package jp4js.benchmark.adapter;

import com.jayway.jsonpath.Configuration;

import jp4js.algebra.DType;
import jp4js.algebra.Match;
import jp4js.algebra.op.FullScan;
import jp4js.algebra.op.structure.StructureList;
import jp4js.utils.algebra.Trans;

public class FullScanAdapter implements TplAdapter {
    private DType.Instance instance;

    public FullScanAdapter() {
    }

    @Override
    public void index(Object json, Configuration configuration) {
        this.instance = Trans.fromJSON(json, configuration);
    }

    @Override
    public int query(StructureList lst) {
        FullScan split = new FullScan(this.instance, lst);
        try {
            Match match = split.open();
            // assert(match.isValid());
            return match.body().size();
        } catch (FullScan.MatchException e) {
            e.printStackTrace();
            return -1;
        }
    }
}