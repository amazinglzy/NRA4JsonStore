package jp4js.index.node;

import jp4js.utils.Iter;

import org.junit.Test;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class CombinedNodeIteratorTest {
    @Test
    public void testCombinedNodeIteratorSanity() {
        LinkedList<LabelNode> l1 = new LinkedList<LabelNode>() {{
            add(NodeFactory.create(0, 0, 10, 1, null, null));
            add(NodeFactory.create(0, 1, 5, 2, null, null));
        }};
        LinkedList<LabelNode> l2 = new LinkedList<LabelNode>() {{
            add(NodeFactory.create(1, 6, 7, 2, null, null));
            add(NodeFactory.create(1, 7, 8, 2, null, null));
        }};
        Iter<LabelNode> iter = new CombinedNodeIterator(new SingleNodeIterator(l1), new SingleNodeIterator(l2));
        assertThat(iter.read()).isEqualToIgnoringNullFields(NodeFactory.create(0, 0, 10, 1, null, null));
        iter.next();
        assertThat(iter.read()).isEqualToIgnoringNullFields(NodeFactory.create(0, 1, 5, 2, null, null));
        iter.next();
        assertThat(iter.read()).isEqualToIgnoringNullFields(NodeFactory.create(1, 6, 7, 2, null, null));
        assertThat(iter.hasNext()).isEqualTo(true);
        iter.next();
        assertThat(iter.read()).isEqualToIgnoringNullFields(NodeFactory.create(1, 7, 8, 2, null, null));
        iter.next();
        assertThat(iter.hasNext()).isEqualTo(false);
    }

    @Test
    public void testCombinedNodeIteratorClone() {
        LinkedList<LabelNode> l1 = new LinkedList<LabelNode>() {{
            add(NodeFactory.create(0, 0, 10, 1, null, null));
            add(NodeFactory.create(0, 1, 5, 2, null, null));
        }};
        LinkedList<LabelNode> l2 = new LinkedList<LabelNode>() {{
            add(NodeFactory.create(1, 6, 7, 2, null, null));
            add(NodeFactory.create(1, 7, 8, 2, null, null));
        }};
        Iter<LabelNode> iter = new CombinedNodeIterator(new SingleNodeIterator(l1), new SingleNodeIterator(l2));

        assertThat(iter.hasNext()).isTrue();
        assertThat(iter.read()).isEqualToIgnoringNullFields(NodeFactory.create(0, 0, 10, 1, null, null));

        Iter<LabelNode> iterCopy = iter.cloneCurrentIterator();
        iter.next();
        assertThat(iter.hasNext()).isTrue();
        assertThat(iter.read()).isEqualToIgnoringNullFields(NodeFactory.create(0, 1, 5, 2, null, null));

        assertThat(iterCopy.read()).isEqualToIgnoringNullFields(NodeFactory.create(0, 0, 10, 1, null, null));

        assertThat(iter.hasNext()); iter.next();
        assertThat(iter.hasNext()); iter.next();
        assertThat(iterCopy.read()).isEqualToIgnoringNullFields(NodeFactory.create(0, 0, 10, 1, null, null));

        assertThat(iterCopy.hasNext()); iterCopy.next();
        assertThat(iterCopy.read()).isEqualToIgnoringNullFields(NodeFactory.create(0, 1, 5, 2, null, null));
    }
}
