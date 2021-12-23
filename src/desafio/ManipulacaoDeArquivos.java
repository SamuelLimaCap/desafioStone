package desafio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ManipulacaoDeArquivos {

    public static String[] testarComCaminhosFixos(String path1, String path2) {
        return new String[] {path1, path2};
    }

    public static String[] getPathsFromConsole() {
        Scanner sc = new Scanner(System.in);
        System.out.println("(caso esteja na mesma pasta do programa, digite apenas o nome do arquivo)");
        System.out.println("Digite o endereço do arquivo de despesas:");
        String path = sc.next();
        System.out.println("Digite o endereço do arquivo de pessoas");
        String path2 = sc.next();
        sc.close();

        return new String[] {path, path2};
    }

    public static BufferedReader carregarLeitorDeArquivo(String path) throws FileNotFoundException {
        return new BufferedReader(new FileReader(path));
    }

    public static void exportFormattedToFile(Map<String, Double> impressao, String path) throws IOException {
        File file = new File(path);
        String fileName = file.getParent() + File.separator + "out-" + file.getName();
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

        impressao.forEach((nome, valor) -> {

            String valorEmReais = String.format("%.2f", (valor/100)).replace(".",",");
            String line = nome + ": R$ " + valorEmReais;
            try {
                bw.write(line);
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        bw.close();
    }
}
