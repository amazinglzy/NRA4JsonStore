package jp4js.shell.layout;

public class HorizontalBuilder extends ContainersBuilder {
    public HorizontalBuilder() {}
    public HorizontalBuilder(WidthAllign width) {
        super(width);
    }

    @Override
    public Horizontal build() {
        Horizontal ret = new Horizontal(this.containers(), this.width());
        ret.update();
        return ret;
    }
}