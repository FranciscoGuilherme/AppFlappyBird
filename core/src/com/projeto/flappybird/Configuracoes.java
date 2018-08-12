package com.projeto.flappybird;

import com.badlogic.gdx.Gdx;

import java.util.Random;

public class Configuracoes
{
    private int altura;
    private int largura;
    private int status;
    private int pontuacao;
    private boolean marcou;
    private Random random;

    public Configuracoes()
    {
        this.altura = Gdx.graphics.getHeight();
        this.largura = Gdx.graphics.getWidth();

        this.status = 0;
        this.pontuacao = 0;
        this.marcou = false;
        this.random = new Random();
    }

    public int getAltura()
    {
        return this.altura;
    }

    public int getLargura()
    {
        return this.largura;
    }

    public int getStatus()
    {
        return this.status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public int getPontuacao()
    {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao)
    {
        this.pontuacao = pontuacao;
    }

    public boolean getMarcou()
    {
        return this.marcou;
    }

    public void setMarcou(boolean marcou)
    {
        this.marcou = marcou;
    }

    public int getRandom(int numero)
    {
        return this.random.nextInt( numero );
    }
}
