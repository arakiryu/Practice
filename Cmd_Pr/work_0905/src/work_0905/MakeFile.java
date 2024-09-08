package work_0905;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeFile {
	
	//현재 디렉토리
	
	
	public void makeDirAndFile(String dir ,String fn,String rd)throws IOException {
		File dr = new File(dir);//아래와 마찬가지
		File fl = new File(fn); //fn에서 경로설정까지 해줘야함
		
		if(dr.exists() == false)dr.mkdir();//디렉터리 
		if(fl.exists() == false)fl.createNewFile();//있는지 없는지 검사하고 없다면 파일을 만들어줌
		
		
		File temp = new File(rd);// 상위폴더 주소로 객체생성 rd = 상위폴더 주소
		File[] list = temp.listFiles();//상위폴더의 리스트들 보여주기
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a HH-mm-ss");
		
		for(File file : list) {
			if(file.isDirectory()){System.out.printf("%-10s%-20s", "<DIR>", file.getName());}
			else { System.out.printf("%-10s%-20s" , file.length(),file.getName());}
			System.out.printf("%-25s", sdf.format(new Date(file.lastModified())));
		}
		System.out.println();
			
	}
	
	
	public void rootdir() { //현재 최상위 루트 보여주기
		File[] roots = File.listRoots();
		
		for (File root : roots) {
			System.out.println(root.getAbsolutePath());
		}
	}
	
	
	public void movedir(String currentPath, String newPath) {
	
		path source 
		
	}
		
		
	}
	
	
}
