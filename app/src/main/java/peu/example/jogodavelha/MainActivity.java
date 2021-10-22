package peu.example.jogodavelha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
        // atributos relativos aos objetos gráficos da interface
        private ImageView i0;
        private ImageView i1;
        private ImageView i2;
        private ImageView i3;
        private ImageView i4;
        private ImageView i5;
        private ImageView i6;
        private ImageView i7;
        private ImageView i8;
        private TextView lblJog;
        private TextView lblApp;
        private Button btnReinicia;

        int[] tabuleiro = { 0,0,0,0,0,0,0,0,0 };

    // pontuação
    int pontosJog = 0;
    int pontosApp = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            // ligando atributos com os objetos, pelo ID
            i0 = findViewById( R.id.i0 );
            i1 = findViewById( R.id.i1 );
            i2 = findViewById( R.id.i2 );
            i3 = findViewById( R.id.i3 );
            i4 = findViewById( R.id.i4 );
            i5 = findViewById( R.id.i5 );
            i6 = findViewById( R.id.i6 );
            i7 = findViewById( R.id.i7 );
            i8 = findViewById( R.id.i8 );
            lblJog = findViewById( R.id.lblJog );
            lblApp = findViewById( R.id.lblApp );
            btnReinicia = findViewById( R.id.btnReinicia );

            EscutadorCliqueImagem escutador = new EscutadorCliqueImagem();

            btnReinicia.setOnClickListener ( new EscutadorBotao() );
            i0.setOnClickListener( escutador );
            i1.setOnClickListener( escutador );
            i2.setOnClickListener( escutador );
            i3.setOnClickListener( escutador );
            i4.setOnClickListener( escutador );
            i5.setOnClickListener( escutador );
            i6.setOnClickListener( escutador );
            i7.setOnClickListener( escutador );
            i8.setOnClickListener( escutador );
        }

    private class EscutadorBotao implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // colocando branco em todas as caixas
            i0.setImageResource( R.drawable.branco );
            i1.setImageResource( R.drawable.branco );
            i2.setImageResource( R.drawable.branco );
            i3.setImageResource( R.drawable.branco );
            i4.setImageResource( R.drawable.branco );
            i5.setImageResource( R.drawable.branco );
            i6.setImageResource( R.drawable.branco );
            i7.setImageResource( R.drawable.branco );
            i8.setImageResource( R.drawable.branco );

            // preenchendo o vetor tabuleiro com zeros
            Arrays.fill( tabuleiro, 0);

            // criando um novo escutador de cliques para as caixas
            EscutadorCliqueImagem escutador = new EscutadorCliqueImagem();

            // colocando o escutador criado acima nas caixas
            i0.setOnClickListener( escutador );
            i1.setOnClickListener( escutador );
            i2.setOnClickListener( escutador );
            i3.setOnClickListener( escutador );
            i4.setOnClickListener( escutador );
            i5.setOnClickListener( escutador );
            i6.setOnClickListener( escutador );
            i7.setOnClickListener( escutador );
            i8.setOnClickListener( escutador );
        }
    }

        // classe interna, para o objeto escutador de cliques nas imagens
        private class EscutadorCliqueImagem implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                // O ImageView que o usuário clicou está na variável view recebida acima.
                // Precisamos converter para ImageView.
                ImageView img = (ImageView) view;
                // Variável para armazenar o número da caixa clicada.
                int caixaClicada = 0;
                // Podemos verificar qual o ID da caixa que foi clicada.
                // Fazemos isso com o método getId().
                // Este método retorna o ID que definimos lá na interface.
                switch (img.getId()) {
                    case R.id.i0:
                        caixaClicada = 0;
                        break;
                    case R.id.i1:
                        caixaClicada = 1;
                        break;
                    case R.id.i2:
                        caixaClicada = 2;
                        break;
                    case R.id.i3:
                        caixaClicada = 3;
                        break;
                    case R.id.i4:
                        caixaClicada = 4;
                        break;
                    case R.id.i5:
                        caixaClicada = 5;
                        break;
                    case R.id.i6:
                        caixaClicada = 6;
                        break;
                    case R.id.i7:
                        caixaClicada = 7;
                        break;
                    case R.id.i8:
                        caixaClicada = 8;
                        break;

                }
                if (tabuleiro[caixaClicada] != 0) {
                    // caixa já ocupada, não pode jogar aqui
                    Toast.makeText(MainActivity.this, "Não pode jogar aí!", Toast.LENGTH_SHORT).show();
                }
                else {
                    // caixa disponível!
                    // vamos colocar um 'xis' neste ImageView
                    img.setImageResource(R.drawable.xis);
                    // vamos indicar esta jogada no vetor tabuleiro
                    tabuleiro[caixaClicada] = 1;
                    if ( testaVitoria() == 1 ){
                        Toast.makeText(MainActivity.this, "Jogador ganhou!", Toast.LENGTH_SHORT).show();
                        pontosJog++;
                        lblJog.setText( Integer.toString( pontosJog ));
                        finalizaJogo();
                    }
                    else {

                        // vamos testar de não foi a última jogada possível,
                        // e deu "velha"
                        // variável para indicar se deu velha
                        boolean velha = true;
                        // vamos varrer o vetor tabuleiro, procurando um valor 0
                        // Se acharmos, colocamos então velha como false.
                        // Podemos achar até mais de um, não tem problema.
                        // O importante é achar pelo menos um.
                        for ( int a : tabuleiro ) {
                            if ( a == 0)
                                velha = false;
                        }
                        // se velha == true, indicamos com toast e saimos
                        if ( velha ) {
                            Toast.makeText(MainActivity.this, "Deu velha!", Toast.LENGTH_SHORT).show();
                            finalizaJogo();
                            return;
                        }




                        // jogador não ganhou
                        // Variável para guardar a jogada do App
                        int jogadaApp = 0;
                        // Gerando número aleatório entre 0 e 8
                        jogadaApp = new Random().nextInt(9);
                        // Enquanto não for uma jogada válida,
                        // ir gerando valores.
                        while ( tabuleiro[jogadaApp] != 0 ) {
                            jogadaApp = new Random().nextInt(9);
                        }
                        // colocar um "circulo" na caixa escolhida pelo app:
                        switch ( jogadaApp ) {
                            case 0:
                                i0.setImageResource( R.drawable.circulo );
                                break;
                            case 1:
                                i1.setImageResource( R.drawable.circulo );
                                break;
                            case 2:
                                i2.setImageResource( R.drawable.circulo );
                                break;
                            case 3:
                                i3.setImageResource( R.drawable.circulo );
                                break;
                            case 4:
                                i4.setImageResource( R.drawable.circulo );
                                break;
                            case 5:
                                i5.setImageResource( R.drawable.circulo );
                                break;
                            case 6:
                                i6.setImageResource( R.drawable.circulo );
                                break;
                            case 7:
                                i7.setImageResource( R.drawable.circulo );
                                break;
                            case 8:
                                i8.setImageResource( R.drawable.circulo );
                                break;
                        }
                        // indicar esta jogada no tabuleiro
                        tabuleiro[jogadaApp] = 2;
                        // Vamos testar se, com esta jogada, o app ganhou!
                        if ( testaVitoria() == 2 ) {
                            // app ganhou!!
                            Toast.makeText(MainActivity.this, "App ganhou!", Toast.LENGTH_SHORT).show();
                            pontosApp++;
                            lblApp.setText( Integer.toString( pontosApp ));
                            finalizaJogo();
                        }
                    }
                }

            }
        }

    private int testaVitoria() {

        // vamos testar a 1a linha do tabuleiro (casas 0, 1, 2).
        if ( tabuleiro[0] == tabuleiro[1] && tabuleiro[1] == tabuleiro[2] ) {
            // todos os valores nesta linha são iguais!
            // Porém precisamos ver se não é o valor 0,
            // que significa que a linha está vazia.
            // Como já vimos que os valores nesta linha são iguais,
            // precisamos apenas testar um deles.
            if ( tabuleiro[0] != 0 ) {
                // se for 1, o jogador ganhou.
                // se for 2, o app ganhou.
                // Então basta apenas retornarmos este valor!
                return tabuleiro[0];
            }
        }
        // Precisamos fazer a mesma coisa para:
        // - as outras 2 linhas
        // - as 3 colunas
        // - as 2 diagonais
        // É só repetir o código, trocando os índices de acordo...
        // vamos testar a 2a linha do tabuleiro (casas 3, 4, 5).
        if ( tabuleiro[3] == tabuleiro[4] && tabuleiro[4] == tabuleiro[5] ) {
            if ( tabuleiro[3] != 0 ) {
                return tabuleiro[3];
            }
        }
        // vamos testar a 3a linha do tabuleiro (casas 3, 4, 5).
        if ( tabuleiro[6] == tabuleiro[7] && tabuleiro[7] == tabuleiro[8] ) {
            if ( tabuleiro[6] != 0 ) {
                return tabuleiro[6];
            }
        }
        // vamos testar a 1a coluna do tabuleiro (casas 0, 3, 6).
        if ( tabuleiro[0] == tabuleiro[3] && tabuleiro[3] == tabuleiro[6] ) {
            if ( tabuleiro[0] != 0 ) {
                return tabuleiro[0];
            }
        }
        // vamos testar a 2a coluna do tabuleiro (casas 1, 4, 7).
        if ( tabuleiro[1] == tabuleiro[4] && tabuleiro[4] == tabuleiro[7] ) {
            if ( tabuleiro[1] != 0 ) {
                return tabuleiro[1];
            }
        }
        // vamos testar a 3a coluna do tabuleiro (casas 2, 5, 8).
        if ( tabuleiro[2] == tabuleiro[5] && tabuleiro[5] == tabuleiro[8] ) {
            if ( tabuleiro[2] != 0 ) {
                return tabuleiro[2];
            }
        }
        // vamos testar a 1a diagonal do tabuleiro (casas 0, 4, 8).
        if ( tabuleiro[0] == tabuleiro[4] && tabuleiro[4] == tabuleiro[8] ) {
            if ( tabuleiro[0] != 0 ) {
                return tabuleiro[0];
            }
        }
        // vamos testar a 2a diagonal do tabuleiro (casas 2, 4, 6).
        if ( tabuleiro[2] == tabuleiro[4] && tabuleiro[4] == tabuleiro[6] ) {
            if ( tabuleiro[2] != 0 ) {
                return tabuleiro[2];
            }
        }
        // se chegou até aqui, é porque ninguém ganhou!
        // Então retornamos o valor 0.
        return 0;
    }

    private void finalizaJogo() {
        i0.setOnClickListener( null );
        i1.setOnClickListener( null );
        i2.setOnClickListener( null );
        i3.setOnClickListener( null );
        i4.setOnClickListener( null );
        i5.setOnClickListener( null );
        i6.setOnClickListener( null );
        i7.setOnClickListener( null );
        i8.setOnClickListener( null );
    }

}
