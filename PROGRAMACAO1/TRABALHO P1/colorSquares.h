#ifndef colorSquares_h
#define colorSquares_h
#include <stdio.h>


int marcar(int sz, int tabuleiro[][20], int x, int y);
int movimentos(int sz, int tabuleiro[][20], int x, int y);
int pontuacao(int num_quadrados);
void gravidade(int sz, int tabuleiro[][20]);
void coluna (int sz, int tabuleiro[][20]);
int jogada(int sz, int tabuleiro[][20], int x, int y);
void mostrar(int sz, int tabuleiro[][20]);


#endif
