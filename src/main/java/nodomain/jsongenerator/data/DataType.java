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
import nodomain.jsongenerator.gui.generators.BoolGenerator;
import nodomain.jsongenerator.gui.generators.DateGenerator;
import nodomain.jsongenerator.gui.generators.DoubleGenerator;
import nodomain.jsongenerator.gui.generators.NumberGenerator;
import nodomain.jsongenerator.gui.generators.ObjectGenerator;
import nodomain.jsongenerator.gui.generators.PatternGenerator;
import nodomain.jsongenerator.gui.generators.StringGenerator;
import nodomain.jsongenerator.gui.processors.BoolProcessor;
import nodomain.jsongenerator.gui.processors.DateProcessor;
import nodomain.jsongenerator.gui.processors.DoubleProcessor;
import nodomain.jsongenerator.gui.processors.NumberProcessor;
import nodomain.jsongenerator.gui.processors.ObjectProcessor;
import nodomain.jsongenerator.gui.processors.PatternProcessor;
import nodomain.jsongenerator.gui.processors.StringProcessor;
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
			return BoolProcessor.INSTANCE.fromGuiToJSON(JSON_BOOL, pane);
		}

		@Override
		public Node createGUIElement(String name, JSONObject dataOptions, boolean showButtons) {
			return BoolGenerator.INSTANCE.generateItem(name, dataOptions, showButtons);
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
			return DateProcessor.INSTANCE.fromGuiToJSON(JSON_DATE, pane);
		}

		@Override
		public Node createGUIElement(String name, JSONObject dataOptions, boolean showButtons) {
			return DateGenerator.INSTANCE.generateItem(name, dataOptions, showButtons);
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
			return DoubleProcessor.INSTANCE.fromGuiToJSON(JSON_DOUBLE, pane);
		}

		@Override
		public Node createGUIElement(String name, JSONObject dataOptions, boolean showButtons) {
			return DoubleGenerator.INSTANCE.generateItem(name, dataOptions, showButtons);
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
			return NumberProcessor.INSTANCE.fromGuiToJSON(JSON_NUMBER, pane);
		}

		@Override
		public Node createGUIElement(String name, JSONObject dataOptions, boolean showButtons) {
			return NumberGenerator.INSTANCE.generateItem(name, dataOptions, showButtons);
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
			return ObjectProcessor.INSTANCE.fromGuiToJSON(JSON_OBJECT, pane);
		}

		@Override
		public Node createGUIElement(String name, JSONObject dataOptions, boolean showButtons) {
			return ObjectGenerator.INSTANCE.generateItem(name, dataOptions, showButtons);
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
			return PatternProcessor.INSTANCE.fromGuiToJSON(JSON_PATTERN, pane);
		}

		@Override
		public Node createGUIElement(String name, JSONObject dataOptions, boolean showButtons) {
			return PatternGenerator.INSTANCE.generateItem(name, dataOptions, showButtons);
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
			return StringProcessor.INSTANCE.fromGuiToJSON(JSON_STRING, pane);
		}

		@Override
		public Node createGUIElement(String name, JSONObject dataOptions, boolean showButtons) {
			return StringGenerator.INSTANCE.generateItem(name, dataOptions, showButtons);
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
