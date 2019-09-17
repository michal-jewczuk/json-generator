package nodomain.jsongenerator.data;

import org.json.JSONObject;

import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.generator.BoolFragmentGenerator;
import nodomain.jsongenerator.generator.DateFragmentGenerator;
import nodomain.jsongenerator.generator.DoubleFragmentGenerator;
import nodomain.jsongenerator.generator.NumberFragmentGenerator;
import nodomain.jsongenerator.generator.ObjectFragmentGenerator;
import nodomain.jsongenerator.generator.PatternFragmentGenerator;
import nodomain.jsongenerator.generator.StringFragmentGenerator;
import nodomain.jsongenerator.gui.processors.BoolItemProcessor;
import nodomain.jsongenerator.gui.processors.DateItemProcessor;
import nodomain.jsongenerator.gui.processors.DoubleItemProcessor;
import nodomain.jsongenerator.gui.processors.NumberItemProcessor;
import nodomain.jsongenerator.gui.processors.ObjectItemProcessor;
import nodomain.jsongenerator.gui.processors.PatternItemProcessor;
import nodomain.jsongenerator.gui.processors.StringItemProcessor;

public enum DataType {

	JSON_STRING {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return StringFragmentGenerator.INSTANCE.generateFragment(name, dataOptions);
		}

		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return StringItemProcessor.INSTANCE.fromeGuiToJSON(JSON_STRING, pane);
		}
	},
	JSON_NUMBER {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return NumberFragmentGenerator.INSTANCE.generateFragment(name, dataOptions);
		}

		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return NumberItemProcessor.INSTANCE.fromeGuiToJSON(JSON_NUMBER, pane);
		}
	},
	JSON_PATTERN {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return PatternFragmentGenerator.INSTANCE.generateFragment(name, dataOptions);
		}

		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return PatternItemProcessor.INSTANCE.fromeGuiToJSON(JSON_PATTERN, pane);
		}		
	},
	JSON_BOOL {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return BoolFragmentGenerator.INSTANCE.generateFragment(name, dataOptions);
		}

		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return BoolItemProcessor.INSTANCE.fromeGuiToJSON(JSON_BOOL, pane);
		}
	},
	JSON_DOUBLE {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return DoubleFragmentGenerator.INSTANCE.generateFragment(name, dataOptions);
		}

		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return DoubleItemProcessor.INSTANCE.fromeGuiToJSON(JSON_DOUBLE, pane);
		}
	},
	JSON_DATE {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return DateFragmentGenerator.INSTANCE.generateFragment(name, dataOptions);
		}

		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return DateItemProcessor.INSTANCE.fromeGuiToJSON(JSON_DATE, pane);
		}
	},
	JSON_OBJECT {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return ObjectFragmentGenerator.INSTANCE.generateFragment(name, dataOptions);
		}

		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return ObjectItemProcessor.INSTANCE.fromeGuiToJSON(JSON_OBJECT, pane);
		}
	};
	
	public abstract StringBuilder createJsonFragment(String name, JSONObject dataOptions);
	public abstract StringBuilder convertToJSON(GridPane pane);
	
}
