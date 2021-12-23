import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LogFileTailer implements Runnable {

    private int runAfterEverySecond = 2000;
    private long lastKnownPosition =0;
    private File file;
    private boolean shouldIRun =true;

    public LogFileTailer(String fileName , int interval){
        this.file = new File(fileName);
        this.runAfterEverySecond = interval;
    }
    public void stopRunning(){
        this.shouldIRun = false;
    }

    private void printLine(String message){
        System.out.println(message);
    }

    @Override
    public  void run(){
        try {
            while(this.shouldIRun){
                Thread.sleep(this.runAfterEverySecond);
                long fileLength = this.file.length();
                if(fileLength > this.lastKnownPosition){
                    RandomAccessFile accessFile = new RandomAccessFile(this.file,"rw");
                    accessFile.seek(lastKnownPosition);
                    String fileContent;
                    while ((fileContent = accessFile.readLine()) !=null){
                        this.printLine(fileContent);
                    }
                    lastKnownPosition = accessFile.getFilePointer();
                    accessFile.close();
                }
            }
        }catch (InterruptedException exception){
            exception.printStackTrace();
        }catch (FileNotFoundException exception){
            exception.printStackTrace();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
