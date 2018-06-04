package com.example.yyq.myandroidthings;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.things.pio.PeripheralManager;
import com.google.android.things.pio.Pwm;

import java.io.IOException;

/**
 * Pluse Width Modulaion(PWM)
 */
public class PWMActivity
        extends Activity
{
    private Pwm mPwm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwm);

        try
        {
            mPwm = PeripheralManager.getInstance().openPwm(BoardDefault.getPWMPort());

            // 设置频率
            mPwm.setPwmFrequencyHz(50);
            // 占空比
            mPwm.setPwmDutyCycle(2);
            // 启动 PWM 信号
            mPwm.setEnabled(true);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void on4(View view) throws IOException
    {
        mPwm.setPwmDutyCycle(4);
    }

    public void on8(View view) throws IOException
    {
        mPwm.setPwmDutyCycle(8);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        try
        {
            mPwm.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            mPwm = null;
        }
    }
}
