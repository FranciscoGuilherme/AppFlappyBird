package com.projeto.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Bird
{
    public int posicao;
    public float variacaoQueda;

    private float variacaoAsas;
    private Texture[] textures;

    public Bird()
    {
        this.variacaoAsas = 0;
        this.variacaoQueda = 0;
        this.posicao = 0;

        this.textures = null;
    }

    public Bird(float variacaoAsas, float variacaoQueda, int posicao)
    {
        this.variacaoAsas = ( Number.isFloat( String.valueOf( variacaoAsas ) ) ) ? variacaoAsas : 0;
        this.variacaoQueda = ( Number.isFloat( String.valueOf( variacaoQueda ) ) ) ? variacaoQueda : 0;
        this.posicao = ( Number.isInt( String.valueOf( posicao ) ) ) ? posicao : 0;

        this.textures = null;
    }

    public float getVariacaoAsas()
    {
        return variacaoAsas;
    }

    public void setVariacaoAsas(int variacao)
    {
        if ( variacao == 0 )
        {
            this.variacaoAsas = 0;
        }

        this.variacaoAsas += Gdx.graphics.getDeltaTime() * variacao;
    }

    public Texture getTextures(int idBird)
    {
        return this.textures[idBird];
    }

    public void setTextures(Texture[] textures)
    {
        byte i = 0;

        if ( textures != null )
        {
            for (; i < textures.length; i++)
            {
                if ( textures[i] == null )
                {
                    break;
                }
            }

            if ( i == textures.length )
            {
                this.textures = textures;
            }
        }
    }

    public void setTextures(String[] name, String[] extension)
    {
        Byte i = 0;

        this.textures = new Texture[name.length];

        for (; i < name.length; i++)
        {
            if ( name[i] == null || extension[i] == null )
            {
                break;
            }
            else
            {
                if ( name[i].trim().isEmpty() || extension[i].trim().isEmpty() )
                {
                    break;
                }

                this.textures[i] = new Texture( name[i] + extension[i] );
            }
        }
    }
}
