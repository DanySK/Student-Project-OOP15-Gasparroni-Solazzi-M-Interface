package Model;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * Classe utilizzata l'intercetto degli eventi
 * ed il controllo delle varie informazioni
 *
 * @author Silvia Gasparroni
 *
 */
class Thrmod implements Runnable
{
	private final OutputStream ostrm_;
	private final InputStream istrm_;
	private final int err;

	/**
	 * Il seguente metodo invocare il model passandogli il commento per effettuare il commit
	 *
	 * @param com
	 *          Stringa contenente il commento
	 * @return true se Mercurial è installato
	 * 		  false invece se è da Installare
	 */
	public Thrmod(InputStream istrm, OutputStream ostrm) {
		istrm_ = istrm;
		ostrm_ = ostrm;
		err=-1;
	}

	/**
	 * Il seguente metodo invocare il model passandogli il commento per effettuare il commit
	 *
	 * @param com
	 *          Stringa contenente il commento
	 * @return true se Mercurial è installato
	 * 		  false invece se è da Installare
	 */
	public Thrmod(InputStream istrm, OutputStream ostrm,int o) {
		istrm_ = istrm;
		ostrm_ = ostrm;
		err=o;

	}
	/**
	 * Il seguente metodo invocare il model passandogli il commento per effettuare il commit
	 *
	 * @param com
	 *          Stringa contenente il commento
	 * @return true se Mercurial è installato
	 * 		  false invece se è da Installare
	 */
	public void run() {
		try
		{
			byte[] buffer = new byte[1024];
			for (int length = 0; (length = istrm_.read(buffer)) != -1; )
			{
				ostrm_.write(buffer, 0, length);
				if(err>0){
					String bff= new String(buffer);
					//System.out.println("stampare:"+bff);
					buffer= new byte[1024];
					ReturnString.setString(bff);
				}else if (err==0){
					String bff= new String(buffer);
					buffer= new byte[1024];
					ReturnString.setLog(bff);
				}
			}



		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Il seguente metodo invocare il model passandogli il commento per effettuare il commit
	 *
	 * @param com
	 *          Stringa contenente il commento
	 * @return true se Mercurial è installato
	 * 		  false invece se è da Installare
	 */
	public static  class ReturnString {
		private static List<String> lista= new ArrayList<>();
		private static int index=0;
		private static List<String> log = new ArrayList<>();

		/**
		 * Il seguente metodo invocare il model passandogli il commento per effettuare il commit
		 *
		 * @param com
		 *          Stringa contenente il commento
		 * @return true se Mercurial è installato
		 * 		  false invece se è da Installare
		 */
		public static void setString(String str){
			System.out.println("sono entrato sono unerrore"+str);
			lista.add(str);
			index++;
		}

		/**
		 * Il seguente metodo invocare il model passandogli il commento per effettuare il commit
		 *
		 * @param com
		 *          Stringa contenente il commento
		 * @return true se Mercurial è installato
		 * 		  false invece se è da Installare
		 */
		public static void setLog(String str){
			System.out.println("sto aggiundo la riga " + str);
			log.add(str);

		}

		/**
		 * Il seguente metodo invocare il model passandogli il commento per effettuare il commit
		 *
		 * @param com
		 *          Stringa contenente il commento
		 * @return true se Mercurial è installato
		 * 		  false invece se è da Installare
		 */
		static boolean state(){
			return index>0?true:false;
		}

		/**
		 * Il seguente metodo invocare il model passandogli il commento per effettuare il commit
		 *
		 * @param com
		 *          Stringa contenente il commento
		 * @return true se Mercurial è installato
		 * 		  false invece se è da Installare
		 */
		public static List<String> getList(){
			return lista;
		}

		/**
		 * Il seguente metodo invocare il model passandogli il commento per effettuare il commit
		 *
		 * @param com
		 *          Stringa contenente il commento
		 * @return true se Mercurial è installato
		 * 		  false invece se è da Installare
		 */
		public static List<String> getLista(){
			return log;
		}

		/**
		 * Il seguente metodo invocare il model passandogli il commento per effettuare il commit
		 *
		 * @param com
		 *          Stringa contenente il commento
		 * @return true se Mercurial è installato
		 * 		  false invece se è da Installare
		 */
		public static String  getElements(){
			int size=log.size();
			System.out.println(log.get(size-2));
			return log.get(size-2);
		}

		/**
		 * Il seguente metodo invocare il model passandogli il commento per effettuare il commit
		 *
		 * @param com
		 *          Stringa contenente il commento
		 * @return true se Mercurial è installato
		 * 		  false invece se è da Installare
		 */
		public static void reset() {
			index=0;
			lista.clear();
			log.clear();	
		}
	}
}
 