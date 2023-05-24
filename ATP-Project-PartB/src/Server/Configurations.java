package Server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**This is the configurations class
 * Its a singleton class
 * we use it to read info from the configuration file**/
public class Configurations {
    private static Configurations instance;
    private static Properties properties;

    /**The constructor is private because we want to control the number of instances of this class
     * in our case we want only one instance of this class**/
    private Configurations()
    {}
    /**
     * This method gets us the instance of the class
     * if the class wasnt initiated it calls the  private constructor and returns the newly created instance**/
    public static Configurations getInstance()
    {
        if(instance == null)
        {
            instance = new Configurations();
            properties = new Properties();
        }
        return instance;
    }
    /**
     * This method reads the threadPoolSize from the configuration file
     * and returns the values **/
    public int getThreadPoolSize()
    {
        int defult = 3; // this is the default value that will be returned if there are problem with reading from the file
        try
        {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
            properties.load(inputStream);
            if(Integer.parseInt(properties.getProperty("threadPoolSize")) <= 0) // if the number is negative we reutn the default value
            {
                return defult;

            }

            return Integer.parseInt(properties.getProperty("threadPoolSize"));
        }
        catch (Exception e)
        {
            return defult; // and if the value is not a number or is the file doesnt exists we return the default value as well
        }
    }

    public String getMazeGeneratingAlgorithm()
    {
        String defult = "MyMazeGenerator";// this is the default value that will be returned if there are problem with reading from the file
        try
        {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
            properties.load(inputStream);
            // here we check it the value is one of the valid options if its not we return the default value
            if(!(Objects.equals(properties.getProperty("mazeGeneratingAlgorithm"), "MyMazeGenerator") || Objects.equals(properties.getProperty("mazeGeneratingAlgorithm"), "SimpleMazeGenerator") || Objects.equals(properties.getProperty("mazeGeneratingAlgorithm"), "EmptyMazeGenerator")))
            {
                return defult;

            }
            return properties.getProperty("mazeGeneratingAlgorithm");
        }
        catch (IOException e)
        {
            return defult;
        }
    }
    public String getMazeSearchingAlgorithm()
    {
        String defult = "BreadthFirstSearch";// this is the default value that will be returned if there are problem with reading from the file
        try
        {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
            properties.load(inputStream);
            // here we check it the value is one of the valid options if its not we return the default value
            if(!((Objects.equals(properties.getProperty("mazeSearchingAlgorithm"), "BestFirstSearch")) || (Objects.equals(properties.getProperty("mazeSearchingAlgorithm"), "BreadthFirstSearch")) || (Objects.equals(properties.getProperty("mazeSearchingAlgorithm"), "DepthFirstSearch"))))
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
