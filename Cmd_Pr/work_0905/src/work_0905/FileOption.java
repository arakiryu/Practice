package work_0905;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FileOption {
	
	Scanner sc = new Scanner(System.in);
	
	
	private String path = startView();
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	//프로그램이 실행된다면?
	
	//1.현재위치 (리눅스 셸을 생각하면 첫번째로는 입력을 받는다 무엇을? 명령어를)
	


	public void enterThePath(String path) {
		
		
		setPath(path);//주소를 받아야하니까
		//패스를 입력받으면 생각할 포인트 (이것이 맞는 주소인가요? 
		
		File file = new File(path);
		
		if(file.isDirectory() == true) {
			System.out.println(file.getPath());//true 라면 현재 경로 반환
			//showFileList(path);
			
		}else {
			System.out.println("현재 경로가 없습니다 다시 입력하세요");
			
		}
		
		
	}
	
	//2. 경로가 맞고 현재 파일에 이동을 했다면 현재 디렉터리 내의 파일들을 보여줄 수 있어야함
	//첫번째 메서드에서 경로가 맞는지 확인을 했으니 현재 디렉터리 내의 파일들만 보여줄수 있으면 됨
	
	public void showFileList(String path) {
		File file = new File(path);
		File[] fileArr = file.listFiles();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		View.printProperty();//상세정보 프린트
		System.out.println();
		
		for(File fl: fileArr) {
			
			System.out.printf("%-30s %-10s %-25s %10d", 
				    fl.getName(),
				    fl.isDirectory() ? "Directory" : fl.getName().substring(fl.getName().lastIndexOf('.') + 1),
				    sdf.format(new Date(fl.lastModified())),
				    fl.length()
				);
			
			System.out.println();
		}
		
		
	}
	
	//3. 이전경로
	// 이전경로를 알기위해서 부모 경로 반환 메서드를 사용
	
	public void backToThePath() {
		File file = new File(path);
		String parent = file.getParent();
		
		//부모 주소 값 반환
		File parentfile = new File(parent);
		setPath(parent);//현재 폴더값으로 변경
		System.out.println(parentfile.getPath()); //현재 폴더 경로 보여주기
		
		
		//or  슬래쉬의 총개수 -1 을 하고 -1된 슬래쉬앞에 있는 문자까지 주소로 지정하든가
		//and 슬래쉬 총개수 -1을 하면 문자도 사라지게 되니까 끝에 있는 문자로 가든지
		
	}
	
	
	
	//4. 이름변경
	//
	public void changeName(String oldname , String newname) {
		
		File oldfile = new File(getPath() + "/" + oldname);
		File newfile = new File(getPath() + "/" + newname);
		
		boolean succes = oldfile.renameTo(newfile);
		  
		if(succes == true) {
			System.out.println("변경 되었습니다" + newname);
		}else {
			System.out.println("변경 되지않았습니다 , 확장자 명까지 입력바랍니다");
		}
	}
	
	
	
	
	//.5 처음 시작시 루트경로 보여주기
	public String startView() {
		
		String rootpath = "C:" + File.separator; 
		return rootpath;		
				
	}

}
