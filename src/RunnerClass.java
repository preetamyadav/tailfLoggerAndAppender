import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnerClass {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        String filePath ="/home/preeam/Desktop/LogTailF/src/samplelogfile.log";
        LogFileTailer logFileTailer = new LogFileTailer(filePath,2000);
        executorService.execute(logFileTailer);
        LogFileAppender logFileAppender = new LogFileAppender(filePath,5000);
        logFileAppender.appendData();
    }
}
