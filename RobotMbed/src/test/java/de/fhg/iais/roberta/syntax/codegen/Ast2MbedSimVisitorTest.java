package de.fhg.iais.roberta.syntax.codegen;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import de.fhg.iais.roberta.testutil.Helper;

public class Ast2MbedSimVisitorTest {

    @BeforeClass
    public static void setupConfigurationForAllTests() {

    }

    @Test
    public void visitLightStatusAction_TurnOffLed_ReturnsCorrectJavaScriptProgram() throws Exception {
        String expectedResult = "" //
            + "var stmt0 = createStatusLight(CONST.OFF);\n"
            + "var blocklyProgram = {'programStmts': [stmt0]};";
        assertCodeIsOk(expectedResult, "/action/led_off.xml");
    }

    @Test
    public void visitClearDisplayAction_ScriptWithClearDisplay_ReturnsJavaScriptProgramClearDisplay() throws Exception {
        String expectedResult = "" //
            + "var stmt0 = createClearDisplayAction();\n"
            + "var blocklyProgram = {'programStmts': [stmt0]};";

        assertCodeIsOk(expectedResult, "/action/display_clear.xml");
    }

    @Test
    public void visitDisplayText_ShowHelloScript_ReturnsJavaScriptProgramWithShowTextCall() throws Exception {
        String expectedResult = "" //
            + "var stmt0 = createDisplayTextAction(createConstant(CONST.STRING_CONST, 'Hallo'));\n"
            + "var blocklyProgram = {'programStmts': [stmt0]};";

        assertCodeIsOk(expectedResult, "/action/display_text_show_hello.xml");
    }

    @Test
    public void visitBrickSensor_ScriptChecksKeyAStatus_ReturnsJavaScriptProgram() throws Exception {
        String expectedResult = "" //
            + "var stmt0 = createDisplayTextAction(createGetSample(CONST.BUTTONS, CONST.BUTTON_A));\n"
            + "var blocklyProgram = {'programStmts': [stmt0]};";
        assertCodeIsOk(expectedResult, "/sensor/check_if_key_A_is_pressed.xml");
    }

    @Test
    public void visitCompassSensor_ScriptDisplayCompassHeading_ReturnsJavaScriptProgram() throws Exception {
        String expectedResult = "" //
            + "var stmt0 = createDisplayTextAction(createGetSample(CONST.COMPASS));\n"
            + "var blocklyProgram = {'programStmts': [stmt0]};";

        assertCodeIsOk(expectedResult, "/sensor/get_compass_orientation_value.xml");
    }

    @Test
    public void visitDisplayImageAction_ScriptWithDisplayImageAndAnimation_ReturnsCppProgramWithDisplayImageAndAnimation() throws Exception {
        String expectedResult = "" //
            + "var stmt0 = createDisplayImageAction(CONST.IMAGE, '0,255,0,255,0\\n255,255,255,255,255\\n255,255,255,255,255\\n0,255,255,255,0\\n0,0,255,0,0\\n');\n"
            + "var stmt1 = createDisplayImageAction(CONST.ANIMATION, createCreateListWith(CONST.ARRAY_IMAGE, ['0,0,0,0,0\\n0,255,0,255,0\\n0,255,255,255,0\\n0,0,255,0,0\\n0,0,0,0,0\\n', '0,0,0,0,0\\n255,255,0,255,255\\n0,0,0,0,0\\n0,255,255,255,0\\n0,0,0,0,0\\n']));\n"
            + "var blocklyProgram = {'programStmts': [stmt0,stmt1]};";

        assertCodeIsOk(expectedResult, "/action/display_image_show_imag_and_animation.xml");
    }

    @Test
    public void visitDisplayImageAction_ScriptWithMissinImageToDisplay_ReturnsJavaScriptProgramWithMissingImageToDisplay() throws Exception {
        String expectedResult = "" //
            + "var stmt0 = createDisplayImageAction(CONST.IMAGE, createConstant(CONST.STRING_CONST, ''));\n"
            + "var blocklyProgram = {'programStmts': [stmt0]};";

        assertCodeIsOk(expectedResult, "/action/display_image_missing_image_name.xml");
    }

    @Test
    public void visitPlayNoteAction_ScriptPlayNote_ReturnsJavaScriptProgramWithPlayNote() throws Exception {
        String expectedResult = "" //
            + "var stmt0 = createToneAction(createConstant(CONST.NUM_CONST, 261.626), createConstant(CONST.NUM_CONST, 2000));\n"
            + "var blocklyProgram = {'programStmts': [stmt0]};";

        assertCodeIsOk(expectedResult, "/action/play_note.xml");
    }

    @Test
    public void visitImage_ScriptCreatingImage_ReturnsJavaScriptProgram() throws Exception {
        String expectedResult = "" //
            + "var stmt0 = createDisplayImageAction(CONST.IMAGE, createConstant(CONST.IMAGE, [[255,255,0,0,0],[0,0,0,0,255],[0,85,0,0,0],[0,0,0,255,0],[0,56,0,0,0]]));\n"
            + "var blocklyProgram = {'programStmts': [stmt0]};";

        assertCodeIsOk(expectedResult, "/expr/image_create.xml");
    }

    @Test
    public void visitGestureSensor_ScriptGetCurrentGestureAndDisplay_ReturnsCorrectJavaScriptProgram() throws Exception {
        String expectedResult = "" //
            + "var stmt0 = createDisplayTextAction(createGetSample(CONST.GESTURE, CONST.FACE_DOWN));\n"
            + "var stmt1 = createDisplayTextAction(createGetSample(CONST.GESTURE, CONST.LEFT));\n"
            + "var blocklyProgram = {'programStmts': [stmt0,stmt1]};";

        assertCodeIsOk(expectedResult, "/sensor/check_gesture.xml");
    }

    @Test
    public void visitTemperatureSensor_ScriptGetCurrentTemperatureAndDisplay_ReturnsCorrectJavaScriptProgram() throws Exception {
        String expectedResult = "" //
            + "var stmt0 = createDisplayTextAction(createGetSample(CONST.TEMPERATURE));\n"
            + "var blocklyProgram = {'programStmts': [stmt0]};";

        assertCodeIsOk(expectedResult, "/sensor/get_temperature.xml");
    }

    @Test
    public void visitLedOnAction_TurnOnLedInThreeDifferentColors_ReturnsCorrectCppProgram() throws Exception {
        String expectedResult = "" //
            + "var stmt0 = createLedOnAction(createConstant(CONST.LED_COLOR_CONST, [255, 0, 0]));\n"
            + "var stmt1 = createLedOnAction(createConstant(CONST.LED_COLOR_CONST, [0, 153, 0]));\n"
            + "var stmt2 = createLedOnAction(createConstant(CONST.LED_COLOR_CONST, [153, 153, 255]));\n"
            + "var blocklyProgram = {'programStmts': [stmt0,stmt1,stmt2]};";

        assertCodeIsOk(expectedResult, "/action/led_on_three_colors.xml");
    }

    @Test
    public void visitRgbColor_CreateColorAndDisplay_ReturnsCorrectCppProgram() throws Exception {
        String expectedResult = "" //
            + "var stmt0 = createDisplayTextAction(createRgbColor([createConstant(CONST.NUM_CONST, 20), createConstant(CONST.NUM_CONST, 25), createConstant(CONST.NUM_CONST, 30)]));\n"
            + "var blocklyProgram = {'programStmts': [stmt0]};";
        assertCodeIsOk(expectedResult, "/expr/create_color.xml");
    }

    private void assertCodeIsOk(String a, String fileName) throws Exception {
        Assert.assertEquals(a, Helper.generateJavaScript(fileName));
    }
}
