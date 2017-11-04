package com;

/**
 * @author yhjhoo
 * @since 1.0
 * date : 13 Dec, 2014
 */

public class Constant {
    //public static final String folderPath = "/var/www/upload/";

    public static final String catalina_Home = System.getProperty("user.home");
    //public static final String folderPath = catalina_Home + "/webapps/UploadProgress/download/";

    public static final String relativeFolderPath = "download/";
    public static final String absoluteFolderPath = catalina_Home + "/webapps/UploadProgress/download/";
}
