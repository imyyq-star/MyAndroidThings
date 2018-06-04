package com.example.yyq.myandroidthings;

public class BoardDefault
{
    /**
     * @return LED连接的 GPIO 的引脚，一般负极连接 Ground 脚
     */
    public static String getGPIOForLED()
    {
        return "BCM6";
    }

    /**
     * @return 按钮连接的 GPIO 引脚
     */
    public static String getGPIOForButton()
    {
        return "BCM21";
    }

    public static String getPWMPort()
    {
        return "PWM0";
    }
}
