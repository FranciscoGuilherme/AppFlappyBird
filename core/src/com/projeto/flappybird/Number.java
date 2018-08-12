package com.projeto.flappybird;

public class Number
{
    public static boolean isFloat(String numero)
    {
        try
        {
            Float.parseFloat( numero );

            return true;
        }
        catch( NumberFormatException ex )
        {
            return false;
        }
    }

    public static boolean isByte(String numero)
    {
        try
        {
            Byte.parseByte( numero );

            return true;
        }
        catch ( NumberFormatException ex )
        {
            return false;
        }
    }

    public static boolean isInt(String numero)
    {
        try
        {
            Integer.parseInt( numero );

            return true;
        }
        catch ( NumberFormatException ex )
        {
            return false;
        }
    }
}
