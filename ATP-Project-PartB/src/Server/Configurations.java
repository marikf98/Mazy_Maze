package Server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class Configurations {
    private static Configurations instance;
    private static Properties properties;

    private Configurations()
    {}

    public static Configurations getInstance()
    {
        if(instance == null)
        {
            instance = new Configurations();
            properties = new Properties();
        }
        return instance;
    }

    public int getThreadPoolSize()
    {
        int defult = 3;
        try
        {
            InputStream inputStream = new FileInputStream("resources/config.properties");
            properties.load(inputStream);
            if(Integer.parseInt(properties.getProperty("threadPoolSize")) <= 0)
            {
                return defult;

            }
            return Integer.parseInt(properties.getProperty("threadPoolSize"));
        }
        catch (IOException e) {
            return defult;
        }
    }

    public String getMazeGeneratingAlgorithm()
    {
        String defult = "MyMazeGenerator";
        try
        {
            InputStream inputStream = new FileInputStream("resources/config.properties");
            properties.load(inputStream);
            if(!(Objects.equals(properties.getProperty("mazeGeneratingAlgorithm"), "MyMazeGenerator") || Objects.equals(properties.getProperty("mazeGeneratingAlgorithm"), "SimpleMazeGenerator") || Objects.equals(properties.getProperty("mazeGeneratingAlgorithm"), "EmptyMazeGenerator")))
            {
                return defult;

            }
            return properties.getProperty("mazeGeneratingAlgorithm");
        }
        catch (IOException e) {
            return defult;
        }
    }

    public String getMazeSearchingAlgorithm()
    {
        String defult = "BreadthFirstSearch";
        try
        {
            InputStream inputStream = new FileInputStream("resources/config.properties");
            properties.load(inputStream);
            if(!(Objects.equals(properties.getProperty("mazeSearchingAlgorithm"), "BestFirstSearch") || Objects.equals(properties.getProperty("mazeGeneratingAlgorithm"), "BreadthFirstSearch") || Objects.equals(properties.getProperty("mazeGeneratingAlgorithm"), "DepthFirstSearch")))
            {
                return defult;

            }
            return properties.getProperty("mazeSearchingAlgorithm");
        }
        catch (IOException e) {
            return defult;
        }
    }
}
