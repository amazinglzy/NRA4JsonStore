package jp4js.utils.ui;

public interface Container {
    public int width();
    public int height();
    public void draw(CharMatrixDrawer drawer, int sx, int sy);
}