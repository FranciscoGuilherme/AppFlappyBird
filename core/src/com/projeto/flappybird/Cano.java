package com.projeto.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Cano
{
    public float alturaEntreCanos;
    public int espacoEntreCanos;

    private float movimentacao;
    private Texture[] textures;
    private int[][] dimensoes;

    public static final int CANO_ALTO = 0;
    public static final int CANO_BAIXO = 1;
    public static final int ALTURA = 0;
    public static final int LARGURA = 1;
    public static final boolean MOVE_RESET = true;
    public static final boolean NO_MOVE_RESET = false;

    public Cano()
    {
        this.movimentacao = 0;
        this.alturaEntreCanos = 0;
        this.espacoEntreCanos = 0;

        this.textures = null;
        this.dimensoes = null;
    }

    public Cano(float movimentacao, float alturaEntreCanos, int espacoEntreCanos)
    {
        this.movimentacao = ( Number.isFloat( String.valueOf( movimentacao ) ) ) ? movimentacao : 0;
        this.alturaEntreCanos = ( Number.isFloat( String.valueOf( alturaEntreCanos ) ) ) ? alturaEntreCanos : 0;
        this.espacoEntreCanos = ( Number.isInt( String.valueOf( espacoEntreCanos ) ) ) ? espacoEntreCanos : 0;

        this.textures = null;
        this.dimensoes = null;
    }

    public float getMovimentacao()
    {
        return this.movimentacao;
    }

    public void setMovimentacao(int variacao, boolean reset)
    {
        if ( reset )
        {
            this.movimentacao = variacao;
        }
        else
        {
            this.movimentacao -= Gdx.graphics.getDeltaTime() * variacao;
        }
    }

    public Texture getTextures(int idCano)
    {
        return textures[idCano];
    }

    public void setTextures(Texture[] textures)
    {
        if ( textures != null )
        {
            this.textures = textures;
        }

    }

    public void setTextures(String name[], String[] extension)
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

        this.setDimensoes();
    }

    public int getAltura(int idCano, int dimensao)
    {
        return dimensoes[idCano][dimensao];
    }

    public int getLargura(int idCano, int dimensao)
    {
        return dimensoes[idCano][dimensao];
    }

    private void setDimensoes()
    {
        this.dimensoes = new int[this.textures.length][2];

        for (byte i = 0; i < this.textures.length; i++)
        {
            this.dimensoes[i][0] = this.textures[i].getHeight();
            this.dimensoes[i][1] = this.textures[i].getWidth();
        }
    }
}
