package io.github.pclcommunity.pclfx.ui;

import io.github.pclcommunity.pclfx.ui.page.Pages;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class Frame extends BorderPane {
    public Frame() {
        getStylesheets().add("assets/css/root.css");
        getStylesheets().add("assets/css/iconbutton.css");
        getStylesheets().add("assets/css/radio.css");
        getStylesheets().add("assets/css/sidebar.css");

        getStyleClass().add("window");

        setTop(Controllers.getTitleBar());
        BorderPane center = new BorderPane();
        center.setLeft(Controllers.getSideBar());
        center.getStyleClass().add("center");
        center.setCenter(Pages.HOME_PAGE);
        setCenter(center);

        Rectangle clip = new Rectangle();
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        clip.widthProperty().bind(widthProperty());
        clip.heightProperty().bind(heightProperty());
        setClip(clip);
    }
}