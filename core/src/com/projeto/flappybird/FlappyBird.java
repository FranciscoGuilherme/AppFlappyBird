package com.projeto.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter
{
    private Bird bird;
    private Cano cano;
    private Texture gameOver;
    private BitmapFont texto;
    private Configuracoes config;
    private Background background;
    private SpriteBatch batch;

    private ShapeRenderer shape;
    private Circle shapeBird;
    private Rectangle shapeCanoAlto;
    private Rectangle shapeCanoBaixo;

    private void initialize()
    {
        this.texto = new BitmapFont();
        this.batch = new SpriteBatch();
        this.shape = new ShapeRenderer();
        this.config = new Configuracoes();
        this.background = new Background();

        this.bird = new Bird(  0, 0, config.getAltura() / 2 );
        this.cano = new Cano( config.getLargura(), config.getRandom( 400 ) - 200, 300 );
    }

    private void setTextures()
    {
        String[] b_name = { "bird_up", "bird_middle", "bird_down" };
        String[] b_extension = { ".png", ".png", ".png" };

        String[] c_name = { "cano_alto", "cano_baixo" };
        String[] c_extension = { ".png", ".png" };

        this.bird.setTextures( b_name, b_extension );
        this.cano.setTextures( c_name, c_extension );
        this.background.setDay( "day", ".png" );
        this.gameOver = new Texture( "game_over.png" );
    }

	@Override
	public void create ()
    {
        this.initialize();
        this.setTextures();

        this.texto.setColor( Color.WHITE );
        this.texto.getData().setScale( 6 );
	}

	@Override
	public void render ()
    {
        float y_canoA = ( this.config.getAltura() / 2 ) +
                        ( this.cano.espacoEntreCanos / 2 ) +
                        ( this.cano.alturaEntreCanos );
        float y_canoB = ( this.config.getAltura() / 2 ) -
                        ( this.cano.getAltura( Cano.CANO_BAIXO, Cano.ALTURA ) + ( this.cano.espacoEntreCanos / 2 ) ) -
                        ( this.cano.alturaEntreCanos );

        this.regras();

        this.bird.setVariacaoAsas( 15 );

        if ( this.bird.getVariacaoAsas() > 3 ) this.bird.setVariacaoAsas( 0 );

        batch.begin();
        batch.draw( this.background.getDay(), 0, 0, config.getLargura(), config.getAltura() );
        batch.draw( this.cano.getTextures( Cano.CANO_ALTO ), this.cano.getMovimentacao(), y_canoA );
        batch.draw( this.cano.getTextures( Cano.CANO_BAIXO ), this.cano.getMovimentacao(), y_canoB);
        batch.draw( this.bird.getTextures( (int) this.bird.getVariacaoAsas() ), 200, this.bird.posicao );
        texto.draw( batch, String.valueOf( this.config.getPontuacao() ), this.config.getLargura() / 2, this.config.getAltura() - 50 );

        if ( this.config.getStatus() == 2 )
        {
            batch.draw
            (
                    gameOver,
                    this.config.getLargura() / 2 - gameOver.getWidth() / 2,
                    this.config.getAltura() / 2
            );
        }

        batch.end();

        this.shapes( false );

        if ( this.colision() )
        {
            this.config.setStatus( 2 );
        }
	}

	private void regras()
    {
        if ( this.config.getStatus() == 0 )
        {
            if ( Gdx.input.justTouched() )
            {
                this.config.setStatus( 1 );
            }
        }
        else
        {
            this.bird.variacaoQueda++;

            if ( this.bird.posicao > 0 || this.bird.variacaoQueda < 0 )
            {
                this.bird.posicao -= this.bird.variacaoQueda;
            }

            if ( this.config.getStatus() == 1 )
            {
                this.cano.setMovimentacao( 200, Cano.NO_MOVE_RESET );

                if ( Gdx.input.justTouched() )
                {
                    this.bird.variacaoQueda = -15;
                }

                if ( this.cano.getMovimentacao() < -( this.cano.getLargura( Cano.CANO_ALTO, Cano.LARGURA ) ) )
                {
                    this.cano.setMovimentacao( config.getLargura(), Cano.MOVE_RESET );
                    this.cano.alturaEntreCanos = config.getRandom( 400 ) - 200;
                    this.config.setMarcou( false );
                }

                if ( this.cano.getMovimentacao() < 0 )
                {
                    if ( !this.config.getMarcou() )
                    {
                        this.config.setPontuacao( this.config.getPontuacao() + 1 );
                        this.config.setMarcou( true );
                    }
                }
            }

        }
    }

    private void shapes(boolean color)
    {
        this.shapeBird = new Circle();
        this.shapeCanoAlto = new Rectangle();
        this.shapeCanoBaixo = new Rectangle();

        int x_bird = 200 + ( this.bird.getTextures( 0 ).getWidth() / 2 );
        int y_bird = this.bird.posicao + ( this.bird.getTextures( 0 ).getHeight() / 2 );
        int r_bird = this.bird.getTextures( 0 ).getWidth() / 2;

        float h_canoA = this.cano.getAltura( Cano.CANO_ALTO, Cano.ALTURA );
        float w_canoA = this.cano.getLargura( Cano.CANO_ALTO, Cano.LARGURA ) / 5;
        float x_canoA = this.cano.getMovimentacao() + 180;
        float y_canoA = ( this.config.getAltura() / 2 ) +
                        ( this.cano.espacoEntreCanos / 2 ) +
                        ( this.cano.alturaEntreCanos ) + 40;

        float h_canoB = this.cano.getAltura( Cano.CANO_BAIXO, Cano.ALTURA );
        float w_canoB = this.cano.getLargura( Cano.CANO_BAIXO, Cano.LARGURA ) / 5;
        float x_canoB = this.cano.getMovimentacao() + 180;
        float y_canoB = ( this.config.getAltura() / 2 ) -
                        ( this.cano.getAltura( Cano.CANO_BAIXO, Cano.ALTURA ) + ( this.cano.espacoEntreCanos / 2 ) ) -
                        ( this.cano.alturaEntreCanos ) - 40;

        shapeBird.set( x_bird, y_bird, r_bird );
        shapeCanoAlto.set( x_canoA, y_canoA, w_canoA, h_canoA );
        shapeCanoBaixo.set( x_canoB, y_canoB, w_canoB, h_canoB );

        if ( color )
        {
            shape.begin( ShapeRenderer.ShapeType.Filled );
            shape.circle( shapeBird.x, shapeBird.y, shapeBird.radius );
            shape.rect( shapeCanoAlto.x, shapeCanoAlto.y, shapeCanoAlto.width, shapeCanoAlto.height );
            shape.rect( shapeCanoBaixo.x, shapeCanoBaixo.y, shapeCanoBaixo.width, shapeCanoBaixo.height );
            shape.setColor( Color.RED );
            shape.end();
        }
    }

    private boolean colision()
    {
        boolean condition_a = Intersector.overlaps( shapeBird, shapeCanoAlto );
        boolean condition_b = Intersector.overlaps( shapeBird, shapeCanoBaixo );
        boolean condition_c = this.bird.posicao <= 0;
        boolean condition_d = this.bird.posicao >= this.config.getAltura();

        return condition_a || condition_b || condition_c || condition_d;
    }
}
