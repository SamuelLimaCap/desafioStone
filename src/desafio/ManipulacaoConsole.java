package desafio;

public class ManipulacaoConsole {

    public static String[] pegarCaminhos(String[] args) {
        String paths[];
        if (args.length > 2 && args[0].equals("path")) {
            paths = ManipulacaoDeArquivos.testarComCaminhosFixos(args[1], args[2]);
        } else {
            paths = ManipulacaoDeArquivos.getPathsFromConsole();
        }

        return paths;
    }
}
