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
		String path = "C:\\JSP\\projects\\gr202\\src\\main\\webapp\\static\\img\\qr_code";
		
		try {
			//파일 경로가 없으면 생성하기
			File file = new File(path); 
			if(!file.exists()) { 
		   	 	file.mkdirs(); 
			} 
			String qr_num = m_brith.substring(0,4) + String.valueOf(m_num);
			byte[] bytes = qr_num.getBytes("UTF-8");
			String fileName = new String(bytes);
			//
			BitMatrix bitMatrix = write.encode(fileName, BarcodeFormat.QR_CODE, 400, 400);
			//qrcodeColor, backgroundColor
			MatrixToImageConfig config = new MatrixToImageConfig(0x00000000 , 0xFFFFFFFF);
			BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix , config);
			
		    //파일 이름 , 파일 확장자에 맡는 파일 생성
			File temp =  new File(path, fileName+".png"); 
			// ImageIO를 사용하여 파일쓰기 temp 위치에 qr이 이미지 생성됨. 
			ImageIO.write(qrImage, "png",temp );
			
			return fileName;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
