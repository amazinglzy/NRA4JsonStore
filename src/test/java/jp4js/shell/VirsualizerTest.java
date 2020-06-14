package jp4js.shell;

import jp4js.nf2.rel.NestedRelation;
import jp4js.nf2.rel.NestedRelationSample;
import jp4js.nf2.rel.BasicType;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VirsualizerTest {
    @Test
    public void testHeaderOnlyFlatRel0() {
        NestedRelation.Instance instance = NestedRelationSample.flatRel0.builder().build();
        Virsualizer virsualizer = new Virsualizer(instance);
        
        assertThat(virsualizer.toString()).isEqualTo(
            " -------------- \n"+
            "| ----- ------ |\n"+
            "|| age | name ||\n"+
            "| ----- ------ |\n"+
            " -------------- "
        );
    }

    @Test
    public void testheaderOnlyNestedRel0() {
        NestedRelation.Instance instance = NestedRelationSample.nestedRel0.builder().build();
        Virsualizer virsualizer = new Virsualizer(instance);
        assertThat(virsualizer.toString()).isEqualTo(
            " ----------------------------------- \n"+
            "| ----- -------------------- ------ |\n"+
            "|| age | ------------------ | name ||\n"+
            "||     || courses          ||      ||\n"+
            "||     | ------------------ |      ||\n"+
            "||     || -------- ------- ||      ||\n"+
            "||     ||| course | score |||      ||\n"+
            "||     || -------- ------- ||      ||\n"+
            "||     | ------------------ |      ||\n"+
            "| ----- -------------------- ------ |\n"+
            " ----------------------------------- "
        );
    }

    @Test
    public void testHeaderAndTuplesFlat0() {
        NestedRelation.Instance instance = NestedRelationSample.flatRel0.builder()
            .begin()
                .put("name", BasicType.createDString("Alice"))
                .put("age", BasicType.createDInt(10))
            .end()
            .build();
        Virsualizer virsualizer = new Virsualizer(instance);
        assertThat(virsualizer.toString()).isEqualTo(
            " ----------------- \n" +
            "| ----- --------- |\n" +
            "|| age | name    ||\n" +
            "| ----- --------- |\n" +
            " ----------------- \n" +
            "| ----- --------- |\n" +
            "|| 10  | \"Alice\" ||\n" +
            "| ----- --------- |\n" +
            " ----------------- "
        );
    }

    @Test
    public void testHeaderAndTupleFlat0ExistsNull() {
        NestedRelation.Instance instance = NestedRelationSample.flatRel0.builder()
            .begin()
                .put("name", BasicType.createDString("Alice"))
            .end()
            .build();
        Virsualizer virsualizer = new Virsualizer(instance);
        System.out.println(virsualizer.toString());
        assertThat(virsualizer.toString()).isEqualTo(
            " ----------------- \n" +
            "| ----- --------- |\n" +
            "|| age | name    ||\n" +
            "| ----- --------- |\n" +
            " ----------------- \n" +
            "| ----- --------- |\n" +
            "||     | \"Alice\" ||\n" +
            "| ----- --------- |\n" +
            " ----------------- "
        );
    }

    @Test
    public void testHeaderAndTupleNested0() {
        NestedRelation.Instance instance = NestedRelationSample.nestedRel0.builder()
            .begin()
                .put("name", BasicType.createDString("Alice"))
                .put("age", BasicType.createDInt(10))
                .enter("courses")
                    .begin()
                        .put("course", BasicType.createDString("English"))
                        .put("score", BasicType.createDInt(90))
                    .end()
                    .begin()
                        .put("course", BasicType.createDString("Chinese"))
                        .put("score", BasicType.createDInt(95))
                    .end()
                .exit()
            .end()
            .build();
        Virsualizer virsualizer = new Virsualizer(instance);
        assertThat(virsualizer.toString()).isEqualTo(
            " ----------------------------------------- \n" +
            "| ----- ----------------------- --------- |\n" +
            "|| age | --------------------- | name    ||\n" +
            "||     || courses             ||         ||\n" +
            "||     | --------------------- |         ||\n" +
            "||     || ----------- ------- ||         ||\n" +
            "||     ||| course    | score |||         ||\n" +
            "||     || ----------- ------- ||         ||\n" +
            "||     | --------------------- |         ||\n" +
            "| ----- ----------------------- --------- |\n" +
            " ----------------------------------------- \n" +
            "| ----- ----------------------- --------- |\n" +
            "|| 10  | --------------------- | \"Alice\" ||\n" +
            "||     || ----------- ------- ||         ||\n" +
            "||     ||| \"English\" | 90    |||         ||\n" +
            "||     || ----------- ------- ||         ||\n" +
            "||     | --------------------- |         ||\n" +
            "||     || ----------- ------- ||         ||\n" +
            "||     ||| \"Chinese\" | 95    |||         ||\n" +
            "||     || ----------- ------- ||         ||\n" +
            "||     | --------------------- |         ||\n" +
            "| ----- ----------------------- --------- |\n" +
            " ----------------------------------------- "
        );
    }

    @Test
    public void testHeaderAndTupleNested0ExistsNull() {
        NestedRelation.Instance instance = NestedRelationSample.nestedRel0.builder()
            .begin()
                .put("name", BasicType.createDString("Alice"))
                .put("age", BasicType.createDInt(10))
                .enter("courses")
                    .begin()
                        .put("course", BasicType.createDString("English"))
                        .put("score", BasicType.createDInt(90))
                    .end()
                    .begin()
                        .put("course", BasicType.createDString("Chinese"))
                        .put("score", BasicType.createDInt(95))
                    .end()
                .exit()
            .end()
            .begin()
                .put("name", BasicType.createDString("Bob"))
                .put("age", BasicType.createDInt(20))
            .end()
            .build();
        Virsualizer virsualizer = new Virsualizer(instance);
        assertThat(virsualizer.toString()).isEqualTo(
            " ----------------------------------------- \n"+
            "| ----- ----------------------- --------- |\n"+
            "|| age | --------------------- | name    ||\n"+
            "||     || courses             ||         ||\n"+
            "||     | --------------------- |         ||\n"+
            "||     || ----------- ------- ||         ||\n"+
            "||     ||| course    | score |||         ||\n"+
            "||     || ----------- ------- ||         ||\n"+
            "||     | --------------------- |         ||\n"+
            "| ----- ----------------------- --------- |\n"+
            " ----------------------------------------- \n"+
            "| ----- ----------------------- --------- |\n"+
            "|| 10  | --------------------- | \"Alice\" ||\n"+
            "||     || ----------- ------- ||         ||\n"+
            "||     ||| \"English\" | 90    |||         ||\n"+
            "||     || ----------- ------- ||         ||\n"+
            "||     | --------------------- |         ||\n"+
            "||     || ----------- ------- ||         ||\n"+
            "||     ||| \"Chinese\" | 95    |||         ||\n"+
            "||     || ----------- ------- ||         ||\n"+
            "||     | --------------------- |         ||\n"+
            "| ----- ----------------------- --------- |\n"+
            " ----------------------------------------- \n"+
            "| ----- --------------------- ---------   |\n"+
            "|| 20  |                     | \"Bob\"   |  |\n"+
            "| ----- --------------------- ---------   |\n"+
            " ----------------------------------------- "
        );
    }

    @Test
    public void testHeaderAndTupleNested1ExistsNull() {
        NestedRelation.Instance instance = NestedRelationSample.nestedRel1.builder()
            .begin()
                .enter("customer")
                    .begin()
                        .put("name.first.[str]", BasicType.createDString("John"))
                        .put("name.last.[str]", BasicType.createDString("Smith"))
                        .enter("address")
                            .begin()
                                .put("[str]", BasicType.createDString("11 Maple"))
                            .end()
                        .exit()
                    .end()
                    .begin()
                        .put("name.first.[str]", BasicType.createDString("Mary"))
                        .put("name.last.[str]", BasicType.createDString("Jones"))
                        .enter("address")
                            .begin()
                                .put("[str]", BasicType.createDString("456 Oak"))
                            .end()
                            .begin()
                                .put("[str]", BasicType.createDString("789 Pine"))
                            .end()
                        .exit()
                    .end()
                    .begin()
                        .put("name.first.[str]", BasicType.createDString("David"))
                        .put("name.last.[str]", BasicType.createDString("Johnson"))
                    .end()
                .exit()
            .end()
            .build();
        Virsualizer virsualizer = new Virsualizer(instance);
        System.out.println(virsualizer.toString());
    }
}