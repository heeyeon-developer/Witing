package com.multi.frame;

import java.io.FileOutputStream;
import org.springframework.web.multipart.MultipartFile;

public class Util {
	public static void saveFile(MultipartFile mf, String admindir, String custdir) {
		System.out.println("1."+mf);
		System.out.println("2."+custdir);
		System.out.println("3."+admindir);
		byte [] data;
		String imgname = mf.getOriginalFilename();
		System.out.println("4."+imgname);
		try {
			data = mf.getBytes();
			FileOutputStream fo = 
					new FileOutputStream(admindir+imgname);
			fo.write(data);
			fo.close();
			FileOutputStream fo2 = 
					new FileOutputStream(custdir+imgname);
			System.out.println("경로확인: "+fo2);
			fo2.write(data);
			fo2.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}




