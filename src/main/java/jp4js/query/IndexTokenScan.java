package jp4js.query;

import jp4js.index.IndexContext;
import jp4js.index.node.LabelNode;
import jp4js.utils.Iter;
import jp4js.query.PlanOperator;

public class IndexTokenScan implements PlanOperator {
    private IndexContext indexContext;

    public IndexTokenScan(IndexContext indexContext) {
        this.indexContext = indexContext;
    }

    @Override
    public Iter<LabelNode> iterator() {
        return this.indexContext.openAll();
    }
}
