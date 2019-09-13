package nodomain.jsongenerator.gui.domain;

import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.gui.processors.BoolItemProcessor;
import nodomain.jsongenerator.gui.processors.PatternItemProcessor;

public enum GUIDataType {

	JSON_STRING {
		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			System.out.println("Processing string...");
			return null;
		}
	},
	JSON_NUMBER {
		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			System.out.println("Processing number...");
			return null;
		}
	},
	JSON_PATTERN {
		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			System.out.println("Processing pattern...");
			return PatternItemProcessor.INSTANCE.fromeGuiToJSON(JSON_PATTERN, pane);
		}
	},
	JSON_BOOL {
		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			System.out.println("Processing bool...");		
			return BoolItemProcessor.INSTANCE.fromeGuiToJSON(JSON_BOOL, pane);
		}
	},
	JSON_DOUBLE {
		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			System.out.println("Processing double...");
			return null;
		}
	},
	JSON_DATE {
		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			System.out.println("Processing date...");
			return null;
		}
	},
	JSON_OBJECT {
		@Override
		public StringBuilder convertToJSON(GridPane pane) {
			System.out.println("Processing object...");
			return null;
		}
	};
	
	public abstract StringBuilder convertToJSON(GridPane pane);
	
}
