package io.github.pclcommunity.pclfx.ui;

import io.github.pclcommunity.pclfx.ui.controls.PCLIconButton;
import io.github.pclcommunity.pclfx.ui.controls.PCLRadioButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class TitleBar extends BorderPane {
    private final Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;

    public TitleBar() {
        this.stage = Controllers.getStage();
        initialize();
        setupDragHandlers();
    }

    private void initialize() {
        getStyleClass().add("title-bar");
        setPadding(new Insets(0));

        setMaxHeight(45);
        setPrefHeight(45);
        setMinHeight(45);


        StackPane logo = createLogo();

        logo.getStyleClass().add("title-bar-logo");

        Label fxLabel = new Label("FX");
        fxLabel.getStyleClass().add("title-bar-fx-label");

        HBox leftContainer = new HBox(0, logo, fxLabel);

        Insets logoMargin = new Insets(0, 0, 0, 32);
        Insets fxMargin = new Insets(12, 0, 0, 25);

        HBox.setMargin(logo, logoMargin);
        HBox.setMargin(fxLabel, fxMargin);

        setLeft(leftContainer);

        HBox nav = createNav();
        nav.setAlignment(Pos.CENTER);
        setCenter(nav);

        HBox rightContainer = new HBox(0);
        rightContainer.getChildren().addAll(createControls());
        rightContainer.setAlignment(Pos.CENTER_RIGHT);
        Scale rightScale = new Scale(0.7, 0.7);
        rightContainer.getTransforms().add(rightScale);

        setRight(rightContainer);
    }

    private HBox createNav() {
        HBox nav = new HBox(10);
        nav.setPadding(new Insets(15));
        nav.setAlignment(Pos.CENTER);

        ToggleGroup group = new ToggleGroup();

        PCLRadioButton launchButton = new PCLRadioButton("启动", Icons.POWER);
        PCLRadioButton downloadButton = new PCLRadioButton("下载", Icons.DOWNLOAD);
        PCLRadioButton settingButton = new PCLRadioButton("设置", Icons.SETTING, 0.48);
        PCLRadioButton moreButton = new PCLRadioButton("更多", Icons.MORE);

        launchButton.setToggleGroup(group);
        downloadButton.setToggleGroup(group);
        settingButton.setToggleGroup(group);
        moreButton.setToggleGroup(group);

        group.selectToggle(launchButton);
        nav.getChildren().addAll(launchButton, downloadButton, settingButton, moreButton);
        return nav;
    }

    private StackPane createLogo() {
        StackPane logo = (StackPane) Icons.PCL.toNode(14);
        SVGPath logoSvg = (SVGPath) logo.getChildren().getFirst();
        logoSvg.setContent(Icons.PCL.pathData);
        logoSvg.setFill(Color.TRANSPARENT);
        logoSvg.setStroke(Color.WHITE);
        logoSvg.setStrokeWidth(3);
        return logo;
    }

    private PCLIconButton[] createControls() {
        PCLIconButton[] buttons = new PCLIconButton[2];
        buttons[0] = new PCLIconButton(Icons.MIN.pathData);
        buttons[1] = new PCLIconButton(Icons.CLOSE.pathData);

        buttons[0].setOnAction(event -> stage.setIconified(true));
        buttons[1].setOnAction(event -> stage.close());

        buttons[0].setPrefWidth(24);
        buttons[1].setPrefWidth(24);

        HBox.setMargin(buttons[0], new Insets(13, 0, 2, 0));
        HBox.setMargin(buttons[1], new Insets(13, 0, 2, 0));

        return buttons;
    }

    private void setupDragHandlers() {
        setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });
    }
}