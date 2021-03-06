package jp4js.utils.query;

import org.junit.Test;

import jp4js.data.Goessener;
import jp4js.data.JsonArrayMulti;

import static org.assertj.core.api.Assertions.assertThat;

public class PathCompilerTest {
    @Test
    public void basic01_() {
        Goessener data = new Goessener();
        // System.out.println(data.query(0).toString());
        System.out.println(data.query(1).toString());
        System.out.println(data.query(2).toString());
        System.out.println(data.query(3).toString());
        System.out.println(data.query(4).toString());
        assertThat(data.query(0).toString()).isEqualTo(
            "[[@]:asy(.store.book.*.author)]"
        );
        assertThat(data.query(1).toString()).isEqualTo(
            "[[@]:asy(..author)]"
        );
        assertThat(data.query(2).toString()).isEqualTo(
            "[[@]:asy(.store.*)]"
        );
        assertThat(data.query(3).toString()).isEqualTo(
            "[[@]:asy(.store..price)]"
        );
        assertThat(data.query(4).toString()).isEqualTo(
            "[[@]:asy(..book.{2,3})]"
        );
    }

    @Test
    public void basic02_() {
        JsonArrayMulti data = new JsonArrayMulti();
        System.out.println(data.query(0).toString());
        assertThat(data.query(0).toString()).isEqualTo("[[@]:asy(.embedded:asy(..count, ..difference))]");
    }
}