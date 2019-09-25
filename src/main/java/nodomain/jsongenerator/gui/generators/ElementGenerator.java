package nodomain.jsongenerator.gui.generators;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import nodomain.jsongenerator.gui.UIMessages;
import nodomain.jsongenerator.gui.controller.StructureController;

public interface ElementGenerator {

	Node generateElement(String name, JSONObject rawOptions, boolean showButtons);
	
	final double SPACING = 15.0;
	final Image imageArrowUp = new Image(Object.class.getResourceAsStream("/icons/arrow-up.png"));
	final Image imageArrowDown = new Image(Object.class.getResourceAsStream("/icons/arrow-down.png"));
	final Image imageDelete = new Image(Object.class.getResourceAsStream("/icons/trash.png"));
	final Image imagePlus = new Image(Object.class.getResourceAsStream("/icons/plus.png"));	
	
	default BorderPane createObjectLayout(boolean showButtons) {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(15, 0, 15, 0));
		
		if (showButtons) {
			pane.setRight(crateMenuButtons());
		}
		
		return pane;
	}
	
	default Node crateMenuButtons() {
		Button up = new Button();
		Button down = new Button();
		Button remove = new Button();
		
		up.setGraphic(new ImageView(imageArrowUp));
		down.setGraphic(new ImageView(imageArrowDown));
		remove.setGraphic(new ImageView(imageDelete));
		
		up.setOnAction((ActionEvent e) -> {
			TitledPane pane = getParentPane(e);
			StructureController.movePanelUp(pane);
		});		
		down.setOnAction((ActionEvent e) -> {
			TitledPane pane = getParentPane(e);
			StructureController.movePanelDown(pane);
		});	
		remove.setOnAction((ActionEvent e) -> {
			TitledPane pane = getParentPane(e);
			StructureController.removePanel(pane);
		});
		
		TilePane buttons = new TilePane();
		buttons.setOrientation(Orientation.VERTICAL);
		buttons.setAlignment(Pos.CENTER);
		buttons.setVgap(SPACING);
		buttons.getChildren().addAll(up, down, remove);
		
		HBox buttonGroup = new HBox();
		buttonGroup.setSpacing(SPACING);
		Separator separator = new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		buttonGroup.getChildren().addAll(separator, buttons);
		
		return buttonGroup;
	}
	
	default TitledPane getParentPane(ActionEvent e) {
		Button btn = (Button) e.getSource();
		return (TitledPane) btn.getParent().getParent().getParent().getParent().getParent();
	}

	default GridPane createGrid(String name) {
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(0, 25, 0, 25));
		pane.setHgap(SPACING);
		pane.setVgap(SPACING);
		pane.setAlignment(Pos.CENTER);
		Label nameL = ComponentGenerator.INSTANCE.generateLabel(UIMessages.LABEL_NAME);
		TextField nameF = ComponentGenerator.INSTANCE.generateTextField(name);
		pane.add(nameL, 0, 0);
		pane.add(nameF, 1, 0);
		GridPane.setHgrow(nameL, Priority.ALWAYS);
		GridPane.setHgrow(nameF, Priority.ALWAYS);
		
		return pane;
	}
}
