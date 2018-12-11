package br.com.secretariavirtual.test;

import org.hibernate.Session;
import org.junit.Test;

import br.com.secretariavirtual.util.HibernateUtil;

public class HibernateUtilTest {
	@Test
	public void conectar(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}