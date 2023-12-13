package ImageDownloader;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.google.common.io.Files;
public class ImageDownloader {
	
	public ImageDownloader(String url,String path, String user, int number) {	
        String extension = obtenerExtensionImagen(url);
        if (extension != null) {
            System.out.println("La extensión de imagen del URL es: " + extension);
            try {
    			downloadImage(url,path,user,1,extension);
    			System.out.println("Imagen descargada con exito");
    		} catch (IOException e) { e.printStackTrace(); }
        }    	
	}
	
    public static void downloadImage(String imageUrl, String destinationPath,String user,int number, String extension) throws IOException {
    	try {
    		URL url = new URL(imageUrl);
    		InputStream is = url.openStream();
    		FileOutputStream fos = new FileOutputStream(destinationPath+"\\"+user+number+"."+extension);
    		byte[] bytes = new byte[1024];
    		int len;
    		while((len = is.read(bytes)) != -1) {
    			fos.write(bytes,0,len);
    		}
    		fos.close();
    		is.close();
    		System.out.println("Descargado");
    	}catch (Exception e) {
    		e.printStackTrace();
    	}	
    }	
    public static String obtenerExtensionImagen(String url) {
        try {
            URLConnection connection = new URL(url).openConnection();
            String contentType = connection.getContentType(); 
            Pattern pattern = Pattern.compile("image/(\\w+)");
            Matcher matcher = pattern.matcher(contentType);

            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] arg) {
    	// DEMO
    	/*
        String url = "";
        String extension = obtenerExtensionImagen(url);
        if (extension != null) {
            System.out.println("La extensión de imagen del URL es: " + extension);
            try {
    			downloadImage(url,"D:\\Prueba","zac",1,extension);
    		} catch (IOException e) { e.printStackTrace(); }
        }    
        */
    }
}
