package com.example.yyq.myandroidthings;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.GpioCallback;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;

public class ButtonActivity
        extends Activity
{
    private Gpio mButtonGpio;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        try
        {
            mButtonGpio = PeripheralManager.getInstance().openGpio(BoardDefault.getGPIOForButton());
            // 输出
            mButtonGpio.setDirection(Gpio.DIRECTION_IN);
            /*
            使用setEdgeTriggerType()方法声明触发中断事件的状态变化。边缘触发器支持下面的四个类型：
            EDGE_NONE：没有中断事件，这个是默认的值。
            EDGE_RISING：从低到高过渡中断。
            EDGE_FALLING：从高到底过渡中断。
            EDGE_BOTH：在所有状态转换中断。
             */
            mButtonGpio.setEdgeTriggerType(Gpio.EDGE_FALLING);
            // 注册按钮按下监听
            mButtonGpio.registerGpioCallback(new GpioCallback()
            {
                @Override
                public boolean onGpioEdge(Gpio gpio)
                {
                    // 不一定马上响应，有些奇怪
                    Log.i("ButtonActivity", "onGpioEdge: press");
                    // true，继续监听
                    return true;
                }
            });
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
        if (mButtonGpio != null)
        {
            try
            {
                mButtonGpio.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                mButtonGpio = null;
            }
        }
    }
}
