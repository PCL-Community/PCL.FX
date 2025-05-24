package io.github.pclcommunity.pclfx.ui.controls;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class IconButton extends Button {
    public IconButton(String svgContent) {
        getStyleClass().add("icon-button");
        SVGPath svgPath = new SVGPath();
        svgPath.setStroke(Color.WHITE);
        svgPath.setFill(Color.WHITE);
        svgPath.setStrokeWidth(0);
        svgPath.setContent(svgContent);
        setGraphic(svgPath);

        setupHoverAnimation();
    }

    private void setupHoverAnimation() {
        final ObjectProperty<Color> backgroundColor = getColorObjectProperty();

        hoverProperty().addListener((obs, wasHovering, isHovering) -> {
            Color target = isHovering ? Color.rgb(255, 255, 255, 0.1) : Color.TRANSPARENT;
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), new KeyValue(backgroundColor, target)));
            timeline.play();
        });
    }

    private ObjectProperty<Color> getColorObjectProperty() {
        final ObjectProperty<Color> backgroundColor = new SimpleObjectProperty<>(Color.TRANSPARENT);
        backgroundColor.addListener((obs, oldColor, newColor) -> {
            String style = String.format("-fx-background-color: rgba(%d, %d, %d, %f);", (int) (newColor.getRed() * 255), (int) (newColor.getGreen() * 255), (int) (newColor.getBlue() * 255), newColor.getOpacity());
            setStyle(style);
        });
        return backgroundColor;
    }
}