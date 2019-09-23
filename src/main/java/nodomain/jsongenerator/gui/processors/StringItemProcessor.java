package nodomain.jsongenerator.gui.processors;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.DataType;
import nodomain.jsongenerator.data.StringDataOptions;
import nodomain.jsongenerator.gui.domain.Element;

public enum StringItemProcessor implements ItemProcessor {
	
	INSTANCE;

	@Override
	public Element fromGuiToElement(DataType type, GridPane pane) {
		Element element = createGenericElement(type, pane);
		
		int minLength = Integer.valueOf(((TextField) pane.getChildren().get(3)).getText());
		int maxLength = Integer.valueOf(((TextField) pane.getChildren().get(5)).getText());
		boolean firstCap = ((CheckBox) pane.getChildren().get(6)).isSelected();
		boolean allCaps = ((CheckBox) pane.getChildren().get(7)).isSelected();
		
		StringDataOptions options = new StringDataOptions.Builder()
				.minLength(minLength)
				.maxLength(maxLength)
				.firstCapital(firstCap)
				.allCapital(allCaps)
				.build();
		element.setOptions(options);
		
		return element;
	}

	@Override
	public StringBuilder returnOptionsAsString(DataOptions options) {
		StringDataOptions datao = (StringDataOptions) options;
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"min_length\": ").append(datao.getMinLength()).append(",");
		sb.append("\"max_length\": ").append(datao.getMaxLength()).append(",");
		sb.append("\"first_cap\": ").append(datao.isFirstCapital()).append(",");
		sb.append("\"all_cap\": ").append(datao.isAllCapital());
		sb.append("}");
		
		return sb;
	}

	@Override
	public StringBuilder extractOptionsFromGUI(GridPane pane) {
		String minLength = ((TextField) pane.getChildren().get(3)).getText();
		String maxLength = ((TextField) pane.getChildren().get(5)).getText();
		boolean firstCap = ((CheckBox) pane.getChildren().get(6)).isSelected();
		boolean allCaps = ((CheckBox) pane.getChildren().get(7)).isSelected();
		
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"min_length\": ").append(minLength).append(",");
		sb.append("\"max_length\": ").append(maxLength).append(",");
		sb.append("\"first_cap\": ").append(firstCap).append(",");
		sb.append("\"all_cap\": ").append(allCaps);
		sb.append("}");
		
		return sb;
	}

}
