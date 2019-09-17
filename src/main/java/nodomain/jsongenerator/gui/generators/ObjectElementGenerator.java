package nodomain.jsongenerator.gui.generators;

import java.util.List;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.data.ObjectDataOptions;
import nodomain.jsongenerator.data.parsers.ObjectDataOptionsParser;
import nodomain.jsongenerator.gui.controller.StructureController;

public enum ObjectElementGenerator implements ElementGenerator {
	
	INSTANCE;

	@Override
	public Node generateElement(String name, JSONObject rawOptions) {
		ObjectDataOptions options = ObjectDataOptionsParser.INSTANCE.parseDataOptions(rawOptions);
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

}
