package View;

import Control.Control;

/**
 * Classe utilizzata per effettuare il login
 * ed impostare il proprio nome utente
 *
 * @author Filippo Solazzi
 *
 */
public interface Login_View {
	
	//Metodo per impostare il control
	/**
	* Metodo utilizzato per impostare il control
	* che verr� richiamato quando succede un evento
	*
	* @param ctr
	*          Control the gestisce gli eventi
	*/
	void set_Control(Control ctr);
}
