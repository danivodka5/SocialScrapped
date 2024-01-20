package Daniel;

import java.util.ArrayList;

public class Stringg {

	public static void main(String[] args) {
		String uno = "https://scontent.cdninstagram.com/o1/v/t16/f1/m78/42498B00492DE2207831EDA66889138D_video_dashinit.mp4?efg=eyJ2ZW5jb2RlX3RhZyI6ImlnLXhwdmRzLnN0b3J5LmMyLUMzLmRhc2hfYmFzZWxpbmVfMV92MSJ9&_nc_ht=scontent.cdninstagram.com&_nc_cat=105&ccb=9-4&oh=00_AfBFftZVS02rhdtn4W4xtw0t1mASbkzgqQut3qhpvY1xsg&oe=65AC90FD&_nc_sid=9ca052&bytestart=866&byteend=921";
		
		int d = uno.indexOf("&bytestart=");
		
		for (int i=0; i<uno.length(); i++) {
			System.out.println(uno.charAt(i) + " "+ i);
		}
		System.out.println(" D ="+d);
		
		String dos = uno.substring(0, d);
	
		ArrayList<String> storysc = new ArrayList<>();
		
		if (!storysc.contains(uno)) {
			System.out.println("No lo tengo, añadiendo..");
			storysc.add(uno);
		}
		
		for (int i=0; i<storysc.size(); i++) {
			System.out.println(storysc.get(i));
		}
		
		
	}

}
