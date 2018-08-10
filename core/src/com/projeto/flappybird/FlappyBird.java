package com.projeto.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter
{

    private SpriteBatch batch;
    private Texture[] bird;
    private Texture background_day;

    private int altura;
    private int largura;

    private float variacao = 0;
    private float velocidadeQueda = 0;
    private float posicaoInicialVertical = 0;

	@Override
	public void create ()
    {
        batch = new SpriteBatch();
        bird = new Texture[3];

        bird[0] = new Texture( "bird_up.png" );
        bird[1] = new Texture( "bird_middle.png" );
        bird[2] = new Texture( "bird_down.png" );

        background_day = new Texture( "day.png" );

        altura = Gdx.graphics.getWidth();
        largura = Gdx.graphics.getHeight();
        posicaoInicialVertical = altura / 2;
	}

	@Override
	public void render ()
    {
        variacao += Gdx.graphics.getDeltaTime() * 15;
        velocidadeQueda++;

        if ( variacao > 3 ) variacao = 0;

        if ( Gdx.input.justTouched() )
        {
            velocidadeQueda = -15;
        }

        if ( posicaoInicialVertical > 0 || velocidadeQueda < 0 )
        {
            posicaoInicialVertical -= velocidadeQueda;
        }

        batch.begin();

        batch.draw( background_day, 0, 0, altura, largura );
        batch.draw( bird[ (int) variacao ], 30, posicaoInicialVertical );

        batch.end();
	}

}
