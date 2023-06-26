package br.edu.ifpe.jaboatao.ts.utils;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.util.Calendar;
import java.util.Date;

public class ManipularDatas {
	


	/**
	 * Retorna uma nova data de acordo com a quantidade de dias passada por parametro, a partir de uma data específica.
	 * @param data a ser incrementado/decrementado
	 * @param dias Quantidade de dias que irá incrementado/decrementado a data
	 * @return Data atualizada
	 */
	public static Date novaDataComDiferencaDeDias(int dias, Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(DAY_OF_MONTH, dias);
		return calendar.getTime();
	}

	
	/**
	 * Retorna uma nova data de acordo com a quantidade de dias passada por parametro.
	 * @param dias Quantidade de dias a ser incrementado/decrementado
	 */
	public static Date novaDataComDiferencaDeDias(int dias) {
		return novaDataComDiferencaDeDias(dias, new Date());
	}	

	/**
	 * Verifica se duas datas são iguais.. Leva em consideração apenas dia, mes e ano
	 * @param primeiraData
	 * @param segundaData
	 */
	public static boolean boDatasIguais(Date primeiraData, Date segundaData) {
		//Primeira data
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(primeiraData);
		//Segunda data
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(segundaData);
		return (cal1.get(DAY_OF_MONTH) == cal2.get(DAY_OF_MONTH))
				&& (cal1.get(MONTH) == cal2.get(MONTH))
				&& (cal1.get(YEAR) == cal2.get(YEAR));
	}
}
