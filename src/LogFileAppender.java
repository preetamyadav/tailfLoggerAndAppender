import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogFileAppender {

    private String fileName;
    private boolean shouldIRun =true;
    private int runAfterEverySecond = 2000;

    public LogFileAppender(String fileName ,int interval){
        this.fileName  = fileName;
        this.runAfterEverySecond = interval;
    }
    public void stopRunning(){
        this.shouldIRun = false;
    }

    public void appendData(){
        FileWriter fileWriter;
        try {
            while (this.shouldIRun){
                Thread.sleep(this.runAfterEverySecond);
                fileWriter = new FileWriter(this.fileName,true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                String data = "\n this is preetam Yadav log file content: "+Math.random();
                bufferedWriter.write(data);
                bufferedWriter.close();
            }

        }catch (InterruptedException exception){
            exception.printStackTrace();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
