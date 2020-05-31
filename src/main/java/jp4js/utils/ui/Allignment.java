package jp4js.utils.ui;

public abstract class Allignment implements Container {
    private SharedWidth width;
    public Allignment() {
        this.width = null;
    }
    public Allignment(SharedWidth width) {
        this.width = width;
    }

    public abstract int actualWidth();
    public abstract int actualHeight();

    @Override
    public int width() {
        if (this.width != null) return this.width.data();
        return this.actualWidth();
    }

    @Override
    public int height() {
        return this.actualHeight();
    }

    public void update() {
        if (this.width != null)
            if (this.actualWidth() > this.width.data()) {
                this.width.update(this.actualWidth());
            }
    }
}