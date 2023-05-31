package br.edu.ifpe.jaboatao.ts.servicos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import br.edu.ifpe.jaboatao.ts.entidades.Cliente;

@DisplayName("Testando Assertivas")
public class AssertivasTest {
	
	@Test
	@DisplayName("Teste assertTrue/assertFalse")
	public void teste() {
		// Retornar um valor verdadeiro
		Assertions.assertTrue(true);
		// Retornar um valor falso
		Assertions.assertFalse(false);
	}
	
	@Test
	@DisplayName("Teste assertEquals")
	public void teste2() {
		// Retornar valores iguais       Mensagem de erro        
		Assertions.assertEquals(1, 1, "1 deveria ser igual a 1");
		Assertions.assertEquals(1.1, 1.1);
		// Retorna o valor de PI com apenas duas casas decimais (no formato 0.01)
		Assertions.assertEquals(Math.PI, 3.14, 0.01);
		Assertions.assertEquals("casa", "casa");
		// Retornar valores que não são iguais
		Assertions.assertNotEquals("casa", "Casa");
		// Retornar o valor igual, apesar de começar com a letra maiúscula
		Assertions.assertTrue("casa".equalsIgnoreCase("Casa"));
		
		Cliente cliente1 = new Cliente("Cliente 01");
		Cliente cliente2 = new Cliente("Cliente 01");
		
		Assertions.assertEquals(cliente1, cliente2);
	}
	
	@Test
	@DisplayName("Teste assertSame")
	public void teste3() {
		Cliente cliente1 = new Cliente("Cliente 01");
		Cliente cliente2 = cliente1;
		Cliente cliente3 = new Cliente("Cliente 01");
		// Valor que possui a mesma instancia
		Assertions.assertSame(cliente1, cliente2);
		Assertions.assertNotSame(cliente1, cliente3);
	}
	
	@Test
	@DisplayName("Teste assertNull/assertNotNull")
	public void teste4() {
		Cliente cliente1 = new Cliente("Cliente 01");
		Cliente cliente2 = null;
		// Retornar um valor nulo
		Assertions.assertNull(cliente2);
		// Retornar um valor que não é nulo
		Assertions.assertNotNull(cliente1);
	}
	
	@Test
	@DisplayName("Teste assertAll")
	public void teste5() {
		Assertions.assertAll(
				() -> Assertions.assertEquals(1, 1, "1 deveria ser igual a 1"),
				() -> Assertions.assertEquals("casa", "casa", "Era esperada a palavra 'casa' com C minúsculo"),
				() -> Assertions.assertTrue(true, "Era esperado True")
				);
	}
}
