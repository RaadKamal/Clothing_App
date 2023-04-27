package clothingapp.persistence.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import clothingapp.application.Main;

public class DatabaseCopier {

    public static boolean copyDatabaseToDevice(Context context) {
        boolean successfulCopy;

        if(context != null){
            final String DB_PATH = "db";

            String[] assetNames;
            File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
            AssetManager assetManager = context.getAssets();

            try {

                assetNames = assetManager.list(DB_PATH);
                for (int i = 0; i < assetNames.length; i++) {
                    assetNames[i] = DB_PATH + "/" + assetNames[i];
                }

                copyAssetsToDirectory(assetNames, dataDirectory, context);
                Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);
                successfulCopy = true;

            } catch (IOException ioe) {
                successfulCopy = false;
                System.out.println("Unable to access application data: " + ioe.getMessage());
            }
        }
        else{
            successfulCopy = false;
            Main.setDBPathName(null);
        }

        return successfulCopy;
    }

    private static boolean copyAssetsToDirectory(String[] assets, File directory, Context context) throws IOException {

        boolean successfulCopy = false;

        if(assets.length > 0){
            AssetManager assetManager = context.getAssets();

            for (String asset : assets) {
                String[] components = asset.split("/");
                String copyPath = directory.toString() + "/" + components[components.length - 1];
                char[] buffer = new char[1024];
                int count;

                File outFile = new File(copyPath);

                if (!outFile.exists()) {
                    InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                    FileWriter out = new FileWriter(outFile);

                    count = in.read(buffer);
                    while (count != -1) {
                        out.write(buffer, 0, count);
                        count = in.read(buffer);
                    }

                    successfulCopy = true;
                    out.close();
                    in.close();
                }
            }
        }

        return successfulCopy;
    }
}
