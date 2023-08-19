package constants;

import java.io.File;

public enum Constants {
    ASSETS_PATH {
        public String toString() {
            File currentDirectory = new File(new File(".").getAbsolutePath());
            return currentDirectory + "/storage/assets/";
        }
    }
}
