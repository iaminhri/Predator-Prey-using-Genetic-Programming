package pursuit;

import ec.Evolve;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PredatorRunner {
    public static void main(String[] args) throws IOException {
        int numOfJobs = 2;

        String paramPath = ClassLoader.getSystemResource("predator.params").getPath();
        Path outDir = Paths.get(System.getProperty("user.dir"), "output.d");
        Files.createDirectories(outDir); // creates the directory if it does not exist

        String[] runConfig = new String[] {
                Evolve.A_FILE, paramPath,
                "-p", ("stat=ec.gp.koza.KozaShortStatistics"),
                "-p", ("stat.file=$" + outDir.resolve("out.stat")),
                "-p", ("jobs=" + numOfJobs)
        };
        Evolve.main(runConfig);
    }
}
