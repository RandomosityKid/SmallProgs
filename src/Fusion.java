import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Fusion {
	public static HashMap<String, pokemon> dex = new HashMap<String, pokemon>();
	public static HashMap<String, pokemon> fused = new HashMap<String, pokemon>();
	
    public static void main(String[] args) throws IOException {
		String path = "C:\\PROGRAMS\\GAMES\\POKEMON\\Infinite Fusions\\Sheet\\PokeStats.csv";
		String finaltxt = "C:\\PROGRAMS\\GAMES\\POKEMON\\Infinite Fusions\\Sheet\\PokeResult.txt";
		String line = ""; //used to read files
		
		
		try {
			//Tool to read CSV //TryCatch for message instead of throws clause
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			//Read every line
			while ((line = br.readLine()) != null) {				
				String[] values = line.split(",");
				dex.put(values[0], new pokemon(values[0], values[1], values[2], Integer.valueOf(values[3]),
						Integer.valueOf(values[4]), Integer.valueOf(values[5]), Integer.valueOf(values[6]),
						Integer.valueOf(values[7]), Integer.valueOf(values[8]), Integer.valueOf(values[9])));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find file.");
		} catch (IOException e) {
			System.out.println("Cannot read file.");
		}
		
//		BufferedWriter bw = new BufferedWriter(new FileWriter(finaltxt));
//		for (int i = 0; i < 10; i++) {
//			bw.newLine();
//			bw.write(String.valueOf(i));
//		}
//		bw.close();

		BufferedWriter bw = new BufferedWriter(new FileWriter(finaltxt));

		for (pokemon poke : dex.values()) {
			for (pokemon poke2 : dex.values()) {
				bw.write(pW(poke, poke2));
				bw.newLine();
			}
		}
		
		bw.close();
		

    }

	private static String pW(pokemon poke, pokemon poke2) {
		return poke.getName()+","+poke2.getName()+","+poke.getType1()+","+poke2.getType2()+","+
				round(fuseCalc(poke.getHp(),poke2.getHp()))+","+
				round(fuseCalc(poke2.getAtk(),poke.getAtk()))+","+
				round(fuseCalc(poke2.getDef(),poke.getDef()))+","+
				round(fuseCalc(poke.getSpAtk(),poke2.getSpAtk()))+","+
				round(fuseCalc(poke.getSpDef(),poke2.getSpDef()))+","+
				round(fuseCalc(poke2.getSpd(),poke.getSpd()))+",";
	}

	private static Double fuseCalc(int mainStat, int sideStat) {
		return (2.0/3.0*mainStat) + (1.0/3.0*sideStat);
	}

	private static String round(double result) {
		int rounded = (int)Math.floor(result);
		return String.valueOf(rounded);
	}
}

class pokemon{
	private String name;
	private String type1;
	private String type2;
	private int Hp;
	private int Atk;
	private int Def;
	private int SpAtk;
	private int SpDef;
	private int Spd;
	private int BST;

	public pokemon(String name, String type1, String type2, int Hp, int Atk, int Def, int SpAtk, int SpDef, int Spd, int BST) {
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
		this.Hp = Hp;
		this.Atk = Atk;
		this.Def = Def;
		this.SpAtk = SpAtk;
		this.SpDef = SpDef;
		this.Spd = Spd;
		this.BST = BST;
	}

	public String getName() {
		return name;
	}

	public String getType1() {
		return type1;
	}

	public String getType2() {
		return type2;
	}

	public int getHp() {
		return Hp;
	}

	public int getAtk() {
		return Atk;
	}

	public int getDef() {
		return Def;
	}

	public int getSpAtk() {
		return SpAtk;
	}

	public int getSpDef() {
		return SpDef;
	}

	public int getSpd() {
		return Spd;
	}

	public int getBST() {
		return BST;
	}
}
