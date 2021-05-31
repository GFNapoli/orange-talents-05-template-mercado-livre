package br.com.zup.mercado.fake;

public class EmailFake {

	public static void send(String login, String name,String title) {
		System.out.println("para: " + login);
		System.out.println("nosava pergunta sobre "+name);
		System.out.println(title);
	}

}
