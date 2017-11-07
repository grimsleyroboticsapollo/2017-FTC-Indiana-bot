package org.firstinspires.ftc.teamcode;

public class JoystickHelper {

    // factor to convert radians into degrees
    private final static double RAD_TO_DEGREES = 180. / Math.PI;

    /**
     * Given a joystick position, calculates the angle where it is pointing at.
     *
     * @param joyX The joystick x-axis position. 0 = neutral, -1.0 is left, and +1.0 is right
     * @param joyY The joystick y-axis position. 0 = neutral, -1.0 is down, and +1.0 is up
     * @return Angle in degrees, between 0 (including) and 360 (excluding) degrees. 0 degrees is
     * considered "forward", 90 is "left", and 270 is "right".
     * <p>
     * Usage exmample:
     * <p>
     * double angle = JoystickHelper.getAngle(leftX, leftY);
     */
    public static double getAngle(double joyX, double joyY) {

        double joyXabs = Math.abs(joyX);
        boolean joyXpositive = (joyX > 0);
        double joyYabs = Math.abs(joyY);
        boolean joyYpositive = (joyY > 0);

        if (joyY == 0) {
            if (joyXpositive) {
                return 270.;
            } else {
                return 90.;
            }
        }

        if (joyX == 0) {
            if (joyYpositive) {
                return 0.;
            } else {
                return 180.;
            }
        }

        if (joyXpositive) {
            // 3rd or 4th quadrant

            if (joyYpositive) {
                // 4th quadrant

                return 270. + Math.atan(joyYabs / joyXabs) * RAD_TO_DEGREES;

            } else {

                return 180. + Math.atan(joyXabs / joyYabs) * RAD_TO_DEGREES;

            }


        } else {
            // 1st or 2nd quadrant

            if (joyYpositive) {
                // 1st quadrant

                return 0. + Math.atan(joyXabs / joyYabs) * RAD_TO_DEGREES;

            } else {

                return 90. + Math.atan(joyYabs / joyXabs) * RAD_TO_DEGREES;

            }

        }


    }

}
