package Basic;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
public class ScreenShot {
    public WebDriver driver;
    public ScreenShot(WebDriver driver){
       this.driver=driver;
    }
    private void takeScreenshot(String screenPath){
        try {
            //获取截图file
            File scrFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //将图片移动到指定位置
            FileUtils.moveFile(scrFile, new File(screenPath));
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void takeScreenshot(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd(hh_mm_ss)SSS");
        String fileName=dateFormat.format( new Date());
        //创建新的文件名
        String screenName=fileName+".jpg";
        //创建文件夹
        File dir = new File("test-output/snapshot");
        if (!dir.exists()){
            dir.mkdirs();
        }
        //获取新文件名的绝对路径
        String screenPath= dir.getAbsolutePath()+"/"+screenName;
        //截图
        this.takeScreenshot(screenPath);
    }
}