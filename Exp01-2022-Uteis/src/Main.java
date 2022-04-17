import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Main
{
	public static void processarArquivoSpharms()
	{
		try 
		{
			int contador = 1;
			Scanner scan = new Scanner(new FileReader("OctantParedeSPHARM.txt"));
			FileWriter outputFile = new FileWriter("saida.txt");
			PrintWriter output = new PrintWriter(outputFile);
			while (scan.hasNextLine())
			{
				String linha = scan.nextLine();
				String[] partesLinha = linha.split(" ");
				if (partesLinha.length > 2)
				{
					if (partesLinha[0].equals("VALOR") && partesLinha[2].equals("L:10"))
					{
						output.print(contador + ",");
						linha = scan.nextLine();
						linha = linha.replaceAll(" ", "");
						output.printf("%s\n", linha);
						contador++;
					}
				}
			}
			outputFile.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void ProcessarArquivoHough()
	{
		try
		{
			int contador = 1;
			int caso = 1;
			Scanner scan = new Scanner(new FileReader("Hough90Total.txt"));
			FileWriter outputFile = new FileWriter("saida_hough.txt");
			PrintWriter output = new PrintWriter(outputFile);
			output.print("id,hough1,hough2,hough3,hough4,hough5,hough6,hough7,hough8,\n");
			while (scan.hasNextLine())
			{
				String linha = scan.nextLine();
				if (contador % 2 != 1)
				{
					output.print(caso + ",");
					String[] partesLinha = linha.split(",");
					int[][][] freqs = new int[2][4][11];
					for(int i = 0; i < freqs.length; i++)
					{
						for (int j = 0; j < freqs[i].length; j++)
						{
							for (int k = 0; k < freqs[i][j].length; k++)
							{
								freqs[i][j][k] = 0;
							}
						}
					}
					for(String parte : partesLinha)
					{
						String[] valores = parte.split(";");
						int theta = Integer.parseInt(valores[0]);
						int phi = Integer.parseInt(valores[1]);
						int dist = Integer.parseInt(valores[2]);
						int freq = Integer.parseInt(valores[3]);
						freqs[theta][phi][dist] = freq;
					}
					for(int i = 0; i < freqs.length; i++)
					{
						for (int j = 0; j < freqs[i].length; j++)
						{
							int soma = 0;
							for (int k = 0; k < freqs[i][j].length; k++)
							{
								soma = soma + freqs[i][j][k];
							}
							output.print(soma + ",");
						}
					}
					output.print("\n");
					caso++;
				}
				contador++;
			}
			outputFile.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		//processarArquivoSpharms();
		ProcessarArquivoHough();
	}
}