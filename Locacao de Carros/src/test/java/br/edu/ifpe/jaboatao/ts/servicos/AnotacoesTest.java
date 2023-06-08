package br.edu.ifpe.jaboatao.ts.servicos;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// Ordena métodos de forma aleatória 
//@TestMethodOrder(MethodOrderer.Random.class)
// Ordena métodos na ordem padrão
//@TestMethodOrder(MethodOrderer.MethodName.class)
// Ordena de acordo com o DisplayName, nesse exemplo, será em ordem numérica 
//@TestMethodOrder(MethodOrderer.DisplayName.class)
// Ordena de forma nemérica, usando o @Order
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class AnotacoesTest {
	
	private static int varInt = 0;
	
	@BeforeEach // indica que o método será executado primeiro
	public void metodoBeforeEach() {
		varInt++;
		System.out.println(varInt);
		System.out.println("Before Each");
	}
	
	@AfterEach // indica que o método será executado último
	public void metodoAfterEach() {
		System.out.println("After Each");
	}
	
	@BeforeAll // indica que o método será o primeiro de todos
	public static void metodoBeforeAll() {
		System.out.println("Before All");
	}
	
	/* Os métodos ue vão receber as anotações @BeforeAll e @AfterAll precisam ser
	 * declarados como STATIC */
	
	@AfterAll // indica que o método será o último de todos 
	public static void metodoAfterAll() {
		System.out.println("After All");
	}
	
	@Test
	@DisplayName("2")
	@Order(3)
	public void metodoA() {
		System.out.println("Método A");	
	}
	
	@Test
	@DisplayName("1")
	@Order(1)
	public void metodoB() {
		System.out.println("Método B");
	}
	
	@Test
	@DisplayName("3")
	@Order(2)
	//@Disabled  O método não será executado
	public void metodoC() {
		System.out.println("Método C");
	}
}
