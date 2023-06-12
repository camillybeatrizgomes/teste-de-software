package br.edu.ifpe.jaboatao.ts.servicos;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ifpe.jaboatao.ts.exceptions.LocacaoException;

public class CalculadoraTest {
	
	private Calculadora calc;
	
	@BeforeEach
	public void beforeEach() {
		calc = new Calculadora();
	}
	/*
	@Test
	public void somarDoisNumeros() {
		// Cenário
		int x = 4;
		int y = 3;
		
		// Ação
		int resultado = calc.somar(x, y);
		
		// Verificação
		Assertions.assertEquals(7, resultado);
	}*/
	
	@Test
	public void somarNumeros() {
		// Cenário
		List<Integer> soma = new ArrayList();
		soma.add(5);
		soma.add(4);
		soma.add(3);
		soma.add(10);
		
		// Ação
		int resultado = calc.somar(soma);
		
		// Verificação
		Assertions.assertEquals(22, resultado);
	}
	/*
	@Test
	public void subtrairDoisNumeros() {
		// Cenário
		int x = 4;
		int y = 3;
		
		// Ação
		int resultado = calc.subtrair(x, y);
		
		// Verificação
		Assertions.assertEquals(1, resultado);
	}*/
	
	@Test
	public void subtrairNumeros() {
		// Cenário
		List<Integer> subtrair = new ArrayList();
		subtrair.add(10);
		subtrair.add(5);
		subtrair.add(1);
		subtrair.add(1);
		
		// Ação
		int resultado = calc.subtrair(subtrair);
		
		// Verificação
		Assertions.assertEquals(0, resultado);
	}
	
	@Test
	public void dividirNumeros() {
		// Cenário
		List<Integer> dividir = new ArrayList();
		dividir.add(12);
		dividir.add(2);
		dividir.add(3);
		
		double resultado = 0;
		
		// Ação 
		try {
			resultado = calc.dividir(dividir);
		} catch (LocacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Verificação
		Assertions.assertEquals(2, resultado);
	}
	
	/*
	@Test 
	public void dividirDoisNumeros() {
		// Cenário 
		int x = 6;
		int y = 4;
		
		// Ação 
		double resultado = 0;
		try {
			resultado = calc.dividir(x, y);
		} catch (LocacaoException e) {
			// TODO Auto-generated catch block
		}
		// Verificação
		Assertions.assertEquals(1.5, resultado);
	}*/
	
	@Test
	public void DividirPorZero() {
		// Cenário
		List<Integer> dividir = new ArrayList();
		dividir.add(6);
		dividir.add(0);
		dividir.add(4);
		
		// Ação
		try {
			double resultado = calc.dividir(dividir);
			fail("Era para levantar uma exceção mas não veio");
		} catch (LocacaoException e) {
			// Verificação
			Assertions.assertEquals("Não pode ser dividido por zero", e.getMessage());
		}
	}
	
	/*
	@Test
	public void dividirPorZero() {
		// Cenário
		int x = 6;
		int y = 0;
		
		// Ação
		try {
			double resultado = calc.dividir(x, y);
			fail("Era para levantar uma exceção mas não veio");
		} catch (LocacaoException e) {
			// Verificação
			Assertions.assertEquals("Não pode ser dividido por zero", e.getMessage());
		}
	}*/
}
