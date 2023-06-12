package br.edu.ifpe.jaboatao.ts.servicos;

import java.util.List;

import br.edu.ifpe.jaboatao.ts.exceptions.LocacaoException;

public class Calculadora {

	/*public int somar(int x, int y) {
		return x + y;
	}*/
	
	public int somar(List<Integer> parNumeros) {
		int varTotal = 0;
		for (Integer numero : parNumeros) {
			varTotal = varTotal + numero;
		}
		return varTotal;
	}

	/*public int subtrair(int x, int y) {
		return x - y;
	}*/
	
	public int subtrair(List<Integer> parNumeros) {
		int varTotal = 0;
		int varNumero = 0;
		
		for (Integer i = 0; i < parNumeros.size(); i++) {
			// Se for o primeiro número, apenas armazena-lo
			if(i == 0) {
				varTotal = varNumero;
			} else { // Se não for o primeiro número, calcular a divisão e armazenar no acumulador
				varTotal = varTotal - varNumero;
			}
		}
		return varTotal;
	}
	
	public double dividir(List<Integer> parNumeros) throws LocacaoException {
		int varTotal = 1;
		int varNumero = 0;
		
		// Verificar se o segundo número em diante é zero. Se for, levantar a exceção
		for(Integer i = 0; i < parNumeros.size(); i++) {
			varNumero = parNumeros.get(i);
			if(i != 0) { // Se não for o primeiro número, verifique...
				if(varNumero == 0) {
					throw new LocacaoException("Não pode ser dividido por zero");
				}
			}
		}
		varNumero = 0;  // Zerando o valor da variavél para reutiliza-la
		
		// Percorrer toda a lista de números e fazer a divisão
		for(Integer i = 0; i < parNumeros.size(); i++) {
			varNumero = parNumeros.get(i);
			
			// Se for o primeiro número, apenas armazena-lo
			if(i == 0) {
				varTotal = varNumero;
			} else {  // Se não for o primeiro número, calcular a divisão a armazenar no acumulador
				varTotal = varTotal / varNumero;
			}
		}
		return varTotal;
	}
	
/*
	public double dividir(double x, double y) throws LocacaoException {
		if (y == 0) {
			throw new LocacaoException("Não pode ser dividido por zero");
		}
		return x / y;
	}*/

	
}
