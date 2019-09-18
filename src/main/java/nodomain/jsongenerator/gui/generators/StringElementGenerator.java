package nodomain.jsongenerator.gui.generators;

import org.json.JSONObject;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.data.StringDataOptions;
import nodomain.jsongenerator.data.parsers.StringDataOptionsParser;

public enum StringElementGenerator implements ElementGenerator {
	
	INSTANCE;

	@Override
	public Node generateElement(String name, JSONObject rawOptions, boolean showButtons) {
		StringDataOptions options = StringDataOptionsParser.INSTANCE.parseDataOptions(rawOptions);
		BorderPane pane = createObjectLayout(showButtons);
		
		Label minLengthL = new Label("min length");
		TextField minLengthF = new TextField(String.valueOf(options.getMinLength()));
		Label maxLengthL = new Label("max length");
		TextField maxLengthF = new TextField(String.valueOf(options.getMaxLength()));
		CheckBox firstCapCB = new CheckBox("first capital");
		firstCapCB.setSelected(options.isFirstCapital());
		CheckBox allCapCB = new CheckBox("all capital");
		allCapCB.setSelected(options.isAllCapital());
		
		GridPane gp = createGrid(name);
		gp.add(minLengthL, 0, 1);
		gp.add(minLengthF, 1, 1);
		gp.add(maxLengthL, 0, 2);
		gp.add(maxLengthF, 1, 2);
		gp.add(firstCapCB, 1, 3);
		gp.add(allCapCB, 1, 4);
		
		pane.setCenter(gp);
		return pane;
	}

}
