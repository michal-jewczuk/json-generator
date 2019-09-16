package nodomain.jsongenerator.gui.domain;

import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.gui.processors.BoolItemProcessor;
import nodomain.jsongenerator.gui.processors.DateItemProcessor;
import nodomain.jsongenerator.gui.processors.DoubleItemProcessor;
import nodomain.jsongenerator.gui.processors.NumberItemProcessor;
import nodomain.jsongenerator.gui.processors.ObjectItemProcessor;
import nodomain.jsongenerator.gui.processors.PatternItemProcessor;
import nodomain.jsongenerator.gui.processors.StringItemProcessor;

public enum GUIDataType {

	JSON_STRING {
		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return StringItemProcessor.INSTANCE.fromeGuiToJSON(JSON_STRING, pane);
		}
	},
	JSON_NUMBER {
		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return NumberItemProcessor.INSTANCE.fromeGuiToJSON(JSON_NUMBER, pane);
		}
	},
	JSON_PATTERN {
		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return PatternItemProcessor.INSTANCE.fromeGuiToJSON(JSON_PATTERN, pane);
		}
	},
	JSON_BOOL {
		@Override
		public StringBuilder convertToJSON(GridPane pane) {	
			return BoolItemProcessor.INSTANCE.fromeGuiToJSON(JSON_BOOL, pane);
		}
	},
	JSON_DOUBLE {
		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return DoubleItemProcessor.INSTANCE.fromeGuiToJSON(JSON_DOUBLE, pane);
		}
	},
	JSON_DATE {
		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return DateItemProcessor.INSTANCE.fromeGuiToJSON(JSON_DATE, pane);
		}
	},
	JSON_OBJECT {
		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			return ObjectItemProcessor.INSTANCE.fromeGuiToJSON(JSON_OBJECT, pane);
		}
	};
	
	public abstract StringBuilder convertToJSON(GridPane pane);
	
}
