package nodomain.jsongenerator.gui;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import nodomain.jsongenerator.data.BoolDataOptions;
import nodomain.jsongenerator.data.DateDataOptions;
import nodomain.jsongenerator.data.DoubleDataOptions;
import nodomain.jsongenerator.data.NumberDataOptions;
import nodomain.jsongenerator.data.ObjectDataOptions;
import nodomain.jsongenerator.data.PatternDataOptions;
import nodomain.jsongenerator.data.StringDataOptions;

public enum StructureGenerator {
	
	INSTANCE;
	
	private BorderPane pane;
	private GridPane gp;
	private final double SPACING = 15.0;
	
	public Node createJsonString(String name, StringDataOptions options) {
		createObjectLayout(name);
		
		return pane;
	}
	
	public Node createJsonNumber(String name, NumberDataOptions options) {
		createObjectLayout(name);

		return pane;
	}
	
	public Node createJsonPattern(String name, PatternDataOptions options) {
		createObjectLayout(name);

		return pane;
	}
	
	public Node createJsonBool(String name, BoolDataOptions options) {
		createObjectLayout(name);		

		CheckBox onlyTrueCB = new CheckBox("only TRUE");
		onlyTrueCB.setSelected(options.isOnlyTrue());
		CheckBox onlyFalseCB = new CheckBox("only FALSE");
		onlyFalseCB.setSelected(options.isOnlyFalse());	
		
		gp.add(onlyTrueCB, 1, 1);
		gp.add(onlyFalseCB, 1, 2);
		pane.layout();
		
		return pane;
	}
	
	public Node createJsonDouble(String name, DoubleDataOptions options) {
		createObjectLayout(name);

		return pane;
	}
	
	public Node createJsonDate(String name, DateDataOptions options) {
		createObjectLayout(name);

		return pane;
	}
	
	public Node createJsonObject(String name, ObjectDataOptions options) {
		createObjectLayout(name);

		return pane;
	}
	
	private BorderPane createObjectLayout(String name) {
		pane = new BorderPane();
		pane.setPadding(new Insets(15, 0, 15, 0));
		
		pane.setRight(crateMenuButtons());
		
		gp = createGrid(name);
		pane.setCenter(gp);
		
		return pane;
	}
	
	private Node crateMenuButtons() {
		Button up = new Button("up");
		Button down = new Button("down");
		Button remove = new Button("remove");
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
	
	private GridPane createGrid(String name) {
		GridPane pane = new GridPane();
		pane.setHgap(SPACING);
		pane.setVgap(SPACING);
		pane.setAlignment(Pos.CENTER);
		Label nameL = new Label("name");
		TextField nameF = new TextField(name);
		pane.add(nameL, 0, 0);
		pane.add(nameF, 1, 0);
		
		return pane;
	}

}
