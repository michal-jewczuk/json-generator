package nodomain.jsongenerator.gui.generators;

import org.json.JSONObject;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.gui.UIMessages;

public enum DoubleElementGenerator implements ElementGenerator {
	
	INSTANCE;

	@Override
	public Node generateElement(String name, JSONObject options, boolean showButtons) {
		BorderPane pane = createObjectLayout(showButtons);
		String lowerBound = options == null ? "" : options.get("lower_bound").toString();
		String upperBound = options == null ? "" : options.get("upper_bound").toString();
		String precision = options == null ? "" : options.get("precision").toString();
		
		Label lowerL = ComponentGenerator.INSTANCE.generateLabel(UIMessages.LABEL_LOWER_BOUND);
		TextField lowerF = ComponentGenerator.INSTANCE.generateTextField(lowerBound);		
		Label upperL = ComponentGenerator.INSTANCE.generateLabel(UIMessages.LABEL_UPPER_BOUND);
		TextField upperF = ComponentGenerator.INSTANCE.generateTextField(upperBound);
		Label precisionL = ComponentGenerator.INSTANCE.generateLabel(UIMessages.LABEL_PRECISION);
		TextField precisionF = ComponentGenerator.INSTANCE.generateTextField(precision);

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

}
