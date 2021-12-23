package desafio;

import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        List<String> pessoas = new ArrayList<>();
        List<Despesa> despesas = null;
        String paths[] = null; //path[0] => caminho do arquivo de despesa; path[1] caminho do arquivo de pessoas
        BufferedReader brDespesas;
        BufferedReader brPessoas;

        paths = ManipulacaoConsole.pegarCaminhos(args);
        brDespesas = ManipulacaoDeArquivos.carregarLeitorDeArquivo(paths[0]);
        brPessoas = ManipulacaoDeArquivos.carregarLeitorDeArquivo(paths[1]);
        try {
            despesas = pegarDespesasDoArquivo(brDespesas);
            pessoas = pegarPessoasDoArquivo(brPessoas);
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
        Map<String, Double> valorPorPessoa = calcular(pessoas, despesas);
        ManipulacaoDeArquivos.exportFormattedToFile(valorPorPessoa, paths[0]);
    }

    public static List<Despesa> pegarDespesasDoArquivo(BufferedReader br) throws IOException {
        List<Despesa> despesas = new ArrayList<>();
        String line;
        Despesa despesa = null;

        while ((line = br.readLine()) != null) {
            if ( !line.isEmpty() ) {
                try {
                    Object[] obj = pegarDespesa(br, line);
                    despesa =  (Despesa) obj[1];
                    if (despesa != null) despesas.add(despesa);
                    br = (BufferedReader) obj[0];
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return despesas;
    }

    /**
     *
     * @param br BufferedReader
     * @param lineAtual String
     * @return Object[] {BufferedReader br, Despesa despesa}
     * @throws IOException
     */
    public static Object[] pegarDespesa(BufferedReader br, String lineAtual) throws IOException {
        String line = lineAtual.trim();
        Despesa despesa = new Despesa();

        do {
            line = line.trim();
            if (line.contains("nome:") && despesa.nome == null) {
                despesa.nome = pegarInformacao("nome", line);
            } else if (line.contains("quantidade:")) {
                despesa.quantidade = Double.parseDouble(
                        pegarInformacao("quantidade:", line)
                                .replaceAll("\\.","")
                                .replace(",",".")
                                .trim()
                );
                
            } else if (line.contains("valor unitário:")) {
                String stringValorEmReais = pegarInformacao("valor unitário", line)
                        .replace("R$", "")
                        .replaceAll("\\.","")
                        .replace(",", ".")
                        .trim();

                String valorEmCentavos = stringValorEmReais.split("\\.")[0] + stringValorEmReais.split("\\.")[1].substring(0,2);
                despesa.valorUnitario = Long.valueOf(valorEmCentavos.split("\\.")[0]) ; //Transformando para centavos

                break;
            } else {
                throw new RuntimeException("Informação errada: " + line);
            }
        } while ( ((line = br.readLine() ) != null) );

        return new Object[] {br, despesa};
    }

    public static String pegarInformacao(String chave, String linha) {
        String chaveComDoisPontos = chave + ":";
        return linha.substring(linha.indexOf(chaveComDoisPontos) + chaveComDoisPontos.length());
    }

    public static List<String> pegarPessoasDoArquivo(BufferedReader br) throws IOException {
        List<String> pessoas = new ArrayList<>();
        String line;
        Despesa despesa = null;

        while ((line = br.readLine()) != null) {
            if ( !line.isEmpty() ) {
                try {
                    pessoas.add(line);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return pessoas;
    }

    public static Map<String,Double> calcular(List<String> pessoas, List<Despesa> despesas) {
        Double valorTotalEmCentavos;

        valorTotalEmCentavos = calcularValorTotalDespesas(despesas); //valorTotal pode vir com virgula;
        Map<String, Double> map = calcularValorAPagarPorPessoa(valorTotalEmCentavos, pessoas);

        return map;
    }

    public static Double calcularValorTotalDespesas(List<Despesa> despesaList) {
        Double sum = 0.0;
        for (Despesa despesa : despesaList) {
            if (despesa.verificarQuantidade() && despesa.verificarValorUnitario()) {
                sum += despesa.quantidade * despesa.valorUnitario;
            } else {
                throw new RuntimeException("Uma despesa está com valores incorretos: " + despesa.toString());
            }
        }

        return sum;
    }

    public static Map<String, Double> calcularValorAPagarPorPessoa(Double sum, List<String> pessoas) {
        Map<String, Double> mapPessoaValor = new HashMap<>();
        DecimalFormat df = new DecimalFormat("#.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.CANADA));
        
        if (pessoas.size() == 0) return mapPessoaValor;
        Double valuePerPerson = sum/pessoas.size();

        Long valuePerPersonOnlyInt = Long.parseLong(df.format(valuePerPerson).split("\\.")[0]);

        for( String pessoa : pessoas) {

            if (mapPessoaValor.containsKey(pessoa)) {
                throw new RuntimeException("Essa pessoa está cadastrada mais de uma vez:" + pessoa);
            }else {
                mapPessoaValor.put(pessoa,  valuePerPersonOnlyInt.doubleValue());
            }
        }
        Double decimalPorPessoa = valuePerPerson - valuePerPersonOnlyInt;

        if (decimalPorPessoa > 0.00) {
            Integer valorArrendondado = (int) Math.round(decimalPorPessoa*pessoas.size());
            mapPessoaValor.put(pessoas.get( ( pessoas.size()) - 1), (double) valuePerPersonOnlyInt + valorArrendondado);


        }
        return mapPessoaValor;
    }

}