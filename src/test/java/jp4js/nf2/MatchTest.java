package jp4js.nf2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchTest {
    @Test
    public void basic01_() {
        Match match = new Match(
            DSample.flatRel0,
            DSample.flatRel0_bodys[0]
        );
        assertThat(match.isValid()).isTrue();
    }

    @Test
    public void basic02_() {
        Match match0 = new Match(
            DSample.nestedRel0,
            DSample.nestedRel0_bodys[0]
        );
        assertThat(match0.isValid()).isTrue();
    }
}