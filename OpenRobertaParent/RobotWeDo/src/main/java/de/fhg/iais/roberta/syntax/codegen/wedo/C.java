package de.fhg.iais.roberta.syntax.codegen.wedo;

public class C {

    public static final String ADD = "ADD";
    public static final String AMBIENTLIGHT = "ambientlight";
    public static final String ANALOG = "analog";
    public static final String AND = "AND";
    public static final String ANGLE = "angle";
    public static final String ANIMATION = "animation";
    public static final String ANY = "any";
    public static final String ARG1 = "arg1";
    public static final String ARG2 = "arg2";
    public static final String ARRAY_BOOLEAN = "Array_Boolean";
    public static final String ARRAY_COLOR = "Array_Colour";
    public static final String ARRAY_IMAGE = "Array_Image";
    public static final String ARRAY_NUMBER = "Array_Number";
    public static final String ARRAY_STRING = "Array_String";
    public static final String ASSIGN_METHOD_PARAMETER_STMT = "AssignMethodParameter";
    public static final String ASSIGN_STMT = "AssignStmt";
    public static final String AT1 = "at1";
    public static final String AT2 = "at2";
    public static final String AVERAGE = "average";
    public static final String BACKWARD = "BACKWARD";
    public static final String BINARY = "Binary";
    public static final String BOOLEAN = "BOOLEAN";
    public static final String BOOL_CONST = "BoolConst";
    public static final String BREAK = "break";
    public static final String BRIGHTNESS = "brightness";
    public static final String BUTTONS = "buttons";
    public static final String BUTTON_A = "A";
    public static final String BUTTON_B = "B";
    public static final String CHARACTER = "character";
    public static final String CLEAR_DISPLAY_ACTION = "ClearDisplay";
    public static final String COLOR = "color";
    public static final String COLOR_CONST = "COLOR_CONST";
    public static final String COLOUR = "colorValue";
    public static final String COMPASS = "compass";
    public static final String CONTINUE = "continue";
    public static final String CREATE_DEBUG_ACTION = "CreateDebugAction";
    public static final String CREATE_LISTS_GET_INDEX = "createListsGetIndex";
    public static final String CREATE_LISTS_GET_INDEX_STMT = "createListsGetIndexStmt";
    public static final String CREATE_LISTS_GET_SUBLIST = "createGetSubList";
    public static final String CREATE_LISTS_SET_INDEX = "createListsSetIndex";
    public static final String CREATE_LIST_FIND_ITEM = "createListFindItem";
    public static final String CREATE_LIST_IS_EMPTY = "isListEmpty";
    public static final String CREATE_LIST_LENGTH = "listLength";
    public static final String CREATE_LIST_WITH_ITEM = "createCreateListWithItem";
    public static final String CURVE_ACTION = "CurveAction";
    public static final String DEGREE = "DEGREE";
    public static final String DIGITAL = "digital";
    public static final String DIRECTION = "direction";
    public static final String DISPLAY_GET_BRIGHTNESS_ACTION = "displayGetBrightnessAction";
    public static final String DISPLAY_GET_PIXEL_ACTION = "displayGetPixelAction";
    public static final String DISPLAY_IMAGE_ACTION = "DisplayImageAction";
    public static final String DISPLAY_SET_BRIGHTNESS_ACTION = "displaySetBrightnessAction";
    public static final String DISPLAY_SET_PIXEL_ACTION = "displaySetPixelAction";
    public static final String DISPLAY_TEXT_ACTION = "displayTextAction";
    public static final String DISTANCE = "distance";
    public static final String DIVIDE = "DIVIDE";
    public static final String DOUBLE_FLASH = "DOUBLE_FLASH";
    public static final String DOWN = "down";
    public static final String DRIVE_ACTION = "DriveAction";
    public static final String DRIVE_DIRECTION = "driveDirection";
    public static final String DURATION = "duration";
    public static final String EACH_COUNTER = "eachcounter";
    public static final String ELSE_STMTS = "elseStmts";
    public static final double ENC = 360.0 / (3.0 * Math.PI * 5.6);
    public static final String ENCODER_SENSOR_RESET = "encoderSensorReset";
    public static final String ENCODER_SENSOR_SAMPLE = "encoderSensorSample";
    public static final String ENTER = "enter";
    public static final String EQ = "EQ";
    public static final String ESCAPE = "escape";
    public static final String EXPR = "expr";
    public static final String EXPR_LIST = "exprList";
    public static final String FACE_DOWN = "face_down";
    public static final String FACE_UP = "face_up";
    public static final String FILE = "file";
    public static final String FIRST = "first";
    public static final String FLASH = "FLASH";
    public static final String FLOW_CONTROL = "flowControl";
    public static final String FOR = "FOR";
    public static final String FOREVER = "FOREVER";
    public static final String FOREWARD = "FOREWARD";
    public static final String FOR_EACH = "forEach";
    public static final String FREEFALL = "freefall";
    public static final String FREQUENCY = "frequency";
    public static final String FROM_END = "fromEnd";
    public static final String FROM_START = "fromStart";
    public static final String FUNCTION_DECLARATION = "functionDeclaration";
    public static final String GESTURE = "gesture";
    public static final String GET = "get";
    public static final String GET_GYRO_SENSOR_SAMPLE = "gyroSensorSample";
    public static final String GET_REMOVE = "getRemove";
    public static final String GET_SAMPLE = "GetSample";
    public static final String GET_VOLUME = "GetVolume";
    public static final String GREEN = "GREEN";
    public static final String GT = "GT";
    public static final String GTE = "GTE";
    public static final String GYRO = "gyro";
    public static final String GYRO_SENSOR_RESET = "createResetGyroSensor";
    public static final String IF_RETURN = "createMethodIfReturn";
    public static final String IF_STMT = "IfStatement";
    public static final String IMAGE = "image";
    public static final String IMAGE_CONST = "image";
    public static final String IMAGE_INVERT_ACTION = "ImageInvertAction";
    public static final String IMAGE_SHIFT_ACTION = "ImageShiftAction";
    public static final String IN = "in";
    public static final String INFRARED = "infrared";
    public static final String INSERT = "insert";
    public static final String ITEM = "item";
    public static final String KIND = "kind";
    public static final String LANGUAGE = "language";
    public static final String LAST = "last";
    public static final String LED_COLOR_CONST = "ledColor";
    public static final String LED_ON_ACTION = "ledOnAction";
    public static final String LEFT = "left";
    public static final String LIGHT = "light";
    public static final String LIGHT_ACTION = "lightSensorAction";
    public static final String LIST = "list";
    public static final String LOOP_NUMBER = "loopNumber";
    public static final String LT = "LT";
    public static final String LTE = "LTE";
    public static final String MATH_CHANGE = "mathChange";
    public static final String MATH_CONST = "createMathConstant";
    public static final String MATH_ON_LIST = "createMathOnList";
    public static final String MATH_PROP_FUNCT = "MathPropFunct";
    public static final String MATH_CONSTRAIN_FUNCTION = "MathConstrainFunct";
    public static final String MAX = "max";
    public static final int MAXDIAG = 2500;
    public static final double MAXPOWER = 0.351858377 * 3; // real Robot drives approx. 35 cm / 1 sec -> 105 pix/sec | 3pix   =  1cm
    public static final int MAX_HEIGHT = 1000;
    public static final int MAX_WIDTH = 2000;
    public static final String MEDIAN = "median";
    public static final String METHOD_CALL_RETURN = "createMethodCallReturn";
    public static final String METHOD_CALL_VOID = "createMethodCallVoid";
    public static final String METHOD_RETURN = "createMethodReturn";
    public static final String METHOD_VOID = "createMethodVoid";
    public static final String MIN = "min";
    public static final String MINUS = "MINUS";
    public static final String MOD = "MOD";
    public static final String MODE = "mode";
    public static final String MOTOR_ACTION = "motorAction";
    public static final String MOTOR_DURATION = "motorDuration";
    public static final String MOTOR_DURATION_VALUE = "motorDurationValue";
    public static final String MOTOR_GET_POWER = "motorGetPowerAction";
    public static final String MOTOR_LEFT = "C";
    public static final String MOTOR_MOVE_MODE = "motorMoveMode";
    public static final String MOTOR_ON_ACTION = "motorOnAction";
    public static final String MOTOR_RIGHT = "B";
    public static final String MOTOR_SET_POWER = "motorSetPowerAction";
    public static final String MOTOR_SIDE = "motorSide";
    public static final String MOTOR_STOP = "motorStop";
    public static final String MOTOR_XA = "XA";
    public static final String MOTOR_XAB = "XAB";
    public static final String MOTOR_XB = "XB";
    public static final String MULTIPLY = "MULTIPLY";
    public static final String N = "n";
    public static final String NAME = "name";
    public static final String NAMES = "names";
    public static final String NEG = "NEG";
    public static final String NEQ = "NEQ";
    public static final String NOOP_STMT = "createNoopStmt";
    public static final String NOT = "NOT";
    public static final String NULL_CONST = "null";
    public static final String NUMBER = "NUMBER";
    public static final String NUMERIC = "Numeric";
    public static final String NUM_CONST = "NumConst";
    public static final String OFF = "OFF";
    public static final String ON = "ON";
    public static final String OP = "op";
    public static final String STEP = "step";
    public static final String END = "end";
    public static final String OPCODE = "opc";
    public static final String OR = "OR";
    public static final String ORANGE = "ORANGE";
    public static final String PARAMETERS = "parameters";
    public static final String PICTURE = "picture";
    public static final String PILOT = "pilot";
    public static final String PIN = "pin";
    public static final String PIN_GET_VALUE_SENSOR = "createPinGetValueSensor";
    public static final String PIN_TOUCH_SENSOR = "createPinTouchSensor";
    public static final String PIN_WRITE_VALUE_SENSOR = "createPinWriteValueSensor";
    public static final String PITCH = "pitch";
    public static final String PIXEL = "pixel";
    public static final String PLAY_FILE_ACTION = "PlayFileAction";
    public static final String POSITION = "position";
    public static final String POWER = "POWER";
    public static final String PORT = "port";
    public static final String PRESENCE = "presence";
    public static final String RANDOM = "random";
    public static final String RANDOM_DOUBLE = "randDouble";
    public static final String RANDOM_INT = "randInt";
    public static final String RATE = "rate";
    public static final String RED = "red";
    public static final String REMOVE = "remove";
    public static final String REPEAT_STMT = "RepeatStmt";
    public static final String RESET = "reset";
    public static final String RETURN = "return";
    public static final String RETURN_TYPE = "returnType";
    public static final String RGB = "rgb";
    public static final String RGB_COLOR_CONST = "rgbColor";
    public static final String RIGHT = "right";
    public static final String ROTATION = "ROTATION";
    public static final String ROTATIONS = "ROTATIONS";
    public static final String SAY_TEXT_ACTION = "SayTextAction";
    public static final String SEEK = "presence";
    public static final String SENSOR_MODE = "sensorMode";
    public static final String SENSOR_TYPE = "sensorType";
    public static final String SET = "set";
    public static final String SET_LANGUAGE_ACTION = "SetLanguageAction";
    public static final String SET_VOLUME_ACTION = "SetVolumeAction";
    public static final String SHAKE = "shake";
    public static final String SHOW_PICTURE_ACTION = "ShowPictureAction";
    public static final String SHOW_TEXT_ACTION = "ShowTextAction";
    public static final String SINGLE_FUNCTION = "SingleFunction";
    public static final String SIZE = "size";
    public static final String SLOT = "slot";
    public static final String SOUND = "sound";
    public static final String SPEED = "speed";
    public static final String SPEED_L = "speedL";
    public static final String SPEED_R = "speedR";
    public static final String STATEMENTS = "statements";
    public static final String STATUS_LIGHT_ACTION = "statusLightAction";
    public static final String STD_DEV = "stdDev";
    public static final String STMT_LIST = "stmtList";
    public static final String STOP_DRIVE = "stopDrive";
    public static final String STRING = "STRING";
    public static final String STRING_CONST = "StringConst";
    public static final String SUM = "sum";
    public static final String TEMPERATURE = "temperature";
    public static final String TERNARY_EXPR = "createTernaryExpr";
    public static final String TEXT = "text";
    public static final String TEXT_APPEND = "textAppend";
    public static final String TEXT_JOIN = "createTextJoin";
    public static final String THEN_LIST = "thenList";
    public static final String THEN_STMTS = "thenStmts";
    public static final String TIME = "time";
    public static final String TIMER = "timer";
    public static final String TIMER_SENSOR_RESET = "createResetTimer";
    public static final String TIMES = "TIMES";
    public static final String TONE_ACTION = "ToneAction";
    public static final String TOUCH = "touch";
    public static final int TRACKWIDTH = 40;
    public static final String TURN_ACTION = "TurnAction";
    public static final String TURN_DIRECTION = "turnDirection";
    public static final String TURN_LIGHT = "turnLight";
    public static final double TURN_RATIO = (40 / 3.) / 2.8;
    public static final String TYPE = "type";
    public static final String ULTRASONIC = "ultrasonic";
    public static final String UNARY = "Unary";
    public static final String UNTIL = "UNTIL";
    public static final String UP = "up";
    public static final String VALUE = "value";
    public static final String VALUES = "values";
    public static final String VAR = "Var";
    public static final String VAR_DECLARATION = "VarDeclaration";
    public static final String VOLUME = "volume";
    public static final String WAIT_STMT = "WaitStmt";
    public static final String WAIT_TIME_STMT = "WaitTimeSTMT";
    public static final int WAVE_LENGTH = 60;
    public static final double WHEEL_DIAMETER = 5.6;
    public static final String WHERE1 = "where1";
    public static final String WHERE2 = "where2";
    public static final String WHILE = "WHILE";
    public static final String X = "x";
    public static final String Y = "y";

    public static enum Colors {
        NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN;
    }
}