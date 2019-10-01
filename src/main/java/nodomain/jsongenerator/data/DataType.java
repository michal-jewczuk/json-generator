package nodomain.jsongenerator.data;

import org.json.JSONObject;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.exceptions.ValidationException;
import nodomain.jsongenerator.generator.BoolKVGenerator;
import nodomain.jsongenerator.generator.DateKVGenerator;
import nodomain.jsongenerator.generator.DoubleKVGenerator;
import nodomain.jsongenerator.generator.NumberKVGenerator;
import nodomain.jsongenerator.generator.ObjectKVGenerator;
import nodomain.jsongenerator.generator.PatternKVGenerator;
import nodomain.jsongenerator.generator.StringKVGenerator;
import nodomain.jsongenerator.gui.generators.BoolElementGenerator;
import nodomain.jsongenerator.gui.generators.DateElementGenerator;
import nodomain.jsongenerator.gui.generators.DoubleElementGenerator;
import nodomain.jsongenerator.gui.generators.NumberElementGenerator;
import nodomain.jsongenerator.gui.generators.ObjectElementGenerator;
import nodomain.jsongenerator.gui.generators.PatternElementGenerator;
import nodomain.jsongenerator.gui.generators.StringElementGenerator;
import nodomain.jsongenerator.gui.processors.BoolItemProcessor;
import nodomain.jsongenerator.gui.processors.DateItemProcessor;
import nodomain.jsongenerator.gui.processors.DoubleItemProcessor;
import nodomain.jsongenerator.gui.processors.NumberItemProcessor;
import nodomain.jsongenerator.gui.processors.ObjectItemProcessor;
import nodomain.jsongenerator.gui.processors.PatternItemProcessor;
import nodomain.jsongenerator.gui.processors.StringItemProcessor;
import nodomain.jsongenerator.validators.BoolValidator;
import nodomain.jsongenerator.validators.DateValidator;
import nodomain.jsongenerator.validators.DoubleValidator;
import nodomain.jsongenerator.validators.NumberValidator;
import nodomain.jsongenerator.validators.ObjectValidator;
import nodomain.jsongenerator.validators.PatternValidator;
import nodomain.jsongenerator.validators.StringValidator;

public enum DataType {

	JSON_BOOL {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return BoolKVGenerator.INSTANCE.generateKeyValue(name, dataOptions);
		}

		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return BoolItemProcessor.INSTANCE.fromGuiToJSON(JSON_BOOL, pane);
		}

		@Override
		public Node createGUIElement(String name, JSONObject dataOptions, boolean showButtons) {
			return BoolElementGenerator.INSTANCE.generateElement(name, dataOptions, showButtons);
		}

		@Override
		public boolean validate(JSONObject structure) throws ValidationException {
			return BoolValidator.INSTANCE.validateItem(structure);
		}
	},	

	JSON_DATE {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return DateKVGenerator.INSTANCE.generateKeyValue(name, dataOptions);
		}

		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return DateItemProcessor.INSTANCE.fromGuiToJSON(JSON_DATE, pane);
		}

		@Override
		public Node createGUIElement(String name, JSONObject dataOptions, boolean showButtons) {
			return DateElementGenerator.INSTANCE.generateElement(name, dataOptions, showButtons);
		}

		@Override
		public boolean validate(JSONObject structure) throws ValidationException {
			return DateValidator.INSTANCE.validateItem(structure);
		}
	},	
	
	JSON_DOUBLE {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return DoubleKVGenerator.INSTANCE.generateKeyValue(name, dataOptions);
		}

		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return DoubleItemProcessor.INSTANCE.fromGuiToJSON(JSON_DOUBLE, pane);
		}

		@Override
		public Node createGUIElement(String name, JSONObject dataOptions, boolean showButtons) {
			return DoubleElementGenerator.INSTANCE.generateElement(name, dataOptions, showButtons);
		}

		@Override
		public boolean validate(JSONObject structure) throws ValidationException {
			return DoubleValidator.INSTANCE.validateItem(structure);
		}
	},
	
	JSON_NUMBER {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return NumberKVGenerator.INSTANCE.generateKeyValue(name, dataOptions);
		}

		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return NumberItemProcessor.INSTANCE.fromGuiToJSON(JSON_NUMBER, pane);
		}

		@Override
		public Node createGUIElement(String name, JSONObject dataOptions, boolean showButtons) {
			return NumberElementGenerator.INSTANCE.generateElement(name, dataOptions, showButtons);
		}

		@Override
		public boolean validate(JSONObject structure) throws ValidationException {
			return NumberValidator.INSTANCE.validateItem(structure);
		}
	},
	
	JSON_OBJECT {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return ObjectKVGenerator.INSTANCE.generateKeyValue(name, dataOptions);
		}

		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return ObjectItemProcessor.INSTANCE.fromGuiToJSON(JSON_OBJECT, pane);
		}

		@Override
		public Node createGUIElement(String name, JSONObject dataOptions, boolean showButtons) {
			return ObjectElementGenerator.INSTANCE.generateElement(name, dataOptions, showButtons);
		}

		@Override
		public boolean validate(JSONObject structure) throws ValidationException {
			return ObjectValidator.INSTANCE.validateItem(structure);
		}
	},
	
	JSON_PATTERN {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return PatternKVGenerator.INSTANCE.generateKeyValue(name, dataOptions);
		}

		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return PatternItemProcessor.INSTANCE.fromGuiToJSON(JSON_PATTERN, pane);
		}

		@Override
		public Node createGUIElement(String name, JSONObject dataOptions, boolean showButtons) {
			return PatternElementGenerator.INSTANCE.generateElement(name, dataOptions, showButtons);
		}

		@Override
		public boolean validate(JSONObject structure) throws ValidationException {
			return PatternValidator.INSTANCE.validateItem(structure);
		}		
	},	
	
	JSON_STRING {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return StringKVGenerator.INSTANCE.generateKeyValue(name, dataOptions);
		}

		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return StringItemProcessor.INSTANCE.fromGuiToJSON(JSON_STRING, pane);
		}

		@Override
		public Node createGUIElement(String name, JSONObject dataOptions, boolean showButtons) {
			return StringElementGenerator.INSTANCE.generateElement(name, dataOptions, showButtons);
		}

		@Override
		public boolean validate(JSONObject structure) throws ValidationException {
			return StringValidator.INSTANCE.validateItem(structure);
		}
	};
	
	public abstract StringBuilder createJsonFragment(String name, JSONObject dataOptions);
	public abstract StringBuilder convertToJSON(GridPane pane);
	public abstract Node createGUIElement(String name, JSONObject dataOptions, boolean showButtons);
	public abstract boolean validate(JSONObject structure) throws ValidationException;
}
