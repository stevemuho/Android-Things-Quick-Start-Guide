package com.plattysoft.gpiosamples

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import com.plattysoft.l298n.L298N
import com.plattysoft.l298n.MotorMode


/**
 * Created by Raul Portales on 18/05/18.
 */
class RobotCarActivity : Activity() {

    private lateinit var motorController: L298N

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // PINS for the motor controller L298N
        // BCM22, BCM23, BCM24, BCM25
        //            mMotorController = L298N.open("BCM22", "BCM23", "BCM24", "BCM25")
        motorController = L298N.open("GPIO2_IO00", "GPIO2_IO05",
                "GPIO2_IO07", "GPIO6_IO15")
        motorController.setMode(MotorMode.STOP)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            motorController.setMode(MotorMode.FORWARD)
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            motorController.setMode(MotorMode.BACKWARD)
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            motorController.setMode(MotorMode.TURN_LEFT)
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            motorController.setMode(MotorMode.TURN_RIGHT)
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        motorController.setMode(MotorMode.STOP)
        return super.onKeyUp(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        motorController.close()
    }
}