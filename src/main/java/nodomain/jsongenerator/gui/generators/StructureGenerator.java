package nodomain.jsongenerator.gui.generators;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import nodomain.jsongenerator.data.BoolDataOptions;
import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.DateDataOptions;
import nodomain.jsongenerator.data.DoubleDataOptions;
import nodomain.jsongenerator.data.NumberDataOptions;
import nodomain.jsongenerator.data.ObjectDataOptions;
import nodomain.jsongenerator.data.PatternDataOptions;
import nodomain.jsongenerator.data.StringDataOptions;
import nodomain.jsongenerator.gui.controller.StructureController;

public enum StructureGenerator {
	
	INSTANCE;
	
	private final double SPACING = 15.0;
	private final Image imageArrowUp = new Image(getClass().getResourceAsStream("/icons/arrow-up.png"));
	private final Image imageArrowDown = new Image(getClass().getResourceAsStream("/icons/arrow-down.png"));
	private final Image imageDelete = new Image(getClass().getResourceAsStream("/icons/trash.png"));
	private final Image imagePlus = new Image(getClass().getResourceAsStream("/icons/plus.png"));
	
	public Node createJsonString(String name, DataOptions doptions) {
		StringDataOptions options = (StringDataOptions) doptions;
		BorderPane pane = createObjectLayout();
		
		Label lengthL = new Label("length");
		TextField lengthF = new TextField(String.valueOf(options.getMinLength()));
		CheckBox firstCapCB = new CheckBox("first capital");
		firstCapCB.setSelected(options.isFirstCapital());
		CheckBox allCapCB = new CheckBox("all capital");
		allCapCB.setSelected(options.isAllCapital());
		
		GridPane gp = createGrid(name);
		gp.add(lengthL, 0, 1);
		gp.add(lengthF, 1, 1);
		gp.add(firstCapCB, 1, 2);
		gp.add(allCapCB, 1, 3);
		
		pane.setCenter(gp);
		return pane;
	}
	
	public Node createJsonNumber(String name, DataOptions doptions) {
		NumberDataOptions options = (NumberDataOptions) doptions;
		BorderPane pane = createObjectLayout();
		
		Label lowerL = new Label("lower bound");
		TextField lowerF = new TextField(String.valueOf(options.getLowerBound()));		
		Label upperL = new Label("upper bound");
		TextField upperF = new TextField(String.valueOf(options.getUpperBound()));
		
		GridPane gp = createGrid(name);
		gp.add(lowerL, 0, 1);
		gp.add(lowerF, 1, 1);
		gp.add(upperL, 0, 2);
		gp.add(upperF, 1, 2);

		pane.setCenter(gp);
		return pane;
	}
	
	public Node createJsonPattern(String name, DataOptions doptions) {
		PatternDataOptions options = (PatternDataOptions) doptions;
		BorderPane pane = createObjectLayout();
		
		Label patternL = new Label("pattern");
		TextField patternF = new TextField(options.getPattern());
		Label connectorL = new Label("connector");
		TextField connectorF = new TextField(options.getConnector());
		CheckBox allCapCB = new CheckBox("all capital");
		allCapCB.setSelected(options.isAllCapital());
		
		GridPane gp = createGrid(name);
		gp.add(patternL, 0, 1);
		gp.add(patternF, 1, 1);
		gp.add(connectorL, 0, 2);
		gp.add(connectorF, 1, 2);
		gp.add(allCapCB, 1, 3);

		pane.setCenter(gp);
		return pane;
	}

	public Node createJsonBool(String name, DataOptions doptions) {
		BoolDataOptions options = (BoolDataOptions) doptions;
		BorderPane pane = createObjectLayout();		

		CheckBox onlyTrueCB = new CheckBox("only TRUE");
		onlyTrueCB.setSelected(options.isOnlyTrue());
		CheckBox onlyFalseCB = new CheckBox("only FALSE");
		onlyFalseCB.setSelected(options.isOnlyFalse());	
		
		GridPane gp = createGrid(name);
		gp.add(onlyTrueCB, 1, 1);
		gp.add(onlyFalseCB, 1, 2);
		
		pane.setCenter(gp);
		return pane;
	}
	
	public Node createJsonDouble(String name, DataOptions doptions) {
		DoubleDataOptions options = (DoubleDataOptions) doptions;
		BorderPane pane = createObjectLayout();

		Label lowerL = new Label("lower bound");
		TextField lowerF = new TextField(String.valueOf(options.getLowerBound()));		
		Label upperL = new Label("upper bound");
		TextField upperF = new TextField(String.valueOf(options.getUpperBound()));
		Label precisionL = new Label("precision");
		TextField precisionF = new TextField(String.valueOf(options.getPrecision()));

		GridPane gp = createGrid(name);
		gp.add(lowerL, 0, 1);
		gp.add(lowerF, 1, 1);
		gp.add(upperL, 0, 2);
		gp.add(upperF, 1, 2);
		gp.add(precisionL, 0, 3);
		gp.add(precisionF, 1, 3);
		
		pane.setCenter(gp);
		return pane;
	}
	
	public Node createJsonDate(String name, DataOptions doptions) {
		DateDataOptions options = (DateDataOptions) doptions;
		BorderPane pane = createObjectLayout();		
		
		Label lowerL = new Label("lower bound");
		TextField lowerF = new TextField(options.getLowerBound().toString());		
		Label upperL = new Label("upper bound");
		TextField upperF = new TextField(options.getUpperBound().toString());
		Label patternL = new Label("output pattern");
		TextField patternF = new TextField(options.getOutputPattern());
		
		GridPane gp = createGrid(name);
		gp.add(lowerL, 0, 1);
		gp.add(lowerF, 1, 1);
		gp.add(upperL, 0, 2);
		gp.add(upperF, 1, 2);
		gp.add(patternL, 0, 3);
		gp.add(patternF, 1, 3);

		pane.setCenter(gp);
		return pane;
	}
	
	public Node createJsonObject(String name, DataOptions doptions) {
		ObjectDataOptions options = (ObjectDataOptions) doptions;
		BorderPane pane = createObjectLayout();
		
		Button add = new Button("Add element", new ImageView(imagePlus));
		//TODO add real form
		add.setOnAction((ActionEvent e) -> {
			GridPane parent = (GridPane) ((Button)e.getSource()).getParent();
			Accordion acc = (Accordion) parent.getChildren().get(parent.getChildren().size() - 1);
			TitledPane tmp = PanelGenerator.INSTANCE.createSinglePane(StructureController.createMockObject());
			acc.getPanes().add(tmp);
		});	
		
		Label countL = new Label("count");
		TextField countF = new TextField(String.valueOf(options.getCount()));	
		Label elementsL = new Label("elements");
		
    	Accordion acc = new Accordion();
    	List<TitledPane> panes = PanelGenerator.INSTANCE.createStructurePanes(options.getStructure());
    	acc.getPanes().addAll(panes);
    	
    	GridPane gp = createGrid(name);	
    	gp.add(countL, 0, 1);
    	gp.add(countF, 1, 1);
    	gp.add(elementsL, 0, 2);
    	gp.add(add, 1, 2);
    	gp.add(acc, 0, 3, 2, 2);
		
    	pane.setCenter(gp);
		return pane;
	}
	
	private BorderPane createObjectLayout() {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(15, 0, 15, 0));
		
		pane.setRight(crateMenuButtons());
		
		return pane;
	}
	
	private Node crateMenuButtons() {
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
	
	private TitledPane getParentPane(ActionEvent e) {
		Button btn = (Button) e.getSource();
		return (TitledPane) btn.getParent().getParent().getParent().getParent().getParent();
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
