package example.com.lab6;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Async task that downloads a specified web page.
 * @author Chris Williams
 */
public class DownloadWebpageTask extends AsyncTask<String,Integer,String> {
    private ResultHandler resultHandler;

    public DownloadWebpageTask(ResultHandler resultHandler){
        this.resultHandler=resultHandler;
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            String webpageContents = readStream(httpURLConnection.getInputStream());
            return webpageContents;
        }
        catch (MalformedURLException e){
        }
        catch (java.io.IOException e){
        }
        return "";
    }

    @Override
    protected void onProgressUpdate(Integer... progress){
        //This is not implemented here, but know that you can use this to notify users of the task's progress
    }

    @Override
    protected void onPostExecute(String result){
        resultHandler.handleResult(result);
    }

    private String readStream(InputStream inputStream){
        StringBuilder sb = new StringBuilder();
        try  {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String nextLine = "";
            while ((nextLine = bufferedReader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    public interface ResultHandler{
        public void handleResult(String result);
    }
    }

