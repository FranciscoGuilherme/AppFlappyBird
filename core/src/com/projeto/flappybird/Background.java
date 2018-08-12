package com.projeto.flappybird;

import com.badlogic.gdx.graphics.Texture;

public class Background
{
    private Texture day;
    private Texture nigth;

    public Background()
    {
        this.day = null;
        this.nigth = null;
    }

    public Texture getDay()
    {
        return this.day;
    }

    public void setDay(String name, String extension)
    {
        name = name.trim();
        extension = extension.trim();

        if ( !name.isEmpty() && !extension.isEmpty() )
        {
            this.day = new Texture( name + extension );
        }
    }

    public Texture getNigth()
    {
        return this.nigth;
    }

    public void setNigth(String name, String extension)
    {
        name = name.trim();
        extension = extension.trim();

        if ( !name.isEmpty() && !extension.isEmpty() )
        {
            this.nigth = new Texture( name + extension);
        }
    }
}
