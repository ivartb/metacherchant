import ru.ifmo.genetics.utils.tool.Parameter;
import ru.ifmo.genetics.utils.tool.Tool;
import ru.ifmo.genetics.utils.tool.inputParameterBuilder.StringParameterBuilder;
import tools.EnvironmentFinderMain;

public class Runner extends ru.ifmo.genetics.Runner {

    public static final Parameter<String> toolParameter = new Parameter<String>(new StringParameterBuilder("tool")
            .withShortOpt("t")
            .withDescription("set certain tool to run")
            .withDefaultComment(EnvironmentFinderMain.NAME)
            .create());

    static {
        Tool.launchOptions.remove(ru.ifmo.genetics.Runner.toolParameter);
        Tool.launchOptions.add(2, toolParameter);
        Tool.launchOptions.remove(ru.ifmo.genetics.Runner.guiParameter);
    }

    Runner() {
        super();
        defaultTool = findTool(EnvironmentFinderMain.class);
    }


    @Override
    protected void printHeader() {
        out.println("MetaCherchant: genomic environment analysis tool, version " + getVersion());
        out.println();
    }

    @Override
    protected void printFirstHelp() {
        out.println("Usage:     metacherchant [<Launch options>] [<Input parameters>]");
//        printLater.add("To see full documentation visit https://github.com/ctlab/metafast/wiki");
    }

    @Override
    protected boolean checkOptionsBeforeRunning(String[] args) {
        boolean printVersion = containsOption(args, "--version");
        boolean printFirstHelp = (args.length == 0) ||
                (containsOption(args, getOptKeys(Tool.helpParameter)) && !containsOption(args, getOptKeys(toolParameter)));

        if (printVersion) {
            printHeader();
            return true;
        }
        if (printFirstHelp) {
            printHeader();
            printFirstHelp();
            return false;
        }
        return false;
    }



    public static void main(String[] args) {
        new Runner().run(args);
    }
}
