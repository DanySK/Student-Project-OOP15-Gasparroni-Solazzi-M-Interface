/**
 * Classe utilizzata per verificare l'esistenza di mercurial e gestire i vari comandi
 *
 * @author  Silvia Gasparroni
 *
 */
package Model;

import java.io.PrintStream;
import java.util.List;

import javax.swing.JOptionPane;


public class ModelImpl implements Model {
	
	private static String cmd;
	private static Process p;

	private String dir;
	private String repo;
	static boolean va=true; 
	private static boolean bool;
	
	
	private static String cmdArray(){
		String cmd;
		if (!System.getProperty("os.name").contains("Windows")){
			cmd="/usr/bin/ start";
			JOptionPane.showMessageDialog(null, "utilizzare su windows" , "Attenzione", JOptionPane.WARNING_MESSAGE);
		}else{
			cmd="cmd";
		}
		return cmd;
	}
	
	@Override
	public void setCmd(){
		String str=cmdArray();
		cmd=str;
		CommandModelProject.setCommand(str);
		ChoiseRepository.setCommand(str);
		CommandLog.setCommand(str);
	}
	
	@Override
	 public void doCopy(String elem) {
		 if (!System.getProperty("os.name").contains("Windows")){
				CommandModelProject.print("cp",repo);
			}else{
				CommandModelProject.print("copy "+elem +" "+repo," ");
			}
	}
	
	@Override 
	public void setRepository(String repo){
		this.repo = repo;
		ChoiseRepository.setDirectory(repo);
    	ChoiseRepository.CreateRepository();
	}
	
	@Override 
	public boolean check_Existence() {
    setCmd();
		try {
			p = Runtime.getRuntime().exec(cmd);
			int c=1;
			new Thread(new Thrmod(p.getErrorStream(), System.err,c)).start();
			new Thread(new Thrmod(p.getInputStream(), System.out)).start();
			PrintStream stdin = new PrintStream(p.getOutputStream());
			stdin.println("hg");
		    stdin.flush();
		    stdin.close();
		    p.waitFor();
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally{
		   //String str=Thrmod.ReturnString.getList().toString();
			 if(!Thrmod.ReturnString.state()){
				 bool = true;
			   //System.out.println("errore: "+str);
			   Thrmod.ReturnString.reset();
			 }	else{
				 bool=false;
			 }
		}
		return bool;
	}
	
	@Override 
	public void doCommit(String commento)  {
		CommandModelProject.print("hg commit -m" + " " + commento , this.repo);
	}
	
	@Override 
	public List<String> doLog()  {
		return CommandLog.print("hg log", this.repo);
	}
	
	@Override 
	public void doAdd(String dir)  {
		CommandModelProject.print("hg add "+ dir, this.repo);
		
	}
	
	@Override 
	public void clone_Repository(String url) {
		CommandModelProject.print("hg clone "+url, this.dir);
		
	}
	
	@Override 
	public void doUser(String username){
		JOptionPane.showMessageDialog(null, "inserire username="+username+"<nome@email.it>", "login", JOptionPane.INFORMATION_MESSAGE);
		CommandModelProject.print("hg config --edit","");
	}
	
}