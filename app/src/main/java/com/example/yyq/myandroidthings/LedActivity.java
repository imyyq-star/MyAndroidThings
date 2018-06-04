package com.example.yyq.myandroidthings;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;

/**
 */
public class LedActivity
        extends Activity
{
    protected CheckBox cbLed;
    private Gpio mLedGpio;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();

        cbLed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                try
                {
                    mLedGpio.setValue(isChecked);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });

        try
        {
            // 获取 gpio 的引脚
            mLedGpio = PeripheralManager.getInstance().openGpio(BoardDefault.getGPIOForLED());
            // 设置该引脚为输出，似乎设置了这个就会导致原本亮的灯变灭
            mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            // 读取引脚的状态
            cbLed.setChecked(mLedGpio.getValue());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        // 关闭
        if (mLedGpio != null)
        {
            try
            {
                mLedGpio.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                mLedGpio = null;
            }
        }
    }

    private void initView()
    {
        cbLed = (CheckBox) findViewById(R.id.cb_led);
    }
}
