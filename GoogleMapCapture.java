package com.example.myapplication;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GoogleMapCapture {
    
    static int increment = 0;
    static int rowNo = 0;
    private static java.lang.String[] str;

    public static void main(String[] args) throws InterruptedException, AWTException {

        double x = 38.0;
        double y = -123.0;

        double toX = 37.0;
        double toY = -122.0;

        double zoom = 14.0; //12.0
        double step = 0.0600000; //0.1500000

        String savePath = "c:\\tmp\\";

        boolean isReach = true;

        System.setProperty("webdriver.chrome.driver", "C:\\selenium-java-3.8.1\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.manage().window().fullscreen();

        driver.get("https://www.google.com/maps/@" + x + "," + y + "," + zoom + "z?hl=en");


        while (isReach) {

            str = driver.getCurrentUrl().split(",");

            java.lang.String[] split = driver.getCurrentUrl().split("@");
            String[] tmpStr = split[1].split(",");


            if (Float.parseFloat(tmpStr[0]) <= (toX)) {

                isReach = false;
            }

            if (Float.parseFloat(str[1]) < toY) {

                System.out.println("⯆");
                Thread.sleep(250);

                java.lang.String[] str = driver.getCurrentUrl().split("@");
                str = str[1].split(",");

                driver.get("https://www.google.com/maps/@" + str[0] + "," + (Float.parseFloat(str[1]) + step) + "," + zoom + "z?hl=en");

                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                try {
                    FileUtils.copyFile(scrFile, new File( savePath + "map" + rowNo+ "_" + increment++ + ".jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (Float.parseFloat(str[1]) > toY) {
                System.out.println("⯈");
                java.lang.String[] str = driver.getCurrentUrl().split("@");
                str = str[1].split(",");
                rowNo++;
                driver.get("https://www.google.com/maps/@" + (Double.parseDouble(str[0]) - 0.0600000) + ","+ y + "," + zoom + "z?hl=en"); //0.0300000
            }
        }
        System.out.println("Total: "+ increment + " screenshots");
        System.out.println("Total: "+ rowNo++ + " rows");
        driver.quit();
    }
}

