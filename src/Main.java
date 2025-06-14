import biblioteca.BibliotecaService;

import models.*;

import utils.LibMethods;
import utils.SimpleSerializationLib;
import java.io.EOFException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Main {

    private static Path pathBin = Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + "bin");
    private static BibliotecaService bibliotecaService = new BibliotecaService();
    private static List<ArquivoPDF> list = new ArrayList<>();
    private static List<String> libs = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        if (args.length == 0) {
            functions();
            return;
        }


        String command = args[0].toLowerCase();

        // Verifica a se existe os arquivos de persistência e lê eles
        try {
            LibMethods.checkBin();
            libs = LibMethods.checkLib();
            bibliotecaService.setLibName(libs.get(0));
            bibliotecaService.setCurrentPath(pathBin + System.getProperty("file.separator") + bibliotecaService.getLibName());
            list = (List<ArquivoPDF>) SimpleSerializationLib.readObjectFromFile(pathBin + System.getProperty("file.separator") + bibliotecaService.getLibName() + ".ser");



        }catch (Exception e) {
            System.err.println("biblioteca vazia");
        }

        Main.help();


        if (bibliotecaService.getLibName() == null){
            System.out.println("Não existe nenhuma bilbioteca.\n");
            command = "create-lib";
        }

        switch (command){

            case "create-lib":
                if ( args.length < 2) {
                    System.out.println("Erro: Nome da biblioteca é obrigatório");
                    System.out.println("Uso: java Main create-lib <nome>");
                    return;
                }
                bibliotecaService.createDiretory(args[1]);
                list.clear();
                libs.add(bibliotecaService.getLibName());
                SimpleSerializationLib.writeObjectToFile( libs,System.getProperty("user.dir") + System.getProperty("file.separator") + "bin" + System.getProperty("file.separator") + "lib.ser");
                break;

            case "add-book":
                if (args.length < 7) {
                    System.out.println("Erro: Parâmetros insuficientes para criar livro");
                    System.out.println("Uso: java Main add-book <autor> <titulo> <subtitulo> <area> <ano> <caminho>");
                    return;
                }
                Livro livro = new Livro(args[1], args[2], args[3], args[4], Integer.parseInt(args[5]), args[6]);
                livro.copyToDirectory(livro.getTitle(), livro.getType(), livro.getPath(), bibliotecaService.getCurrentPath());
                list.add(livro);
                SimpleSerializationLib.writeObjectToFile(list, pathBin + System.getProperty("file.separator") + bibliotecaService.getLibName() + ".ser");
                break;
            case "add-slide":
                if (args.length < 5) {
                    System.out.println("Erro: Parâmetros insuficientes para criar slide");
                    System.out.println("Uso: java Main add-slide <titulo> <disciplina> <autor> <caminho>");
                    return;
                }
                Slide slide = new Slide(args[1], args[2], args[3], args[4]);
                slide.copyToDirectory(slide.getTitle(), slide.getType(), slide.getPath(), bibliotecaService.getCurrentPath());
                list.add(slide);
                SimpleSerializationLib.writeObjectToFile(list, pathBin + System.getProperty("file.separator") + bibliotecaService.getLibName() + ".ser");
                break;
            case "add-notes":
                if (args.length < 6) {
                    System.out.println("Erro: Parâmetros insuficientes para criar notas");
                    System.out.println("Uso: java Main add-notes <titulo> <subtitulo> <disciplina> <autor> <caminho>");
                    return;
                }
                NotaDeAula notaDeAula = new NotaDeAula(args[1], args[2], args[3], args[4], args[5]);
                notaDeAula.copyToDirectory(notaDeAula.getTitle(), notaDeAula.getType(), notaDeAula.getPath(), bibliotecaService.getCurrentPath());
                list.add(notaDeAula);
                SimpleSerializationLib.writeObjectToFile(list, pathBin + System.getProperty("file.separator") + bibliotecaService.getLibName() + ".ser");
                break;
            case "list-pdf":
                try {
                    list = (List<ArquivoPDF>) SimpleSerializationLib.readObjectFromFile(pathBin + System.getProperty("file.separator") + bibliotecaService.getLibName() + ".ser");
                    list.forEach(System.out::println);
                    break;
                } catch (EOFException e){
                    System.err.println("A biblioteca nao possui nenhum pdf adicionado adicionado");
                    break;
                }

            case "change-lib":
                libs = LibMethods.checkLib();
                Optional<String> libSearch = LibMethods.changeLib(libs);
                libs.add(0,libSearch.get());
                SimpleSerializationLib.writeObjectToFile(libs, pathBin + System.getProperty("file.separator") + "lib.ser");
                bibliotecaService.setCurrentPath(pathBin + System.getProperty("file.separator") + libSearch.get());
                list.clear();
                try{
                    list = (List<ArquivoPDF>) SimpleSerializationLib.readObjectFromFile(pathBin + System.getProperty("file.separator") + bibliotecaService.getLibName() + ".ser");
                }
                catch (EOFException e){
                    System.out.println("Esta biblioteca nao possui pdfs");
                }

                System.out.println("Biblioteca " + libSearch.get() + " selecionada");

                break;


            case "list-libs":
                List <String> allLibs = (List<String>) SimpleSerializationLib.readObjectFromFile(pathBin + System.getProperty("file.separator") + "lib.ser");
                LibMethods.distinctList(allLibs).forEach(System.out::println);
                break;


            default:
                System.out.println("Digite uma opção válida\n");
                functions();
        }



    }

    public static void functions(){
        System.out.println(
                "Escolha uma opção\n" +
                        "create-lib\n" +
                        "add-book\n" +
                        "add-slide\n" +
                        "add-notes\n" +
                        "list-pdf\n" +
                        "change-lib\n" +
                        "exit\n");
    }
    public static void help(){
        System.out.println("""
            """);
    }

}
