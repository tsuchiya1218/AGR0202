package util;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class GenerateQRcode {
	public static String generateQRcode(int m_num,String m_brith) {
		QRCodeWriter write = new QRCodeWriter();
		String path = "C:\\Users\\20jy0209\\git\\AGR0202\\src\\main\\webapp\\static\\img\\qr_code";
		try {
			//Fileのパスがない場合作る
			File file = new File(path); 
			if(!file.exists()) { 
		   	 	file.mkdirs(); 
			} 
			String qr_num = m_brith.substring(0,4) + String.valueOf(m_num);
			byte[] bytes = qr_num.getBytes("UTF-8");
			String fileName = new String(bytes);
			BitMatrix bitMatrix = write.encode(fileName, BarcodeFormat.QR_CODE, 400, 400);
			//qrcodeColor, backgroundColor
			MatrixToImageConfig config = new MatrixToImageConfig(0x00000000 , 0xFFFFFFFF);
			BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix , config);
			
			File temp =  new File(path, fileName+".png"); 
			// tempのpathに QR画像が作られる. 
			ImageIO.write(qrImage, "png",temp );
			System.out.println(temp.getName());
			return fileName;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
