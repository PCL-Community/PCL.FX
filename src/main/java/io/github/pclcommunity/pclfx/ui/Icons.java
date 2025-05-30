package io.github.pclcommunity.pclfx.ui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public enum Icons {
    PCL("M26,29 v-25 h5 a7,7 180 0 1 0,14 h-5 M61,6.5 a10,11.5 180 1 0 0,18 M68,2.5 v24.5 h12 M98,2 v27 M107,2 v27"), CLOSE("M2,0 L0,2 8,10 0,18 2,20 10,12 18,20 20,18 12,10 20,2 18,0 10,8 2,0Z"), POWER("M985.403 552.96C985.403 812.702 773.041 1024 512 1024S38.597 812.702 38.597 552.96c0-158.208 79.163-304.955 211.771-392.625 31.074-20.52 72.94-11.973 93.46 19.101s12.012 72.94-19.062 93.46c-94.681 62.622-151.237 167.306-151.237 280.064 0 185.344 151.788 336.108 338.471 336.108S850.471 738.304 850.471 552.96c0-114.649-57.974-220.278-155.096-282.585-31.35-20.125-40.448-61.833-20.362-93.184s61.794-40.448 93.184-20.4c135.995 87.236 217.206 235.362 217.206 396.169zM512 560.128c37.258 0 67.466-30.208 67.466-67.466V67.466C579.466 30.208 549.258 0 512 0s-67.466 30.208-67.466 67.466v425.196c0 37.258 30.208 67.466 67.466 67.466z"), DOWNLOAD("M955 610h-59c-15 0-29 13-29 29v196c0 15-13 29-29 29h-649c-15 0-29-13-29-29v-196c0-15-13-29-29-29h-59c-15 0-29 13-29 29V905c0 43 35 78 78 78h787c43 0 78-35 78-78V640c0-15-13-29-29-29zM492 740c11 11 29 11 41 0l265-265c11-11 11-29 0-41l-41-41c-11-11-29-11-41 0l-110 110c-11 11-33 3-33-13V68C571 53 555 39 541 39h-59c-15 0-29 13-29 29v417c0 17-21 25-33 13l-110-110c-11-11-29-11-41 0L226 433c-11 11-11 29 0 41L492 740z"), SETTING("M940.4 463.7L773.3 174.2c-17.3-30-49.2-48.4-83.8-48.4H340.2c-34.6 0-66.5 18.5-83.8 48.4L89.2 463.7c-17.3 30-17.3 66.9 0 96.8L256.4 850c17.3 30 49.2 48.4 83.8 48.4h349.2c34.6 0 66.5-18.5 83.8-48.4l167.2-289.5c17.3-29.9 17.3-66.8 0-96.8z m-94.6 96.8L725.9 768.1c-17.3 30-49.2 48.4-83.8 48.4H387.5c-34.6 0-66.5-18.5-83.8-48.4L183.9 560.5c-17.3-30-17.3-66.9 0-96.8l119.8-207.5c17.3-30 49.2-48.4 83.8-48.4h254.6c34.6 0 66.5 18.5 83.8 48.4l119.8 207.5c17.3 30 17.3 66.9 0.1 96.8z M522.3 321.2c-2.5-0.1-5-0.2-7.5-0.2-119.9 0-214 110.3-186.3 235 15.8 70.9 71.5 126.6 142.4 142.4 17.5 3.9 34.7 5.4 51.4 4.7 102.1-3.9 183.6-87.9 183.6-191 0.1-103-81.5-187-183.6-190.9z m68.6 269.1c-18.5 18-43 28.9-68.6 30.7l-6 0.3c-30.2 0.4-58.6-11.4-79.7-33-19.5-20.1-30.7-47-30.9-75-0.3-29.6 11.1-57.4 32-78.3 20.6-20.6 48-32 77.2-32 2.5 0 5 0.1 7.5 0.3 26.7 1.8 51.5 13.2 70.5 32.5 19.6 20 30.8 46.9 31.2 74.9 0.2 30.2-11.5 58.6-33.2 79.6z"), MORE("M364 0h-273C40 0 0 40 0 91v273C0 414 40 455 91 455h273C414 455 455 414 455 364V91C455 40 414 0 364 0zM341 341H113V113h227v227zM933 0h-273C609 0 568 40 568 91v273c0 50 40 91 91 91h273C983 455 1024 414 1024 364V91c0-50-40-91-90-91zM910 341h-227V113h227v227zM364 568h-273C40 568 0 609 0 659v273c0 50 40 91 91 91h273C414 1024 455 983 455 932v-273C455 609 414 568 364 568zM341 910H113v-227h227v227zM933 568h-273c-50 0-91 40-91 91v273c0 50 40 91 91 91h273c50 0 90-40 90-91v-273c0-50-40-90-90-90zM910 910h-227v-227h227v227z"), MIN("M0,0 h15 v2 h-15 v-2 Z");

    public final String pathData;

    Icons(String pathData) {
        this.pathData = pathData;
    }
    public static Node createIconWithSize(SVGPath path, double size) {
        double scale = (size <= 0 ? 24 : size) / 24;

        StackPane container = new StackPane(path);
        container.setAlignment(Pos.CENTER);
        container.setPrefSize(20, 20);
        container.setMinSize(20, 20);
        container.setScaleX(scale);
        container.setScaleY(scale);

        return container;
    }

    public Node toNode() {
        return createIcon(0.38);
    }

    public Node toNode(double size) {
        return createIcon(size);
    }

    public Node createIcon(double size) {
        SVGPath path = new SVGPath();
        path.getStyleClass().add("svg");
        path.setContent(pathData);

        return createIconWithSize(path, size);
    }
}