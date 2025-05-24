package io.github.pclcommunity.pclfx.ui;
public enum Icons {
    PCL("M26,29 v-25 h5 a7,7 180 0 1 0,14 h-5 M61,6.5 a10,11.5 180 1 0 0,18 M68,2.5 v24.5 h12 M98,2 v27 M107,2 v27"),
    CLOSE("M2,0 L0,2 8,10 0,18 2,20 10,12 18,20 20,18 12,10 20,2 18,0 10,8 2,0Z"),
    MIN("M0,0 h15 v2 h-15 v-2 Z");

    public final String pathData;

    Icons(String pathData) {
        this.pathData = pathData;
    }
}