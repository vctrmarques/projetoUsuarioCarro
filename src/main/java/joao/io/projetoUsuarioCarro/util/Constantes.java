package joao.io.projetoUsuarioCarro.util;

public class Constantes {
	
	public interface Status{
		public static final int CODIGO_SUCESSO = 0;
		public static final int CODIGO_ERRO = -1;
	}
	
	public interface Url{
		public static final String URL_USERS = "/api/users";
		public static final String URL_SIGNIN = "/api/signin";
	}
}
