package nodomain.jsongenerator.gui.generators;

import java.util.List;

import org.json.JSONArray;
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
import nodomain.jsongenerator.gui.controller.AddController;
import nodomain.jsongenerator.gui.controller.StructureController;

public enum ObjectElementGenerator implements ElementGenerator {
	
	INSTANCE;

	@Override
	public Node generateElement(String name, JSONObject options, boolean showButtons) {
		BorderPane pane = createObjectLayout(showButtons);
		
		Button add = new Button("Add element", new ImageView(imagePlus));
		add.setOnAction((ActionEvent e) -> {
			handleAddElement(e);
		});	
		
		Label countL = new Label("count");
		TextField countF = ComponentGenerator.INSTANCE
					.generateTextField(options.get("count").toString());	
		Label elementsL = new Label("elements");
		
    	Accordion acc = new Accordion();
    	JSONArray arr = options.getJSONArray("structure");
    	if (!arr.isEmpty()) {
        	List<TitledPane> panes = PanelGenerator.INSTANCE.createStructurePanes(arr);
        	acc.getPanes().addAll(panes);   		
    	}
    	
    	GridPane gp = createGrid(name);	
    	gp.add(countL, 0, 1);
    	gp.add(countF, 1, 1);
    	gp.add(elementsL, 0, 2);
    	gp.add(add, 1, 2);
    	gp.add(acc, 0, 3, 2, 2);
		
    	pane.setCenter(gp);
		return pane;
	}
	
	private void handleAddElement(ActionEvent e) {
		GridPane parent = (GridPane) ((Button)e.getSource()).getParent();
		Accordion acc = (Accordion) parent.getChildren().get(parent.getChildren().size() - 1);
		JSONObject element = showFormAddElement();
		if (element != null) {
			TitledPane tmp = PanelGenerator.INSTANCE.createSinglePane(element);
			acc.getPanes().add(tmp);				
		}	
		StructureController.updateStructure();
	}

	private JSONObject showFormAddElement() {
		AddController addController = new AddController();
		addController.showStage();
		String element = addController.getElement();
		if (element.length() == 0) {
			return null;
		} else {
			return new JSONObject(addController.getElement());
		}
	}

}
