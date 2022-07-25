/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.utils;
import hr.algebra.model.Product;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *
 * @author tsold
 */
public class ImageConverter {
    
    
    public static void convertImage(Product product) throws IOException{
        
        

        Path target = Paths.get("C:\\home\\site\\wwwroot\\webapps\\NotinoWebShop\\img\\" + product.getProductName()+".png");
        if (!(target.toFile().exists())) {
            
        
        InputStream is = new ByteArrayInputStream(product.getImg());
        BufferedImage newBi = ImageIO.read(is);
        
       
        ImageIO.write(newBi, "png", target.toFile());
        }
      
    }
    
    
    
    public static void renameImage(String oldName,String newName) throws IOException{
        
        if (oldName == newName){}else{
        delete(oldName);  
        }
    
    
    
    }
    
    
    public static void delete(String del){
       Path target = Paths.get("C:\\home\\site\\wwwroot\\webapps\\NotinoWebShop\\img\\"+del+".png");
              target.toFile().delete();
     

    }
    
    
}
