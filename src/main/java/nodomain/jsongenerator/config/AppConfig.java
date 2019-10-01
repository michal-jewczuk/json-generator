package nodomain.jsongenerator.config;

public class AppConfig {

	public static final String CONFIGURATION_FILE = "structure.json";
	public static final String DEFAULT_OUTPUT_NAME = "data.json";
	public static final String DEFAULT_ARRAY_NAME = "data";
	public static final String STRING_SYMBOLS = "abcdefghijklmnopqrstuvwyz";
	public static final String DEFAULT_CONNECTOR = " ";
	public static final String EMPTY_STRUCTURE = "{\"types\": []}";
	public static final String STARTING_DIR = "user.home";
	
	public static final String APP_NAME = "JSON Generator";
	
	public static final String ERROR_PARSING = "Error parsing element: ";
	public static final String NOT_A_JSON = "Given file does not have a valid json configuration structure.";
	public static final String INVALID_ELEMENTS = "Given file contains invalid elements.";
	public static final String OTHER_PARSING_ERROR = "There was an undentified error in given file.";
	public static final String ERROR_LOADING_DEFAULT = "There was a problem loading default structure";
	
	public static final double SCENE_WIDTH = 700.0;
	public static final double SCENE_HEIGHT = 800.0;
	public static final double TF_MAX_WIDTH = 500.0;
	public static final double POPUP_WIDTH = 400.0;
	public static final double POPUP_HEIGHT = 400.0;
}
