package io.github.pclcommunity.pclfx.ui.controls;

import io.github.pclcommunity.pclfx.ui.Icons;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class RadioButton extends javafx.scene.control.RadioButton {
    private static final Color SELECTED_COLOR = Color.web("#127AE0");
    private static final Duration TRANSITION_DURATION = Duration.millis(150);

    private final DoubleProperty backgroundOpacity = new SimpleDoubleProperty(0);
    private final ObjectProperty<Color> textColor = new SimpleObjectProperty<>(Color.WHITE);
    private final ObjectProperty<Color> iconColor = new SimpleObjectProperty<>(Color.WHITE);
    private final Node iconNode;

    public RadioButton(String text, Icons icon) {
        this.iconNode = icon.toNode();
        initComponents(text);
        setupBindings();
        setupInteractions();
    }

    public RadioButton(String text, Icons icon, double size) {
        this.iconNode = icon.toNode(size);
        initComponents(text);
        setupBindings();
        setupInteractions();
    }

    private void initComponents(String text) {
        getStyleClass().add("my-radio");

        Label label = new Label(text);
        label.getStyleClass().add("radio-text");
        label.textFillProperty().bind(textColor);

        HBox container = new HBox(5, iconNode, label);
        container.setAlignment(Pos.CENTER_LEFT);
        setGraphic(container);
    }

    private void setupBindings() {
        SVGPath svgPath = (SVGPath) ((Pane) iconNode).getChildren().getFirst();
        backgroundOpacity.addListener((obs, oldVal, newVal) -> {
            double alpha = newVal.doubleValue();
            String formattedAlpha = String.format("%.10f", alpha);
            setStyle("-fx-background-color: rgba(255,255,255," + formattedAlpha + ");");
        });
        iconColor.addListener((obs, oldColor, newColor) -> {
            String style = String.format("-fx-fill: %s;", toHex(newColor));
            svgPath.setStyle(style);
        });

        textColor.set(Color.WHITE);
        svgPath.setFill(Color.WHITE);
    }

    private void setupInteractions() {
        selectedProperty().addListener((obs, oldVal, selected) -> {
            updateColors(selected);
            playTransition(selected);
        });

        hoverProperty().addListener((obs, oldVal, hovering) -> {
            if (isSelected()) return;
            backgroundOpacity.set(hovering ? 0.2 : 0.0);
        });
    }

    private void updateColors(boolean selected) {
        Color target = selected ? SELECTED_COLOR : Color.WHITE;
        textColor.set(target);
        iconColor.set(target);
    }

    private void playTransition(boolean selected) {
        Timeline timeline = new Timeline(new KeyFrame(TRANSITION_DURATION, new KeyValue(backgroundOpacity, selected ? 1.0 : 0.0)));
        timeline.play();
    }

    private String toHex(Color color) {
        return String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255), (int) (color.getBlue() * 255));
    }
}